/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     Queue
 * CreationDate: 2020/3/23
 * Author edward
 */
package com.edward.algorithm.queue;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public interface Queue<E> {

    boolean empty();

    E peek();

    E dequeue();

    void enqueue(E e);

    int size();

}
