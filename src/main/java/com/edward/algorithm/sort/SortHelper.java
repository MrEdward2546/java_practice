/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     SortHelper
 * CreationDate: 2020/3/27
 * Author edward
 */
package com.edward.algorithm.sort;

import java.util.Random;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class SortHelper {

    public static Integer[] generateRandom(int num, int min, int max) {
        Integer[] integers = new Integer[num];
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            integers[i] = min + random.nextInt(max - min + 1);
        }
        return integers;
    }


    public static Integer[] generateOrder(int num, int swapTimes) {
        Integer[] integers = new Integer[num];
        for (int i = 0; i < num; i++) {
            integers[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < swapTimes; i++) {
            int posx = random.nextInt(num+1) % num;
            int posy = random.nextInt(num+1) % num;
            swap(integers, posx, posy);
        }
        return integers;
    }


    public static void test(Integer[] data, Sort<Integer> sort, String name) {
        long start = System.nanoTime();
        sort.sort(data);
        long endTime = System.nanoTime();
        double time = (endTime - start) / 1000000000.0;
        System.out.println(name + ":" + time);
    }

    private static void swap(Integer[] data, int i, int j) {
        Integer temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void isValid(Integer[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i - 1] < data[i]) {
                throw new RuntimeException("排序错误");
            }
        }
    }
}
