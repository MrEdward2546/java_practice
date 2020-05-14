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
public class SingleSourcePathImprove {

    private Graph graph;

    private int[] pre;

    private int source;
    private int target;

    public SingleSourcePathImprove(Graph graph, int source, int target) {
        pre = new int[graph.vertex()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        this.source = source;
        this.target = target;
        this.graph = graph;
        dfs(source, source);
    }

    private boolean dfs(int v, int parent) {
        pre[v] = parent;
        if (v == this.target) return true;
        for (int vertex : graph.adjust(v)) {
            if (pre[vertex] == -1) {
                if (dfs(vertex, v)) return true;
            }
        }
        return false;
    }

    public boolean isConnected(int target) {
        validate(target);
        return pre[target] != -1;
    }

    public List<Integer> path() {
        validate(this.target);
        List<Integer> ret = new ArrayList<>();
        if (!isConnected(this.target)) return ret;
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
        SingleSourcePathImprove singleSourcePath = new SingleSourcePathImprove(graph, 0, 6);
        System.out.printf("0->6:%s\n", singleSourcePath.path());

        System.out.println("===================");
        SingleSourcePathImprove s1 = new SingleSourcePathImprove(graph, 0, 1);
        System.out.printf("0->1:%s\n", s1.path());
        System.out.println("==================");
        SingleSourcePathImprove s2 = new SingleSourcePathImprove(graph, 0, 5);
        System.out.printf("0->5:%s\n", s2.path());
    }

}
