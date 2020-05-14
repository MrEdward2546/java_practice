/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     Test
 * CreationDate: 2020/4/13
 * Author edward
 */
package com.edward.algorithm.ship;

import java.util.*;

/**
 * 人 狼 羊 蔬菜
 * 人需要将狼、羊、蔬菜运输到河对岸，每次只能运输意见物品
 * 1.狼不能与羊单独在一起
 * 2.羊不能和蔬菜在一起
 * <p>
 * 使用四位类标志状态：
 * 1111：表示初始状态,人、狼、羊、蔬菜在一侧
 * 0000：最终状态人、狼、羊、蔬菜在另一侧
 * 0010：表示人将蔬菜和狼转移到了另外一侧
 * <p>
 * 0110 -> 6
 * 0111 -> 7
 * 0011 -> 3
 * <p>
 * 1001 -> 9
 * 1000 ->8
 * 1100 -> 13
 * <p>
 * <p>
 * <p>
 * 简单说明：
 * 1表示起始的左侧
 * 0表示将物品转移到另一侧
 *
 * @author edward
 * @see
 * @since
 */
class Test {

    private Integer[] pre;

    public Test() {
        boolean[] visited = new boolean[16];
        HashSet<Integer> set = new HashSet<>(Arrays.asList(3, 9, 7, 13, 6, 8));
        Queue<Integer> queue = new LinkedList<>();
        queue.add(15);
        visited[15] = true;
        pre = new Integer[16];
        while (!queue.isEmpty()) {
            int current = queue.remove();
            List<Integer> status = createStatue(current);
            for (int next : status) {
                if (!visited[next] && !set.contains(next)) {
                    queue.add(next);
                    pre[next] = current;
                    visited[next] = true;
                    if (next == 0) {
                        return;
                    }
                }
            }
        }
    }

    private List<Integer> createStatue(int current) {
        List<Integer> list = new ArrayList<>();
        int people = ((current >> 3 % 2) + 1) % 2;
        int x = 4;
        list.add(people * 8 + current % 8);
        for (int i = 1; i < 4; i++) {
            int height = (current >> 1) / x;
            int low = (current >> 1) % x;
            int hh = height >> 1;
            int cur = height % 2;
            int next = (cur + 1) % 2;
            list.add(people * 8 + hh * x * 2 + next * x + low);
            x = x / 2;
        }
        return list;
    }

    private static void test(int current, int x) {
        int people = ((current >> 3 % 2) + 1) % 2;
        current = current >> 1;
        int height = current / x;
        int low = current % x;
        int hh = height / 2;
        int cur = height % 2;
        int next = (cur + 1) % 2;
        System.out.println(people * 8 + hh * x * 2 + next * x + low);
    }

    public void path() {
        List<Integer> list = new ArrayList<>();
        Integer cur = 0;
        while (cur != null) {
            list.add(cur);
            cur = pre[cur];
        }
        Collections.reverse(list);
        list.forEach(it -> {
            System.out.println(Integer.toBinaryString(it));
            int people = it / 8 % 2;
            int wolf = it / 4 % 2;
            int sheep = it / 2 % 2;
            int vegetable = it % 2;
            List<String> left = new ArrayList<>();
            List<String> right = new ArrayList<>();
            if (people == 1) {
                left.add("人");
            } else {
                right.add("人");
            }
            if (wolf == 1) {
                left.add("狼");
            } else {
                right.add("狼");
            }
            if (sheep == 1) {
                left.add("羊");
            } else {
                right.add("羊");
            }
            if (vegetable == 1) {
                left.add("菜");
            } else {
                right.add("菜");
            }
            System.out.println("左岸" + left);
            System.out.println("右岸" + right);
            System.out.println("==========================");
        });
    }


    public static void main(String[] args) {
        new Test().path();
    }
}
