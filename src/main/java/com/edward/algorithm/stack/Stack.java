/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     Stack
 * CreationDate: 2020/3/23
 * Author edward
 */
package com.edward.algorithm.stack;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public interface Stack<E> {

    boolean empty();

    E peek();

    E pop();

    void push(E e);

    int size();
}
