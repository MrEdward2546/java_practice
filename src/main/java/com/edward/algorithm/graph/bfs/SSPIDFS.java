/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     SSPIDFS
 * CreationDate: 2020/4/12
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
public class SSPIDFS {

    private Graph graph;

    private int[] pre;

    private int source;
    private int target;

    public SSPIDFS(Graph graph, int source, int target) {
        pre = new int[graph.vertex()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        this.source = source;
        this.target = target;
        this.graph = graph;
        bfs(source);
    }

    public boolean bfs() {
        for (int i = 0; i < graph.vertex(); i++) {
            if (pre[i] != -1) if (bfs(i)) return true;
        }
        return false;
    }

    public boolean bfs(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vertex);
        pre[vertex] = vertex;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            for (int ver : graph.adjust(current)) {
                if (pre[ver] == -1) {
                    queue.add(ver);
                    pre[ver] = current;
                } else if (ver == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isConnected() {
        graph.validateVertex(this.target);
        return pre[target] != -1;
    }

    public List<Integer> path() {
        graph.validateVertex(this.target);
        List<Integer> ret = new ArrayList<>();
        if (!isConnected()) return ret;
        int cur = target;
        while (cur != source) {
            ret.add(cur);
            cur = pre[cur];
        }
        ret.add(cur);
        Collections.reverse(ret);
        return ret;
    }


    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        Graph graph = Graph.buildGraph(dir + "/src/main/java/com/edward/algorithm/graph/g2.txt", () -> AdjacencyListGraph.class);
        SSPIDFS singleSourcePath = new SSPIDFS(graph, 0, 6);
        System.out.printf("0->6:%s\n", singleSourcePath.path());

        System.out.println("===================");
        SSPIDFS s1 = new SSPIDFS(graph, 0, 1);
        System.out.printf("0->1:%s\n", s1.path());
        System.out.println("==================");
        SSPIDFS s2 = new SSPIDFS(graph, 0, 5);
        System.out.printf("0->5:%s\n", s2.path());
    }
}
