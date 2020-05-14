/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     SelectSort
 * CreationDate: 2020/3/27
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
public class SelectSort<E extends Comparable<E>> implements Sort<E> {
    @Override
    public void sort(E[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int max = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[max].compareTo(data[j]) < 0) {
                    max = j;
                }
            }
            if (i != max) {
                E temp = data[i];
                data[i] = data[max];
                data[max] = temp;
            }
        }

    }

}
