/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     MaxHeap2
 * CreationDate: 2020/4/7
 * Author edward
 */
package com.edward.algorithm.heap;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class MaxHeap2 {

    private int[] data;

    private int size;

    public int size() {
        return size;
    }

    public MaxHeap2(int capacity) {
        data = new int[capacity];
    }

    public MaxHeap2(int[] ele) {
        data = new int[ele.length];
        for (int i = 0; i < ele.length; i++) {
            data[i] = ele[i];
        }
        size = ele.length;
        for (int i = (size - 1) / 2; i >= 0; i--) {
            shiftDown(i);
        }
    }

    private int left(int parent) {
        return parent * 2 + 1;
    }

    private int right(int parent) {
        return parent * 2 + 2;
    }

    private int parent(int child) {
        return (child - 1) / 2;
    }

    public boolean empty() {
        return size == 0;
    }

    public void add(int ele) {
        data[size++] = ele;
        shiftUp(size - 1);
    }

    private void shiftUp(int i) {
        while (i > 0 && data[i] > data[parent(i)]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public int extractMax() {
        int max = data[0];
        size--;
        data[0] = data[size];
        shiftDown(0);
        return max;
    }

    private void shiftDown(int i) {
        while (left(i) <= size) {
            int j = left(i);
            if (j + 1 < size && data[j + 1] > data[j]) {
                j++;
            }
            if (data[j] <= data[i]) {
                break;
            }
            swap(j, i);
            i = j;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[10];
        for (int i = 0; i < 10; i++) {
            data[i] = i;
        }
        MaxHeap2 maxHeap2 = new MaxHeap2(data);
        while (!maxHeap2.empty()) {
            System.out.println(maxHeap2.extractMax());
        }

    }

}
