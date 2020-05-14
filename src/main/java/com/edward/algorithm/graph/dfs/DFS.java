/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     BFS
 * CreationDate: 2020/4/11
 * Author edward
 */
package com.edward.algorithm.graph.dfs;


import com.edward.algorithm.graph.AdjacencyListGraph;
import com.edward.algorithm.graph.Graph;

import java.util.Stack;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class DFS {

    private Graph graph;

    public DFS(Graph graph) {
        this.graph = graph;
    }

    public void dfsByRecursive() {
        boolean[] visited = new boolean[graph.vertex()];
        for (int i = 0; i < graph.vertex(); i++) {
            if (!visited[i]) dfsByRecursive(i, visited);
        }
    }

    private void dfsByRecursive(int vertex, boolean[] visited) {
        System.out.println(vertex);
        visited[vertex] = true;
        for (int ver : graph.adjust(vertex)) {
            if (!visited[ver]) dfsByRecursive(ver,visited);
        }
    }

    public void dfs() {
        boolean[] visited = new boolean[graph.vertex()];
        for (int i = 0; i < graph.vertex(); i++) {
            if (!visited[i]) dfs(i, visited);
        }
    }

    private void dfs(int vertex, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.add(vertex);
        visited[vertex] = true;
        while (!stack.isEmpty()) {
            int current = stack.pop();
            System.out.println(current);
            for (int ver : graph.adjust(current)) {
                if (!visited[ver]) {
                    stack.add(ver);
                    visited[ver] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        Graph graph = Graph.buildGraph(dir+"/src/main/java/com/edward/algorithm/graph/g2.txt", () -> AdjacencyListGraph.class);
        DFS dfs = new DFS(graph);
        dfs.dfsByRecursive();
        System.out.println("==========================");
        dfs.dfs();
    }
}
