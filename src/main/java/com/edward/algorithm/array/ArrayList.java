/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     ArrayList
 * CreationDate: 2020/3/23
 * Author edward
 */
package com.edward.algorithm.array;


import com.edward.algorithm.List;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class ArrayList<E> implements List<E> {

    private E[] elements;
    private int size;

    public ArrayList(int capacity) {
        elements = (E[]) new Object[capacity];
        size = 0;

    }

    public ArrayList() {
        this(10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("索引越界");
        }
        return elements[index];
    }

    @Override
    public int capacity() {
        return elements.length;
    }

    @Override
    public void add(E e) {
        add(size, e);
    }

    @Override
    public void add(int index, E e) {
        if (size >= elements.length) {
            resize(2 * elements.length);
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("数组越界");
        }

        for (int i = size - 1; i >= index; i--)
            elements[i + 1] = elements[i];

        elements[index] = e;

        size++;

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E e) {
        return find(e) != -1;
    }

    @Override
    public int remove(E e) {
        int index = find(e);
        if (index == -1) {
            return index;
        }
        remove(index);
        return index;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("数组越界");
        }
        E ret = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[size - 1] = null;
        size--;
        if (size == elements.length / 4 && elements.length / 2 != 0) {
            resize(elements.length / 2);
        }
        return ret;
    }

    @Override
    public int find(E e) {
        for (int i = 0; i < elements.length; i++) {
            if (e.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = elements[i];
        }
        elements = newData;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ArrayList{");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public E getFirst() {
        return get(0);
    }

    public void swap(int i, int j) {

        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Index is illegal.");

        E t = elements[i];
        elements[i] = elements[j];
        elements[j] = t;
    }

    public E getLast() {
        return get(size - 1);
    }

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
            System.out.println(arrayList);
        }
        arrayList.add(2, 100);
        System.out.println(arrayList);
        arrayList.remove(3);
        System.out.println(arrayList);
//        linkedList.remove(Integer.valueOf(9));
//        System.out.println(linkedList);
        arrayList.remove(Integer.valueOf(9));
        System.out.println(arrayList);
    }
}
