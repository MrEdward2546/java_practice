/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     WaterPoll
 * CreationDate: 2020/4/13
 * Author edward
 */
package com.edward.algorithm.water;

import java.util.*;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class WaterPoll {

    private int[] pre;

    private int end = -1;

    public WaterPoll() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] visited = new boolean[100];
        pre = new int[100];
        visited[0] = true;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            int x = current / 10;
            int y = current % 10;
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(5 * 10 + y);
            arrayList.add(x * 10 + 3);
            arrayList.add(y);
            arrayList.add(x * 10);
            int pollX = Math.min(x, 3 - y);
            arrayList.add((x - pollX) * 10 + y + pollX);
            int pollY = Math.min(5 - x, y);
            arrayList.add((x + pollY) * 10 + y - pollY);

            for (int w : arrayList) {
                if (!visited[w]) {
                    queue.add(w);
                    pre[w] = current;
                    visited[w] = true;
                    if (w / 10 == 4 || w % 10 == 4) {
                        end = w;
                        return;
                    }
                }
            }

        }
    }

    public Iterable<Integer> path() {
        List<Integer> list = new ArrayList<>();
        int cur = end;
        while (cur != 0) {
            list.add(cur);
            cur = pre[cur];
        }
        list.add(cur);
        Collections.reverse(list);
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new WaterPoll().path());
    }


}
