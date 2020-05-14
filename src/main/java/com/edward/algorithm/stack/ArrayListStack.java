/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     ArrayListStack
 * CreationDate: 2020/3/23
 * Author edward
 */
package com.edward.algorithm.stack;


import com.edward.algorithm.array.ArrayList;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class ArrayListStack<E> implements Stack<E> {
    private ArrayList<E> arrayList;

    public ArrayListStack() {
        this.arrayList = new ArrayList<>();
    }

    @Override
    public boolean empty() {
        return arrayList.isEmpty();
    }

    @Override
    public E peek() {
        return arrayList.getLast();
    }

    @Override
    public E pop() {
        return arrayList.remove(0);
    }

    @Override
    public void push(E e) {
        arrayList.add(0, e);
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ArrayListStack{");
        sb.append("arrayList=").append(arrayList);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayListStack<Integer> arrayListStack = new ArrayListStack<>();
        for (int i = 0; i < 10; i++) {
            arrayListStack.push(i);
            System.out.println(arrayListStack);
        }
        arrayListStack.pop();
        System.out.println(arrayListStack);
    }
}
