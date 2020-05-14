/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     AQSThreadPool
 * CreationDate: 2020/5/13
 * Author edward
 */
package com.edward.basic.threadpool.aqs;

import com.edward.basic.future.Callable;
import com.edward.basic.future.Future;
import com.edward.basic.future.FutureTask;
import com.edward.basic.queue.blocking.BlockingQueue;
import com.edward.basic.threadpool.MyExecutor;
import com.edward.basic.threadpool.RejectStrategy;
import com.edward.basic.threadpool.Status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class BlockThreadPool extends Thread implements MyExecutor {

    private final int core;
    private final int max;
    private final long keepAlive;
    private final TimeUnit timeUnit;
    private final BlockingQueue<Runnable> workerQueue;
    private final RejectStrategy rejectStrategy;
    private final List<Worker> workers = new ArrayList<>();
    private volatile boolean shutdown;

    public BlockThreadPool(int core, int max, long keepAlive, TimeUnit timeUnit,
                           BlockingQueue<Runnable> workerQueue,
                           RejectStrategy rejectStrategy) {
        this.core = core;
        this.max = max;
        this.keepAlive = keepAlive;
        this.timeUnit = timeUnit;
        this.workerQueue = workerQueue;
        this.rejectStrategy = rejectStrategy;
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
            if (workerQueue.empty() && workers.size() > core) {
                int needRelease = workers.size() - core;
                Iterator<Worker> iterator = workers.iterator();
                while (iterator.hasNext()) {
                    if (needRelease <= 0) {
                        break;
                    }
                    Worker worker = iterator.next();
                    long endTime = System.currentTimeMillis() + timeUnit.toMillis(keepAlive);
                    if (endTime - worker.freeStartTime <= 0) {
                        worker.shutdown();
                        iterator.remove();
                        needRelease--;
                    }
                }
            }
        }
    }

    @Override
    public void submit(Runnable runnable) {
        if (workers.size() < core) {
            Worker thread = new Worker(runnable);
            workers.add(thread);
            thread.start();
        } else if (workers.size() >= core) {
            boolean successor = workerQueue.offer(runnable);
            if (!successor && workers.size() < max) {
                Worker thread = new Worker(runnable);
                workers.add(thread);
                thread.start();
            } else if (workers.size() >= max) {
                rejectStrategy.reject(runnable);
            }
        }
    }

    @Override
    public <E> Future<E> submit(Callable<E> callable) {
        FutureTask<E> task = new FutureTask<>();
        submit(() -> {
            E e = callable.call();
            task.setValue(e);
        });
        return task;
    }

    @Override
    public void shutdown() throws InterruptedException {
        while (!workerQueue.empty()) {
            Thread.sleep(1000L);
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

    class Worker extends Thread {

        private volatile Status status;

        private Runnable runnable;
        private volatile long freeStartTime;

        public Worker(Runnable runnable) {
            this.status = Status.FREE;
            this.runnable = runnable;
        }

        @Override
        public void run() {
            if (runnable != null) {
                runnable.run();
                runnable = null;
            }
            while (status != Status.DEATH) {
                if (workerQueue.empty()) {
                    status = Status.BLOCKING;
                }
                try {
                    runnable = workerQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
}
