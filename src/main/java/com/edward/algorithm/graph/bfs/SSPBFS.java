/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     SSPBFS
 * CreationDate: 2020/4/11
 * Author edward
 */
package com.edward.algorithm.graph.bfs;


import com.edward.algorithm.graph.AdjacencyListGraph;
import com.edward.algorithm.graph.Graph;

import java.util.*;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class SSPBFS {

    private Graph graph;

    private int[] visited;

    private int source;

    private int[] dis;

    public SSPBFS(Graph graph, int source) {
        this.graph = graph;
        visited = new int[graph.vertex()];
        dis = new int[graph.vertex()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
            dis[i] = -1;
        }
        this.source = source;
        bfs();
    }

    public void bfs() {
        for (int i = 0; i < graph.vertex(); i++) {
            if (visited[i] == -1) bfs(i, i);
        }
    }

    public void bfs(int vertex, int parent) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        visited[vertex] = parent;
        dis[vertex] = 0;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            for (int ver : graph.adjust(current)) {
                if (visited[ver] == -1) {
                    queue.add(ver);
                    visited[ver] = vertex;
                    dis[ver] = dis[vertex] + 1;
                }
            }
        }
    }

    private int dis(int target) {
        graph.validateVertex(target);
        return dis[target];
    }

    public List<Integer> path(int target) {
        graph.validateVertex(target);
        graph.validateVertex(source);

        List<Integer> ret = new ArrayList<>();
        if (!isConnected(target)) {
            return ret;
        }
        int current = target;
        while (current != source) {
            ret.add(current);
            current = visited[current];
        }
        ret.add(current);
        Collections.reverse(ret);
        return ret;
    }

    public boolean isConnected(int target) {
        graph.validateVertex(target);
        return visited[target] != -1;
    }


    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        Graph graph = Graph.buildGraph(dir+"/src/main/java/com/edward/algorithm/graph/g2.txt", () -> AdjacencyListGraph.class);
        SSPBFS sspbfs = new SSPBFS(graph, 0);
        System.out.printf("0->6:%s", sspbfs.path(6));

    }
}
