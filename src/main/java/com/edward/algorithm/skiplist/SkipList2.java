/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     SkipList2
 * CreationDate: 2020/5/11
 * Author edward
 */
package com.edward.algorithm.skiplist;

import java.util.Random;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class SkipList2 {

    private SkipEntry head;
    private SkipEntry tail;

    private int size;
    private int height;
    private final Random random = new Random();

    public SkipList2() {
        head = new SkipEntry(SkipEntry.HEAD_KEY, null);
        tail = new SkipEntry(SkipEntry.TAIL_KEY, null);
        head.right = tail;
        tail.left = head;
    }

    private SkipEntry find(String key) {
        SkipEntry node = head;
        while (true) {
            while (!node.right.key.equals(SkipEntry.TAIL_KEY)
                    && node.right.key.compareTo(key) <= 0) {
                node = node.right;
            }
            if (node.down != null) {
                node = node.down;
            } else {
                break;
            }
        }
        return node;
    }

    public Integer get(String key) {
        SkipEntry entry = find(key);
        if (key.equals(entry.key)) {
            return entry.value;
        }
        return null;
    }

    private int size() {
        return size;
    }

    public Integer remove(String key) {
        SkipEntry p = find(key);
        if (!key.equals(p.key)) {
            return null;
        }
        Integer oldValue = p.value;
        SkipEntry q;
        while (p != null) {
            q = p.up;
            p.left.right = p.right;
            p.right.left = p.left;
            p = q;
        }
        return oldValue;
    }

    public void put(String key, Integer value) {
        SkipEntry node = find(key);
        if (node.key.equals(key)) {
            node.value = value;
            return;
        }
        SkipEntry newNode = new SkipEntry(key, value);
        newNode.left = node;
        newNode.right = node.right;
        node.right.left = newNode;
        node.right = newNode;
        int level = 0;
        while (random.nextDouble() < 0.5) {
            if (level >= height) {
                addEmptyLevel();
            }
            while (node.up == null) {
                node = node.left;
            }
            node = node.up;

            SkipEntry entry = new SkipEntry(key, null);
            entry.left = node;
            entry.right = node.right;
            node.right = entry;
            node.right.left = entry;

            entry.down = newNode;
            newNode.up = entry;

            newNode = entry;
            level++;
        }
        size++;
    }

    private void addEmptyLevel() {
        SkipEntry newHead = new SkipEntry(SkipEntry.HEAD_KEY, null);
        SkipEntry newTail = new SkipEntry(SkipEntry.TAIL_KEY, null);

        newHead.right = newTail;
        newTail.left = newHead;

        newHead.down = head;
        newTail.down = tail;

        head.up = newHead;
        head.down = newTail;

        head = newHead;
        tail = newTail;
        height++;

    }

    private static class SkipEntry {

        private static final String HEAD_KEY = "-";
        private static final String TAIL_KEY = "+";
        private SkipEntry up;
        private SkipEntry down;
        private SkipEntry left;
        private SkipEntry right;

        private String key;
        private Integer value;

        public SkipEntry(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
}
