/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     BPDDFS
 * CreationDate: 2020/4/12
 * Author edward
 */
package com.edward.algorithm.graph.bfs;


import com.edward.algorithm.graph.AdjacencyListGraph;
import com.edward.algorithm.graph.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class BPDDFS {


    private boolean[] visited;

    private Graph graph;

    private int[] colors;

    private boolean isBiPartition;

    public BPDDFS(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.vertex()];
        colors = new int[graph.vertex()];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = -1;
        }
    }

    public void bfs() {
        for (int i = 0; i < graph.vertex(); i++) {
            if (!visited[i]) bfs(i);
        }
    }

    public boolean isBiPartition() {
        for (int i = 0; i < graph.vertex(); i++) {
            if (!visited[i]) if (!bfs(i)) return false;
        }
        isBiPartition = true;
        return true;
    }


    public List<List<Integer>> paths() {
        List<List<Integer>> ret = new ArrayList<>();
        if (isBiPartition) {
            for (int i = 0; i < 2; i++) {
                ret.add(new ArrayList<>());
            }
            for (int i = 0; i < colors.length; i++) {
                ret.get(colors[i]).add(i);
            }
        }
        return ret;
    }

    public boolean bfs(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        visited[vertex] = true;
        colors[vertex] = 0;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            for (int ver : graph.adjust(current)) {
                if (!visited[ver]) {
                    queue.add(ver);
                    visited[ver] = true;
                    colors[ver] = 1 - colors[current];
                } else if (colors[ver] == colors[current]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        Graph graph = Graph.buildGraph(dir + "/src/main/java/com/edward/algorithm/graph/biPartitionDetection2.txt", () -> AdjacencyListGraph.class);
        BPDDFS biPartitionDetection = new BPDDFS(graph);
        System.out.println(biPartitionDetection.isBiPartition());
        System.out.println(biPartitionDetection.paths());
        System.out.println("=====================");


        graph = Graph.buildGraph(dir + "/src/main/java/com/edward/algorithm/graph/biPartitionDetection.txt", () -> AdjacencyListGraph.class);
        BPDDFS b2 = new BPDDFS(graph);
        System.out.println(b2.isBiPartition());
        System.out.println(b2.paths());
    }
}
