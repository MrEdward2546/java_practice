/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     BiPartitionDetection
 * CreationDate: 2020/4/11
 * Author edward
 */
package com.edward.algorithm.graph.dfs;


import com.edward.algorithm.graph.AdjacencyListGraph;
import com.edward.algorithm.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class BiPartitionDetection {

    private boolean[] visited;

    private Graph graph;

    private int[] colors;

    public BiPartitionDetection(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.vertex()];
        colors = new int[graph.vertex()];

    }

    public boolean dfs(int vertex, int color) {
        visited[vertex] = true;
        colors[vertex] = color;
        for (int ver : graph.adjust(vertex)) {
            if (!visited[ver]) {
                if (!dfs(ver, 1 - color)) {
                    return false;
                }
            } else if (colors[ver] == colors[vertex]) {
                return false;
            }
        }

        return true;
    }

    public boolean isBiPartition() {
        for (int i = 0; i < graph.vertex(); i++) {
            if (!visited[i]) if (!dfs(i, 0)) return false;
        }
        return true;
    }


    public List<List<Integer>> paths() {
        List<List<Integer>> ret = new ArrayList<>();
        if (isBiPartition()) {
            for (int i = 0; i < 2; i++) {
                ret.add(new ArrayList<>());
            }
            for (int i = 0; i < colors.length; i++) {
                ret.get(colors[i]).add(i);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        Graph graph = Graph.buildGraph(dir + "/src/main/java/com/edward/algorithm/graph/biPartitionDetection2.txt", () -> AdjacencyListGraph.class);
        BiPartitionDetection biPartitionDetection = new BiPartitionDetection(graph);
        System.out.println(biPartitionDetection.isBiPartition());
        System.out.println(biPartitionDetection.paths());
        System.out.println("=====================");


        graph = Graph.buildGraph(dir + "/src/main/java/com/edward/algorithm/graph/biPartitionDetection.txt", () -> AdjacencyListGraph.class);
        BiPartitionDetection b2 = new BiPartitionDetection(graph);
        System.out.println(b2.isBiPartition());
        System.out.println(b2.paths());
    }
}
