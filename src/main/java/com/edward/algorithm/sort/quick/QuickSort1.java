/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     QuickSort1
 * CreationDate: 2020/4/3
 * Author edward
 */
package com.edward.algorithm.sort.quick;

import com.edward.algorithm.sort.Sort;

import java.util.Random;

/**
 * 单路快排
 *
 * @author edward
 * @see
 * @since
 */
public class QuickSort1<E extends Comparable<E>> implements Sort<E> {
    @Override
    public void sort(E[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private int partition(E[] data, int left, int right) {

        /*
         * 优化：如果此处一直取第一个元素，在一个有序的数组中，可能退化成链表，也就是复杂度会升级到O(n^2),此时如果数据量太大可能会导致stackOverFlow
         * 此处使用的方式是随机选择一个位置
         */
        int random = new Random().nextInt(right - left + 1) + left;
        swap(data, random, left);
        E temp = data[left];
        /*
         *  arr[l+1...j] < v ; arr[j+1...i) > v
         */
        int j = left;
        for (int k = left + 1; k <= right; k++) {
            if (temp.compareTo(data[k]) < 0) {
                j++;
                swap(data, k, j);
            }
        }
        swap(data, left, j);
        return j;
    }

    private void quickSort(E[] data, int left, int right) {
        /*
            优化：此处可以设定一个一个阈值，如果小于这个阈值，可以使用，插入排序，提高性能
         */
//        if (left >= right) {
//            return;
//        }
        if (right -left <= 15) {
            insertSort(data,left,right);
            return;
        }
        int p = partition(data, left, right);
        quickSort(data, left, p - 1);
        quickSort(data, p + 1, right);
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
        Integer[] data = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        new QuickSort1<Integer>().sort(data);
        for (Integer datum : data) {
            System.out.println(datum);
        }
    }
}
