/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     LoopQueue
 * CreationDate: 2020/3/23
 * Author edward
 */
package com.edward.algorithm.queue;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;

    private int front;

    private int rear;

    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public boolean empty() {
        return front == rear;
    }

    @Override
    public E peek() {
        if (empty())
            throw new IllegalArgumentException("Queue is empty.");

        return data[front];
    }

    @Override
    public E dequeue() {

        if (empty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        E e = data[front];
        front = (front + 1) % data.length;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);

        size--;
        return e;
    }

    private int getCapacity() {
        return data.length - 1;
    }

    private void resize(int newCapacity) {
        E[] es = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            es[i] = data[i];
        }
        data = es;
    }

    @Override
    public void enqueue(E e) {
        if ((rear + 1) % data.length == front)
            resize(getCapacity() * 2);

        data[rear] = e;
        rear = (rear + 1) % data.length;
        size++;

    }

    @Override
    public int size() {
        return size;
    }
}
