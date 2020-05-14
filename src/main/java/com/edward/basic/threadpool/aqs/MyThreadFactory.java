/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     MyThreadFactory
 * CreationDate: 2020/5/13
 * Author edward
 */
package com.edward.basic.threadpool.aqs;

import com.edward.basic.threadpool.EThreadFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class MyThreadFactory implements EThreadFactory {

    private AtomicInteger threadNum = new AtomicInteger(1);
    private ThreadGroup threadGroup;
    private String name;

    public MyThreadFactory() {
        this.name = this.getClass().getName();
        this.threadGroup = Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread create(Runnable runnable) {
        return new Thread(threadGroup, name + "-" + threadNum.getAndAdd(1));
    }
}
