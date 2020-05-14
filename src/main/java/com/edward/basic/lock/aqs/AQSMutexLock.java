/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     BooleanMutexLock
 * CreationDate: 2020/5/13
 * Author edward
 */
package com.edward.basic.lock.aqs;

import com.edward.basic.lock.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public class AQSMutexLock implements Lock {
    private final Sync sync = new Sync();


    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1 && Thread.currentThread() == getExclusiveOwnerThread();
        }

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (!isHeldExclusively()) throw new IllegalMonitorStateException("is not current thread");
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }


    @Override
    public void lock() throws Exception {
        sync.acquire(1);
    }

    @Override
    public void lock(long timeout) throws Exception {
        sync.tryAcquireNanos(1, TimeUnit.MILLISECONDS.toNanos(timeout));
    }

    @Override
    public void unlock() throws Exception {
        sync.release(1);
    }
}
