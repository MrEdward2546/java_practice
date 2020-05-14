/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     BFS
 * CreationDate: 2020/4/11
 * Author edward
 */
package com.edward.algorithm.graph.bfs;


import com.edward.algorithm.graph.AdjacencyListGraph;
import com.edward.algorithm.graph.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class BFS {

    private boolean[] visited;

    private Graph graph;

    public BFS(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.vertex()];
    }

    public void bfs() {
        for (int i = 0; i < graph.vertex(); i++) {
            if (!visited[i]) bfs(i);
        }
    }

    public void bfs(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        visited[vertex] = true;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            System.out.println(current);
            for (int ver : graph.adjust(current)) {
                if (!visited[ver]) {
                    queue.add(ver);
                    visited[ver] = true;
                }
            }
        }
    }


    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        Graph graph = Graph.buildGraph(dir + "/src/main/java/com/edward/algorithm/graph/g2.txt", () -> AdjacencyListGraph.class);
        BFS bfs = new BFS(graph);
        bfs.bfs();
    }
}
