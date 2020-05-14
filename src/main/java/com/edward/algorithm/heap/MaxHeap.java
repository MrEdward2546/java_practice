/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     MaxHeap
 * CreationDate: 2020/3/25
 * Author edward
 */
package com.edward.algorithm.heap;


import com.edward.algorithm.array.ArrayList;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class MaxHeap<E extends Comparable<E>> {

    private final ArrayList<E> arrayList;

    public MaxHeap() {
        arrayList = new ArrayList<>();
    }

    public MaxHeap(int capacity) {
        arrayList = new ArrayList<>(capacity);
    }


    public int size() {
        return arrayList.size();
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    private int parent(int key) {
        if (key == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (key - 1) / 2;

    }

    private int left(int key) {
        return 2 * key + 1;
    }

    private int right(int key) {
        return 2 * key + 2;
    }

    public void add(E e) {
        arrayList.add(e);
        siftUp(size() - 1);
    }

    private void siftUp(int index) {
        while (index > 0 && arrayList.get(parent(index)).compareTo(arrayList.get(index)) < 0) {
            arrayList.swap(index, parent(index));
            index = parent(index);
        }
    }

    public E findMax() {
        if (arrayList.size() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return arrayList.get(0);

    }

    public E extractMax() {

        E ret = findMax();

        arrayList.swap(0, arrayList.size() - 1);
        arrayList.remove(size() - 1);
        siftDown(0);

        return ret;

    }

    private void siftDown(int i) {
        while (left(i) < arrayList.size()) {
            int j = left(i);
            if (j + 1 < arrayList.size() &&
                    arrayList.get(j + 1).compareTo(arrayList.get(j)) > 0)
                j++;

            if (arrayList.get(i).compareTo(arrayList.get(j)) >= 0)
                break;

            arrayList.swap(i, j);
            i = j;
        }

    }

    public E replace(E e) {

        E ret = findMax();
        arrayList.add(0, e);
        siftDown(0);
        return ret;

    }

}
