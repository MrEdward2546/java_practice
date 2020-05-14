/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     SelectSort2
 * CreationDate: 2020/4/2
 * Author edward
 */
package com.edward.algorithm.sort.select;

import com.edward.algorithm.sort.Sort;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class SelectSort2<E extends Comparable<E>> implements Sort<E> {
    @Override
    public void sort(E[] data) {
        for (int i = 0; i < data.length; i++) {
            int max = findMax(data,i);
            if(max != i){
                E temp = data[i];
                data[i] = data[max];
                data[max] = temp;
            }
        }
    }

    private int findMax(E[] data, int index) {
        int ret = index;
        for (int i = index + 1; i < data.length; i++) {
            if (data[i].compareTo(data[index]) > 0) {
                ret = i;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        new SelectSort2<Integer>().sort(data);
        for (Integer datum : data) {
            System.out.println(datum);
        }
    }
}
