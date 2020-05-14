/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     ArrayListQueue
 * CreationDate: 2020/3/23
 * Author edward
 */
package com.edward.algorithm.queue;


import com.edward.algorithm.array.ArrayList;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class ArrayListQueue<E> implements Queue<E> {

    private ArrayList<E> arrayList;

    public ArrayListQueue() {
        this.arrayList = new ArrayList<>();
    }

    @Override
    public boolean empty() {
        return arrayList.isEmpty();
    }

    @Override
    public E peek() {
        return arrayList.get(size() - 1);
    }

    @Override
    public E dequeue() {
        return arrayList.remove(0);
    }

    @Override
    public void enqueue(E e) {
        arrayList.add(size(), e);
    }

    @Override
    public int size() {
        return arrayList.size();
    }
}
