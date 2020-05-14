/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     List
 * CreationDate: 2020/3/23
 * Author edward
 */
package com.edward.algorithm;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public interface List<E> {

    int size();

    E get(int index);

    int capacity();

    void add(E e);

    void add(int index, E e);

    boolean isEmpty();

    boolean contains(E e);

    int remove(E e);

    E remove(int index);

    int find(E e);

}
