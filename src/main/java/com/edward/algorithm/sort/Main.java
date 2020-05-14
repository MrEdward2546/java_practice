/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     Main
 * CreationDate: 2020/3/27
 * Author edward
 */
package com.edward.algorithm.sort;

import com.edward.algorithm.sort.quick.QuickSort1;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class Main {

    public static void main(String[] args) {
        int numLength = 50000;
        Integer[] data = SortHelper.generateOrder(numLength, 10);
//        for (Integer datum : data) {
//            System.out.println(datum);
//        }
        Integer[] data2 = data.clone();
        Integer[] data3 = data.clone();
        Integer[] data4 = data.clone();

//        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
//        SortHelper.test(data, bubbleSort, "bubble sort");
//        SortHelper.isValid(data);
//
//
//        SelectSort<Integer> selectSort = new SelectSort<>();
//        SortHelper.test(data2, selectSort, "select sort");
//        SortHelper.isValid(data2);
//
//        InsertSort<Integer> insertSort = new InsertSort<>();
//        SortHelper.test(data3, insertSort, "insert sort");
//        SortHelper.isValid(data3);

//        MergeSort2<Integer> mergeSort = new MergeSort2<>();
//        SortHelper.test(data4, mergeSort, "merge sort");
//        SortHelper.isValid(data4);

        QuickSort1<Integer> quickSort1 = new QuickSort1<>();
        SortHelper.test(data2, quickSort1, "quick sort");
        SortHelper.isValid(data2);
    }
}
