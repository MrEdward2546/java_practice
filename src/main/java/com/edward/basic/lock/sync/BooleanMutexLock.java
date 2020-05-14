/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     BooleanMutexLock
 * CreationDate: 2020/5/13
 * Author edward
 */
package com.edward.basic.lock.sync;

import com.edward.basic.lock.Lock;

import java.util.concurrent.TimeoutException;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class BooleanMutexLock implements Lock {

    private boolean lock = Boolean.FALSE;

    private Thread thread;

    @Override
    public synchronized void lock() throws Exception {
        while (lock) {
            this.wait();
        }
        lock = Boolean.TRUE;
        this.thread = Thread.currentThread();
        this.notifyAll();
    }

    @Override
    public synchronized void lock(long timeout) throws Exception {
        if (timeout <= 0) {
            lock();
        }
        long hasRemaining = timeout;
        long endTime = System.currentTimeMillis() + timeout;
        while (lock) {
            if (hasRemaining <= 0) {
                throw new TimeoutException("timeout");
            }
            this.wait(timeout);
            hasRemaining = endTime - System.currentTimeMillis();
        }
        this.lock = Boolean.TRUE;
        this.thread = Thread.currentThread();
        this.notifyAll();

    }

    @Override
    public synchronized void unlock() throws Exception {
        if (!lock) {
            throw new IllegalMonitorStateException("current thread not lock");
        }
        if (this.thread == Thread.currentThread()) {
            lock = Boolean.FALSE;
            this.thread = null;
            this.notifyAll();
        } else {
            throw new IllegalMonitorStateException("current is not owner thread");
        }

    }

}
