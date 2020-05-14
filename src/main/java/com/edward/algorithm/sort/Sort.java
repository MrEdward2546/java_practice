/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     Sort
 * CreationDate: 2020/3/27
 * Author edward
 */
package com.edward.algorithm.sort;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public interface Sort<E extends Comparable<E>> {

    void sort(E[] data);

    default void swap(E[] data, int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
