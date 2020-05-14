/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     SingleSourcePath
 * CreationDate: 2020/4/11
 * Author edward
 */
package com.edward.algorithm.graph.dfs;

import com.edward.algorithm.graph.AdjacencyListGraph;
import com.edward.algorithm.graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class SingleSourcePath {

    private Graph graph;

    private int[] pre;

    private int source;

    public SingleSourcePath(Graph graph, int source) {
        pre = new int[graph.vertex()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        this.source = source;
        this.graph = graph;
        dfs(source, source);
    }

    private void dfs(int v, int parent) {
        pre[v] = parent;
        for (int vertex : graph.adjust(v)) {
            if (pre[vertex] == -1) {
                dfs(vertex, v);
            }
        }
    }

    public boolean isConnected(int target) {
        validate(target);
        return pre[target] != -1;
    }

    public List<Integer> path(int target) {
        validate(target);
        List<Integer> ret = new ArrayList<>();
        if (!isConnected(target)) return ret;
        int cur = target;
        while (cur != source) {
            ret.add(cur);
            cur = pre[cur];
        }
        ret.add(cur);
        Collections.reverse(ret);
        return ret;
    }

    private void validate(int vertex) {
        if (vertex < 0 || vertex >= graph.vertex()) {
            throw new IllegalArgumentException("vertex is not exist");
        }
    }

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        Graph graph = Graph.buildGraph(dir + "/src/main/java/com/edward/algorithm/graph/g2.txt", () -> AdjacencyListGraph.class);
        SingleSourcePath singleSourcePath = new SingleSourcePath(graph, 0);
        System.out.printf("0->6:%s", singleSourcePath.path(6));
    }

}
