/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     FutureTask
 * CreationDate: 2020/5/13
 * Author edward
 */
package com.edward.basic.future;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class FutureTask<E> implements Future<E> {

    private volatile boolean done;
    private E e;

    @Override
    public synchronized E get() throws InterruptedException {
        while (!done) {
            this.wait();
        }
        return e;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    public synchronized void setValue(E value) {
        this.e = value;
        this.done = true;
        this.notifyAll();
    }
}
