/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     InsertSort2
 * CreationDate: 2020/4/2
 * Author edward
 */
package com.edward.algorithm.sort.insert;

import com.edward.algorithm.sort.Sort;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class InsertSort2<E extends Comparable<E>> implements Sort<E> {

    public static void main(String[] args) {
        Integer[] data = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        new InsertSort2<Integer>().sort(data);
        for (Integer datum : data) {
            System.out.println(datum);
        }
    }

    @Override
    public void sort(E[] data) {
        sort2(data);
    }

    private void sort1(E[] data) {
        for (int i = 1; i < data.length; i++) {
            for (int j = i; j > 0 && data[j].compareTo(data[j - 1]) > 0; j--) {
                E temp = data[j];
                data[j] = data[j - 1];
                data[j - 1] = temp;
            }
        }
    }

    private void sort2(E[] data) {
        for (int i = 1; i < data.length; i++) {
            E temp = data[i];
            int j = i;
            for (; j > 0 && data[j - 1].compareTo(temp) < 0; j--) {
                data[j] = data[j-1];
            }
            data[j] = temp;
        }
    }
}
