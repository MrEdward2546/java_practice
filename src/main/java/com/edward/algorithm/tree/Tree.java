/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     Tree
 * CreationDate: 2020/3/25
 * Author edward
 */
package com.edward.algorithm.tree;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public interface Tree<K extends Comparable<K>, V> {

    int size();

    boolean contain(K key);

    void add(K key, V value);

    V get(K key);

    boolean isEmpty();

    V remove(K key);

    V findMax();

    V findMin();

    void preOrderTraversal();

    void inOrderTraversal();

    void postOrderTraversal();

    void preOrderTraversalRecursive();

    void inOrderTraversalRecursive();

    void postOrderTraversalRecursive();

    void levelTraversal();

}
