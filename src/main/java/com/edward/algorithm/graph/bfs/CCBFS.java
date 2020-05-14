/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     CCBFS
 * CreationDate: 2020/4/11
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
public class CCBFS {

    private int[] visited;

    private Graph graph;

    private int count;

    public CCBFS(Graph graph) {
        this.graph = graph;
        visited = new int[graph.vertex()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }
        bfs();
    }

    public void bfs() {
        for (int i = 0; i < graph.vertex(); i++) {
            if (visited[i] == -1) {
                bfs(i, count++);
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
        for (int i = 0; i < graph.vertex(); i++) {
            ret.get(visited[i]).add(i);
        }
        return ret;
    }

    public void bfs(int vertex, int ccId) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        visited[vertex] = ccId;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            System.out.println(current);
            for (int ver : graph.adjust(current)) {
                if (visited[ver] == -1) {
                    queue.add(ver);
                    visited[ver] = ccId;
                }
            }
        }
    }

    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        Graph graph = Graph.buildGraph(dir + "/src/main/java/com/edward/algorithm/graph/g2.txt", () -> AdjacencyListGraph.class);
        CCBFS cc = new CCBFS(graph);
        System.out.println(cc.components());
        System.out.printf("0->6:%s\n", cc.isConnected(0, 6));
        System.out.printf("0->5:%s\n", cc.isConnected(0, 5));
    }

}
