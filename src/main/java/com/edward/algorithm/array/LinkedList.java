/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     LinkedList
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
public class LinkedList<E> implements List<E> {

    private int size;
    private Node dummyHeader;

    public LinkedList() {
        dummyHeader = new Node(null);
    }

    private class Node {
        private E e;
        private Node next;
        private Node pre;

        public Node(E e, Node next, Node pre) {
            this.e = e;
            this.next = next;
            this.pre = pre;
        }

        public Node(E e) {
            this(e, null, null);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("数组越界");
        }
        Node node = dummyHeader.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.e;
    }

    @Override
    public int capacity() {
        return Integer.MIN_VALUE;
    }

    @Override
    public void add(E e) {
        addLast(e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("数组越界");
        }
        Node node = dummyHeader;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        node.next = new Node(e, node.next, node);
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E e) {
        Node node = dummyHeader;
        while (node.next != null) {
            node = node.next;
            if (e.equals(node.e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int remove(E e) {
        Node node = dummyHeader;
        int i = 0;
        while (node.next != null) {
            node = node.next;
            if (e.equals(node.e)) {
                node.pre.next = node.next;
                if (node.next != null) {
                    node.next.pre = node.pre;
                    node.next = null;
                    node.pre = null;
                }
                size--;
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("数组越界");
        }
        Node node = dummyHeader;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        E ret = node.next.e;
        node.next = node.next.next;
        if (node.next != null) {
            node.next.pre = node;
        }
        size--;
        return ret;
    }

    @Override
    public int find(E e) {
        Node node = dummyHeader;
        int i = 0;
        while (node.next != null) {
            node = node.next;
            if (e.equals(node.e)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LinkedList{");
        sb.append("size=").append(size).append(",[");
        Node node = dummyHeader;
        for (int i = 0; i < size; i++) {
            node = node.next;
            sb.append(node.e);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]}");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
//        for (int i = 0; i < 10; i++) {
//            linkedList.add(i);
//            System.out.println(linkedList);
//        }
        linkedList.add(100);
        System.out.println(linkedList);
        linkedList.remove(0);
        System.out.println(linkedList);
//        linkedList.remove(Integer.valueOf(9));
//        System.out.println(linkedList);
//        linkedList.remove(Integer.valueOf(9));
//        System.out.println(linkedList);
    }
}
