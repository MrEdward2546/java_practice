/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     TrieTree
 * CreationDate: 2020/3/30
 * Author edward
 */
package com.edward.algorithm.trie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class TrieTree {

    private Node root;

    private int size;

    public TrieTree() {
        root = new Node();
    }

    public int getSize() {
        return size;
    }

    public void add(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if (node.children.get(word.charAt(i)) == null) {
                node.children.put(word.charAt(i), new Node());
            }
            node = node.children.get(word.charAt(i));
        }
        if(!node.isWord){
            node.isWord = true;
            size++;
        }

    }

    public boolean contain(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (node.children.get(ch) == null) {
                return false;
            }
            node = node.children.get(ch);
        }
        return node.isWord;
    }

    public boolean prefix(String prefix){
        Node node = root;
        for (char ch : prefix.toCharArray()) {
            if (node.children.get(ch) == null) {
                return false;
            }
            node = node.children.get(ch);
        }
        return true;
    }
    class Node {
        private Map<Character, Node> children;
        private boolean isWord;

        public Node(boolean isWord) {
            this.isWord = isWord;
            children = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }

}
