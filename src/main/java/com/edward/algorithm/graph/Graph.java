/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     Graph
 * CreationDate: 2020/4/9
 * Author edward
 */
package com.edward.algorithm.graph;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public interface Graph {

    /**
     * 图的点
     *
     * @return
     */
    int vertex();

    /**
     * 图的边
     *
     * @return
     */
    int edge();

    /**
     * v与之相连的点
     *
     * @param v
     * @return
     */
    Iterable<Integer> adjust(int v);

    /**
     * 两个点是否相连
     *
     * @param v
     * @param w
     * @return
     */
    boolean hasEdge(int v, int w);

    /**
     * 在v与w之间建立一条边
     *
     * @param v
     * @param w
     */
    void addEdge(int v, int w);

    /**
     * v的度
     *
     * @param v
     * @return
     */
    int degree(int v);

    default void validateVertex(int vertex) {
        if (vertex < 0 || vertex > vertex() - 1) {
            throw new IllegalArgumentException("图中的点不存在");
        }
    }


    static Graph buildGraph(String sourcePath, Supplier<Class<? extends Graph>> supplier) {
        File file = new File(sourcePath);
        try (Scanner scanner = new Scanner(file)) {
            int vertex = scanner.nextInt();
            Graph graph = supplier.get().getConstructor(Integer.class).newInstance(vertex);
            int edge = scanner.nextInt();
            for (int i = 0; i < edge; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                graph.addEdge(a, b);
            }
            return graph;
        } catch (IOException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
