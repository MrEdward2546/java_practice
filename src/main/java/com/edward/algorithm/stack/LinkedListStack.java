/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     LinkedListStack
 * CreationDate: 2020/3/23
 * Author edward
 */
package com.edward.algorithm.stack;


import java.util.LinkedList;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> linkedList;

    public LinkedListStack() {
        this.linkedList = new LinkedList<>();
    }

    @Override
    public boolean empty() {
        return linkedList.isEmpty();
    }

    @Override
    public E peek() {
        return linkedList.get(0);
    }

    @Override
    public E pop() {
        return linkedList.remove(0);
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LinkedListStack{");
        sb.append("linkedList=").append(linkedList);
        sb.append('}');
        return sb.toString();
    }


}
