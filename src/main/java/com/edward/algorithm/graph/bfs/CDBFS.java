/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     CDBFS
 * CreationDate: 2020/4/12
 * Author edward
 */
package com.edward.algorithm.graph.bfs;


import com.edward.algorithm.graph.AdjacencyListGraph;
import com.edward.algorithm.graph.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class CDBFS {

    private int[] pre;

    private Graph graph;

    public CDBFS(Graph graph) {
        this.graph = graph;
        pre = new int[graph.vertex()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
    }

    public boolean bfs() {
        for (int i = 0; i < graph.vertex(); i++) {
            if (pre[i] == -1) if (bfs(i)) return true;
        }
        return false;
    }

    public boolean bfs(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        pre[vertex] = vertex;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            System.out.println(current);
            for (int ver : graph.adjust(current)) {
                if (pre[ver] == -1) {
                    queue.add(ver);
                    pre[ver] = current;
                } else if (pre[current] != ver) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasCycle() {
        System.out.println(Arrays.toString(pre));
        return bfs();
    }

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        Graph graph = Graph.buildGraph(dir + "/src/main/java/com/edward/algorithm/graph/g2.txt", () -> AdjacencyListGraph.class);
        CDBFS cycleDetection = new CDBFS(graph);
        System.out.println(cycleDetection.hasCycle());
    }

}
