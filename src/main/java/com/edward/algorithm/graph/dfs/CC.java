/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     AdjacencyListGraph
 * CreationDate: 2020/4/11
 * Author edward
 */
package com.edward.algorithm.graph.dfs;


import com.edward.algorithm.graph.AdjacencyListGraph;
import com.edward.algorithm.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 图的连通性问题
 *
 * @author edward
 * @see
 * @since
 */
public class CC {

    private int[] visited;

    private Graph graph;
    /**
     * 图的联通分量个数
     */
    private int count;

    public CC(Graph graph) {
        this.graph = graph;
        visited = new int[graph.vertex()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }
        for (int i = 0; i < graph.vertex(); i++) {
            if (visited[i] == -1) {
                dfs(i, count++);
            }
        }
    }

    private int count() {
        return count;
    }

    public boolean isConnected(int v, int w) {
        graph.validateVertex(v);
        graph.validateVertex(w);
        return visited[v] == visited[w];
    }

    public List<List<Integer>> components() {
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ret.add(new ArrayList<>());
        }
        for (int i = 0; i < visited.length; i++) {
            ret.get(visited[i]).add(i);
        }
        return ret;
    }

    public void dfs(int vertex,int ccId) {
        visited[vertex] = ccId;
        for (int ver : graph.adjust(vertex)) {
            if (visited[ver] == -1) {
                dfs(ver, ccId);
            }
        }
    }

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        Graph graph = Graph.buildGraph("/src/main/java/com/edward/algorithm/graph/g2.txt", () -> AdjacencyListGraph.class);
        CC cc = new CC(graph);
        System.out.println(cc.components());
    }

}
