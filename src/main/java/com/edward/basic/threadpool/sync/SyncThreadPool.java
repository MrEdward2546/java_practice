/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     SyncThreadPool
 * CreationDate: 2020/5/13
 * Author edward
 */
package com.edward.basic.threadpool.sync;

import com.edward.basic.future.Callable;
import com.edward.basic.future.Future;
import com.edward.basic.future.FutureTask;
import com.edward.basic.threadpool.MyExecutor;
import com.edward.basic.threadpool.RejectStrategy;
import com.edward.basic.threadpool.Status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池的使用synchronized实现
 * 1.提交任务流程
 * 1.1 如果任务少于核心线程数，则创建新线程去执行任务
 * 1.2 如果任务大于核心线程但是队列尚未满，则将任务放入任务队列
 * 1.3 如果任务任务大于核心线程小于最大线程数并且队列也满了，那么则创建新线程去执行
 * 1.4 其他情况，则执行拒绝策略
 * <p>
 * 2. SyncThreadPool也是一个线程，主要用于检查是否可以回收线程，如果队列空了，且工作线程还存在，则执行线程回收
 *
 * @author edward
 * @see
 * @since
 */
public class SyncThreadPool extends Thread implements MyExecutor {
    /**
     * 核心线程数
     */
    private final int core;
    /**
     * 最大运行的线程数
     */
    private final int max;
    /**
     * 如果没有任务，且工作线程数量过多，超过timeout则执行回收线程（workers.size() - core）
     */
    private long timeout;
    private TimeUnit timeUnit;
    /**
     * 工作队列
     */
    private final LinkedList<Runnable> workQueue = new LinkedList<>();
    private long maxTasks;
    /**
     * 执行中的线程
     */
    private final List<Worker> workers = new ArrayList<>();
    /**
     * 拒绝策略
     */
    private final RejectStrategy rejectStrategy;
    /**
     * 当前线程池是否已经关闭
     */
    private volatile boolean shutdown;

    public SyncThreadPool(int core, int max, long timeout, TimeUnit timeUnit, int maxTasks, RejectStrategy rejectStrategy) {
        this.core = core;
        this.max = max;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.maxTasks = maxTasks;
        this.rejectStrategy = rejectStrategy;
        this.start();
    }

    /**
     * 线程池的维护线程，保证多余的线程能得到回收
     */
    @Override
    public void run() {
        while (!shutdown) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (workers) {
            System.out.printf("core:%d,max:%d,worker:%d,tasks:%d", core, max, workers.size(), workQueue.size());
            if (workQueue.isEmpty() && workers.size() > core) {
                int needRelease = workers.size() - core;
                Iterator<Worker> iterator = workers.iterator();
                while (iterator.hasNext()) {
                    if (needRelease <= 0) {
                        break;
                    }
                    Worker worker = iterator.next();
                    long endTime = System.currentTimeMillis() + timeUnit.toMillis(timeout);
                    if (endTime - worker.freeStartTime <= 0) {
                        worker.shutdown();
                        iterator.remove();
                        needRelease--;
                    }
                }
            }
        }
    }

    /**
     * 提交runnable对象，不带返回结果
     *
     * @param runnable
     */
    @Override
    public void submit(Runnable runnable) {
        if (workers.size() < core) {
            Worker worker = new Worker(runnable);
            workers.add(worker);
            worker.start();
        } else if (workers.size() >= core && workQueue.size() < maxTasks) {
            synchronized (workQueue) {
                workQueue.addLast(runnable);
                workQueue.notifyAll();
            }
        } else if (workers.size() >= core && workers.size() < max && workers.size() >= maxTasks) {
            Worker worker = new Worker(runnable);
            workers.add(worker);
            worker.start();
        } else {
            rejectStrategy.reject(runnable);
        }

    }

    /**
     * 提交带返回值的结果
     *
     * @param callable
     * @param <E>
     * @return
     */
    @Override
    public <E> Future<E> submit(Callable<E> callable) {
        FutureTask<E> task = new FutureTask<>();
        submit(() -> {
            E e = callable.call();
            task.setValue(e);
        });
        return task;
    }

    /**
     * 关闭线程
     *
     * @throws InterruptedException
     */
    @Override
    public void shutdown() throws InterruptedException {
        while (!workQueue.isEmpty()) {
            Thread.sleep(50L);
        }
        synchronized (workers) {
            int activeCount = workers.size();
            while (activeCount > 0) {
                for (Worker worker : workers) {
                    if (worker.status == Status.BLOCKING) {
                        worker.interrupt();
                        worker.shutdown();
                        activeCount--;
                    } else {
                        Thread.sleep(1000);
                    }
                }
            }
        }
        this.shutdown = true;
    }

    /**
     * 实际的工作线程
     */
    private AtomicInteger atomicInteger = new AtomicInteger(1);
    class Worker extends Thread {
        private volatile Status status = Status.FREE;
        private Runnable runnable;

        public Worker(Runnable target) {
            this.runnable = target;
            this.setName("worker-" + atomicInteger.getAndAdd(1));
        }

        public volatile long freeStartTime;

        @Override
        public void run() {
            if (this.runnable != null) {
                runnable.run();
                runnable = null;
            }
            retry:
            while (status != Status.DEATH) {
                synchronized (workQueue) {
                    while (workQueue.isEmpty()) {
                        status = Status.BLOCKING;
                        try {
                            workQueue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("closed");
                            break retry;
                        }
                    }
                    runnable = workQueue.removeFirst();
                }
                if (this.runnable != null) {
                    status = Status.RUNNING;
                    runnable.run();
                    status = Status.FREE;
                    freeStartTime = System.currentTimeMillis();
                }
            }
        }

        public void shutdown() {
            this.status = Status.DEATH;
        }


    }

    public static void main(String[] args) throws InterruptedException {
        SyncThreadPool threadPool = new SyncThreadPool(4, 14, 10, TimeUnit.MILLISECONDS, 100, r -> {
            throw new RuntimeException("discard");
        });
        for (int i = 0; i < 40; i++) {
            threadPool.submit(() -> {
                System.out.println("The runnable  be serviced by " + Thread.currentThread().getName() + " start.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable be serviced by " + Thread.currentThread().getName() + " finished.");
            });
        }
        threadPool.shutdown();
    }
}
