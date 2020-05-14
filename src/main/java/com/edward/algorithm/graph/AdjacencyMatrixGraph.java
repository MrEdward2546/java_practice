/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     AdjaceListGraph
 * CreationDate: 2020/4/9
 * Author edward
 */
package com.edward.algorithm.graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * 图的实现邻接矩阵
 *
 * @author edward
 * @see
 * @since
 */
public class AdjacencyMatrixGraph implements Graph {

    private int[][] graph;

    private int edge;

    private int vertex;

    private List<Integer> vertexs;

    private boolean[] visited;

    public AdjacencyMatrixGraph(int v) {
        graph = new int[v][v];
        this.vertex = v;
        this.visited = new boolean[v];
    }

    public void setVertex(int vertex) {
        graph = new int[vertex][vertex];
        this.vertex = vertex;
        this.visited = new boolean[vertex];
    }

    /**
     * 图的点
     *
     * @return
     */
    @Override
    public int vertex() {
        return vertex;
    }

    /**
     * 图的边
     *
     * @return
     */
    @Override
    public int edge() {
        return edge;
    }

    /**
     * v与之相连的点
     *
     * @param v
     * @return
     */
    @Override
    public Iterable<Integer> adjust(int v) {
        validateVertex(v);
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < vertex; i++) {
            if (graph[v][i] == 1) {
                ret.add(i);
            }
        }
        return ret;
    }

    /**
     * 两个点是否相连
     *
     * @param v
     * @param w
     * @return
     */
    @Override
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        return graph[v][w] == 1;
    }

    /**
     * 在v与w之间建立一条边
     *
     * @param v
     * @param w
     */
    @Override
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        graph[v][w] = 1;
        graph[w][v] = 1;
        edge++;
    }

    /**
     * v的度
     *
     * @param v
     * @return
     */
    @Override
    public int degree(int v) {
        validateVertex(v);
        int ret = 0;
        for (int i = 0; i < vertex; i++) {
            if (graph[v][i] == 1) {
                ret++;
            }
        }
        return ret;
    }



}
