/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     LinkedListQueue
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
public class LinkedListQueue<E> implements Queue<E> {

    private Node head;

    private Node rear;

    private int size;

    private class Node {
        private E data;
        private Node pre;
        private Node next;

        public Node(E data, Node pre, Node next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }

        public Node(E data) {
            this(data, null, null);
        }
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public E peek() {
        if (head != null) {
            return head.data;
        }
        return null;
    }

    @Override
    public E dequeue() {
        if (head == null) {
            return null;
        }
        Node node = head;
        head = node.next;
        size--;
        return node.data;
    }

    @Override
    public void enqueue(E e) {
        if (rear == null) {
            rear = new Node(e);
            head = rear;
        } else {
            rear.next = new Node(e, rear, null);
            rear = rear.next;
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }
}
