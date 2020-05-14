/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     ArrayBlockQueue
 * CreationDate: 2020/5/14
 * Author edward
 */
package com.edward.basic.queue.blocking;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class LinkedBlockQueue<T> implements BlockingQueue<T> {
    private final Lock lock = new ReentrantLock();
    private final Condition canPut = lock.newCondition();
    private final Condition canTake = lock.newCondition();
    private final int capacity;
    private volatile int size;
    private Node head;
    private Node tail;

    public LinkedBlockQueue(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (empty()) {
                canTake.await();
            }
            T data = head.data;
            head = head.next;
            canPut.signalAll();
            size--;
            return data;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void put(T ele) throws InterruptedException {
        lock.lock();
        try {
            while (size == capacity) {
                canPut.await();
            }
            if (head == null) {
                head = tail = new Node(ele, null);
            } else {
                tail.next = new Node(ele, null);
                tail = tail.next;
            }
            size++;
            canTake.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean offer(T ele) {
        lock.lock();
        try {
            if (size >= capacity) {
                return false;
            }
            if (head == null) {
                head = tail = new Node(ele, null);
            } else {
                tail.next = new Node(ele, null);
                tail = tail.next;
            }
            size++;
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T poll() {
        lock.lock();
        try {
            if (size == 0) {
                return null;
            }
            T data = head.data;
            head = head.next;
            size--;
            return data;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public T peek() {
        lock.lock();
        try {
            if (size == 0) {
                return null;
            }
            return head.data;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public T dequeue() {
        lock.lock();
        try {
            if (size == 0) {
                throw new RuntimeException("队列已空");
            }
            T data = head.data;
            head = head.next;
            size--;
            return data;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void enqueue(T t) {
        lock.lock();
        try {
            if (size >= capacity) {
                throw new RuntimeException("队列已满");
            }
            if (head == null) {
                head = tail = new Node(t, null);
            } else {
                tail.next = new Node(t, null);
                tail = tail.next;
            }
            size++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        return 0;
    }

    private class Node {
        private T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedBlockQueue<String> container = new LinkedBlockQueue<>(10);

        IntStream.range(0, 20).forEach(i -> new Thread(() -> {
            try {
                System.out.println(container.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "takeThread-" + i).start());

        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
            try {
                container.put("hello" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "putThread-" + i).start());
    }
}
