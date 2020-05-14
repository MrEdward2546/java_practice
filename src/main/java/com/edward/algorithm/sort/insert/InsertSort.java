/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     InsertSort
 * CreationDate: 2020/3/27
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
public class InsertSort<E extends Comparable<E>> implements Sort<E> {
    @Override
    public void sort(E[] data) {
        sort1(data);
    }

    public void sort1(E[] data) {
        for (int i = 1; i < data.length; i++) {
            for (int j = i; j > 0 && data[j].compareTo(data[j - 1]) > 0; j--) {
                E temp = data[j];
                data[j] = data[j - 1];
                data[j - 1] = temp;
            }
        }
    }

    public void sort2(E[] data) {
        for (int i = 1; i < data.length; i++) {
            E e = data[i];
            int j;
            for (j = i; j > 0 && data[j].compareTo(data[j - 1]) > 0; j--) {
                data[j - 1] = data[j];
            }
            data[j] = e;
        }
    }

    public void insertSort(E[]  data,int left,int right){
        for (int i = left+1; i <= right; i++) {
            E e = data[i];
            int j;
            for (j = i; j > 0 && data[j].compareTo(data[j - 1]) > 0; j--) {
                data[j - 1] = data[j];
            }
            data[j] = e;
        }
    }

    public static void main(String[] args) {
        int numLength = 50000;
//        Integer[] data = SortHelper.generateRandom(numLength, 0, 1000);
        InsertSort<Integer> insertSort = new InsertSort<>();
        insertSort.sort(new Integer[]{1, 2,3});
//        System.out.println(Arrays.toString(data));
//        SortHelper.test(data, insertSort, "insert sort");
//        SortHelper.isValid(data);
    }
}
