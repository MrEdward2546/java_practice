/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     AdjacencyListGraph
 * CreationDate: 2020/4/11
 * Author edward
 */
package com.edward.algorithm.graph;

import java.util.Optional;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class AdjacencyListGraph implements Graph {

    private int vertex;
    private int edge;
    private TreeMap<Integer, TreeSet<Integer>> graph;

    public AdjacencyListGraph(Integer vertex) {
        graph = new TreeMap<>();
        this.vertex = vertex;
    }

    @Override
    public int vertex() {
        return vertex;
    }

    @Override
    public int edge() {
        return edge;
    }

    @Override
    public Iterable<Integer> adjust(int v) {
        validateVertex(v);
        return Optional.ofNullable(graph.get(v)).orElse(new TreeSet<>());
    }

    @Override
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return Optional.ofNullable(graph.get(v)).orElse(new TreeSet<>()).contains(w);
    }

    @Override
    public void addEdge(int v, int w) {
        if (v == w) {
            return;
        }
        if (graph.get(v) != null && graph.get(v).contains(w)) {
            return;
        }
        if (graph.get(v) == null) {
            TreeSet<Integer> set = new TreeSet<>();
            set.add(w);
            graph.put(v, set);
        } else {
            graph.get(v).add(w);
        }
        if (graph.get(w) == null) {
            TreeSet<Integer> set = new TreeSet<>();
            set.add(v);
            graph.put(w, set);
        } else {
            graph.get(w).add(v);
        }
        edge++;
    }

    @Override
    public int degree(int v) {
        validateVertex(v);
        return Optional.ofNullable(graph.get(v)).orElse(new TreeSet<>()).size();
    }


}
