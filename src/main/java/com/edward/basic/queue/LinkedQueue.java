/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     LinkedQueue
 * CreationDate: 2020/5/14
 * Author edward
 */
package com.edward.basic.queue;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class LinkedQueue<T> implements Queue<T> {

    private int size;

    private Node head;
    private Node tail;

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public T peek() {
        if (empty()) {
            return null;
        }
        return head.data;
    }

    @Override
    public T dequeue() {
        if (empty()) {
            return null;
        }
        T temp = head.data;
        head = head.next;
        size--;
        return temp;
    }

    @Override
    public void enqueue(T t) {
        if (head == null) {
            tail = head = new Node(t, null);
        } else {
            tail.next = new Node(t, null);
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    private class Node {
        private final T data;
        private Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }


}
