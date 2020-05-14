package com.edward.algorithm.queue;


import java.util.Random;

public class Main {

    // 测试使用q运行opCount个enqueueu和dequeue操作所需要的时间，单位：秒
    private static double testQueue(Queue<Integer> q, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            q.dequeue();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 100000;

        ArrayListQueue<Integer> arrayQueue = new ArrayListQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + " s");

        LinkedListQueue<Integer> linkedListStack = new LinkedListQueue<>();
        double time2 = testQueue(linkedListStack, opCount);
        System.out.println("LinkedListQueue, time: " + time2 + " s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time3 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time3 + " s");


    }
}
