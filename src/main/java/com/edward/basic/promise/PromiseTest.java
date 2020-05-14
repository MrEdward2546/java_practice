/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     PromiseTest
 * CreationDate: 2020/5/14
 * Author edward
 */
package com.edward.basic.promise;

import java.util.concurrent.ExecutionException;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class PromiseTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Promise<String> promise = new PromiseImpl<>();
        promise.onFailure(f -> {
            new Thread(() -> {
                System.out.println("onFailure");
            }).start();
            return f;
        }).onSuccess(f -> {
            new Thread(() -> {
                System.out.println(promise.getAttr("hello"));
                System.out.println("onSuccess");
            }).start();
            return f;
        }).onCompleted(f -> {
            new Thread(() -> {
                System.out.println("onCompleted");
            }).start();
            return f;
        });

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            promise.addAttr("hello", "welcome");
            promise.setSuccess("hello");
        }).start();

        String str = promise.get();
        System.out.println(str);
    }
}
