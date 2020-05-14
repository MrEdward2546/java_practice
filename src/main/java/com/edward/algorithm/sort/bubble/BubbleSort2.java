/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     BubbleSort2
 * CreationDate: 2020/4/2
 * Author edward
 */
package com.edward.algorithm.sort.bubble;

import com.edward.algorithm.sort.Sort;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class BubbleSort2<E extends Comparable<E>> implements Sort<E> {

    @Override
    public void sort(E[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            boolean isSorted = true;
            for (int j = i + 1; j < data.length; j++) {
                if (data[i].compareTo(data[j]) < 0) {
                    E temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                    isSorted = false;
                }
            }
            if(isSorted){
                break;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{1,2,3,4,5,6,7,8,9};
        new BubbleSort2<Integer>().sort(data);
        for (Integer datum : data) {
            System.out.println(datum);
        }
    }
}
