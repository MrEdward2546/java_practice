package com.edward.algorithm.stack;

import java.util.Random;

public class Main {

    // 测试使用q运行opCount个enqueueu和dequeue操作所需要的时间，单位：秒
    private static double testQueue(Stack<Integer> q, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            q.push(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            q.pop();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 100000;

//        ArrayListStack<Integer> arrayQueue = new ArrayListStack<>();
//        double time1 = testQueue(arrayQueue, opCount);
//        System.out.println("ArrayQueue, time: " + time1 + " s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testQueue(linkedListStack, opCount);
        System.out.println("ArrayQueue, time: " + time2 + " s");


    }
}
