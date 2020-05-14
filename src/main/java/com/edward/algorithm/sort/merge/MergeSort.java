/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     MergeSort
 * CreationDate: 2020/4/2
 * Author edward
 */
package com.edward.algorithm.sort.merge;

import com.edward.algorithm.sort.Sort;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class MergeSort<E extends Comparable<E>> implements Sort<E> {
    @Override
    public void sort(E[] data) {
        mergeSort(data, 0, data.length - 1);
    }

    private void mergeSort(E[] data, int left, int right) {
//        if (left >= right) {
//            return;
//        }
        if(right -left<=16){
            insertSort(data,left,right);
            return;
        }
        int middle = left + (right - left) / 2;
        mergeSort(data, left, middle);
        mergeSort(data, middle + 1, right);
        if (data[middle].compareTo(data[middle + 1]) < 0)
            merge(data, left, middle, right);
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
    private void merge(E[] data, int left, int middle, int right) {
        Object[] aux = new Object[right - left + 1];
        for (int i = left; i <= right; i++) {
            aux[i - left] = data[i];
        }
        int i = left;
        int j = middle + 1;
        for (int k = left; k <= right; k++) {
            if (i > middle) {
                data[k] = (E) aux[j - left];
                j++;
            } else if (j > right) {
                data[k] = (E) aux[i - left];
                i++;
            } else if (((E) aux[i - left]).compareTo((E) aux[j - left]) < 0) {
                data[k] = (E) aux[j - left];
                j++;
            } else {
                data[k] = (E) aux[i - left];
                i++;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        new MergeSort<Integer>().sort(data);
        for (Integer datum : data) {
            System.out.println(datum);
        }
    }
}
