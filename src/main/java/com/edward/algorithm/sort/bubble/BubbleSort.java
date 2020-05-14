/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     BubbleSort
 * CreationDate: 2020/3/27
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
public class BubbleSort<E extends Comparable<E>> implements Sort<E> {
    @Override
    public void sort(E[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            boolean flag = false;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j].compareTo(data[i]) > 0) {
                    E temp = data[j];
                    data[j] = data[i];
                    data[i] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }


}
