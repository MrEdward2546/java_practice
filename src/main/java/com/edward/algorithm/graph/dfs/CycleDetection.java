/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     CycleDetection
 * CreationDate: 2020/4/11
 * Author edward
 */
package com.edward.algorithm.graph.dfs;


import com.edward.algorithm.graph.AdjacencyListGraph;
import com.edward.algorithm.graph.Graph;

/**
 * 无线图环检测
 *
 * @author edward
 * @see
 * @since
 */
public class CycleDetection {

    private Graph graph;

    private boolean[] visited;

    public CycleDetection(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.vertex()];
    }

    public boolean dfs(int vertex, int parent) {
        visited[vertex] = true;
        for (int ver : graph.adjust(vertex)) {
            if (!visited[ver]) {
                if (dfs(ver, vertex)) return true;
            } else if (ver != parent) {
                return true;
            }
        }
        return false;
    }


    public boolean hasCycle() {
        for (int i = 0; i < graph.vertex(); i++) {
            if (!visited[i]) return dfs(i, i);
        }
        return false;
    }

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        Graph graph = Graph.buildGraph(dir+"/src/main/java/com/edward/algorithm/graph/g2.txt", () -> AdjacencyListGraph.class);
        CycleDetection cycleDetection = new CycleDetection(graph);
        System.out.println(cycleDetection.hasCycle());
    }
}
