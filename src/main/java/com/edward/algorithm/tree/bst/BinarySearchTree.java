/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     BinarySearchTree
 * CreationDate: 2020/3/25
 * Author edward
 */
package com.edward.algorithm.tree.bst;

import com.edward.algorithm.tree.FileOperation;
import com.edward.algorithm.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class BinarySearchTree<K extends Comparable<K>, V> implements Tree<K, V> {

    private Node root;

    private int size;

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean contain(K key) {
        return get(key) != null;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node parent, K key, V value) {
        if (parent == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(parent.key) > 0) {
            parent.right = add(parent.right, key, value);
        } else if (key.compareTo(parent.key) < 0) {
            parent.left = add(parent.left, key, value);
        } else {
            parent.value = value;
        }
        return parent;
    }

    @Override
    public V get(K key) {
        Node node = get(root, key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    private Node get(Node parent, K key) {
        if (parent == null) {
            return null;
        }
        if (key.compareTo(parent.key) > 0) {
            return get(parent.right, key);
        } else if (key.compareTo(parent.key) < 0) {
            return get(parent.left, key);
        } else {
            return parent;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else {
            if (node.left == null) {
                Node right = node.right;
                node.right = null;
                return right;
            }
            if (node.right == null) {
                Node left = node.left;
                node.left = null;
                return left;
            }
            Node min = findMin(node.right);
            min.right = removeMin(node.right);
            min.left = node.left;
            node.left = node.right = null;
            return min;
        }
    }

    @Override
    public V findMax() {
        Node node = findMax(root);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    private Node removeMin(Node node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.right = removeMin(node.left);
        return node;
    }

    private Node removeMax(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMin(node.right);
        return node;
    }

    private Node findMax(Node node) {
        if (node == null) {
            return null;
        }
        Node temp = node;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }

    private Node findMin(Node node) {
        if (node == null) {
            return null;
        }
        Node temp = node;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }


    @Override
    public V findMin() {
        Node node = findMin(root);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    @Override
    public void preOrderTraversal() {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                System.out.println(node.value);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
    }


    @Override
    public void inOrderTraversal() {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            System.out.println(node.value);
            node = node.right;
        }
    }

    private void inOrderTraversal(Node node) {
        if (node.left != null) {
            preOrderTraversal(node.left);
        }
        System.out.println(node.value);
        if (node.right != null) {
            preOrderTraversal(node.right);
        }
    }

    @Override
    public void postOrderTraversal() {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        Node lastOccur = null;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.peek();
            if (node.right == null || lastOccur == node.right) {
                stack.pop();
                System.out.println(node.value);
                lastOccur = node;
                node = null;
            } else {
                node = node.right;
            }

        }
    }

    private void preOrderTraversal(Node node) {
        System.out.println(node.value);
        if (node.left != null) {
            preOrderTraversal(node.left);
        }
        if (node.right != null) {
            preOrderTraversal(node.right);
        }
    }

    private void postOrderTraversal(Node node) {
        if (node.left != null) {
            postOrderTraversal(node.left);
        }
        if (node.right != null) {
            postOrderTraversal(node.right);
        }
        System.out.println(node.value);
    }

    @Override
    public void preOrderTraversalRecursive() {
        preOrderTraversal(root);
    }

    @Override
    public void inOrderTraversalRecursive() {
        inOrderTraversal(root);
    }

    @Override
    public void postOrderTraversalRecursive() {
        postOrderTraversal(root);
    }

    @Override
    public void levelTraversal() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.println(node.value);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("src/main/resources/pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BinarySearchTree<String, Integer> map = new BinarySearchTree<>();
            for (String word : words) {
                if (map.contain(word))
                    map.add(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.size());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

        }

        System.out.println();

        BinarySearchTree<Integer, Integer> map = new BinarySearchTree<>();
        map.add(11, 11);
        map.add(9, 9);
        map.add(13, 13);
        map.add(8, 8);
        map.add(10, 10);
        map.add(12, 12);
        map.add(14, 14);


        map.inOrderTraversal();
        System.out.println("===========================");
        map.inOrderTraversalRecursive();
        System.out.println("==========pre order========");
        map.preOrderTraversal();
        System.out.println("==========pre order========");
        System.out.println("==========post order========");
        map.postOrderTraversal();


    }
}
