/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     QuickSort2
 * CreationDate: 2020/4/3
 * Author edward
 */
package com.edward.algorithm.sort.quick;

import com.edward.algorithm.sort.Sort;

import java.util.Random;

/**
 * 双路快排
 * 基本思路：
 * 返回p, 使得arr[l...p-1] <= arr[p] ; arr[p+1...r] >= arr[p]
 *
 * @author edward
 * @see
 * @since
 */
public class QuickSort2<E extends Comparable<E>> implements Sort<E> {
    @Override
    public void sort(E[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private void insertSort(E[] data, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            E e = data[i];
            int j;
            for (j = i; j > 0 && data[j-1].compareTo(e) < 0; j--) {
                data[j] = data[j-1];
            }
            data[j] = e;
        }
    }

    private int partition(E[] data, int left, int right) {
        /*
         * 优化：如果此处一直取第一个元素，在一个有序的数组中，可能退化成链表，也就是复杂度会升级到O(n^2),此时如果数据量太大可能会导致stackOverFlow
         * 此处使用的方式是随机选择一个位置
         */
        int random = new Random().nextInt(right - left + 1) + left;
        swap(data, random, left);
        E temp = data[left];
        int i = left + 1, j = right;
        while (true) {
            while (i <= right && data[i].compareTo(temp) > 0)
                i++;

            while (j >= left + 1 && data[j].compareTo(temp) < 0)
                j--;

            if (i > j)
                break;

            swap(data, i, j);
            i++;
            j--;
        }
        swap(data, left, j);

        return j;
    }

    private void quickSort(E[] data, int left, int right) {
        // 对于小规模数组, 使用插入排序进行优化
        if (right <= left) {
//            insertSort(data, left, right);
            return;
        }

        // 调用双路快速排序的partition
        int p = partition(data, left, right);
        quickSort(data, left, p - 1);
        quickSort(data, p + 1, right);
    }
    public static void main(String[] args) {
        Integer[] data = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        new QuickSort2<Integer>().sort(data);
        for (Integer datum : data) {
            System.out.println(datum);
        }
    }
}
