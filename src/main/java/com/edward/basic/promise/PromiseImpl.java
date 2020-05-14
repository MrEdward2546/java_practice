/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     PromiseImpl
 * CreationDate: 2020/5/14
 * Author edward
 */
package com.edward.basic.promise;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * promise的一个实现
 *
 * @author edward
 * @see
 * @since
 */
public class PromiseImpl<T> implements Promise<T> {

    @SuppressWarnings("rawtypes")
    private static final AtomicReferenceFieldUpdater<PromiseImpl, ConcurrentMap> updater = AtomicReferenceFieldUpdater
            .newUpdater(PromiseImpl.class, ConcurrentMap.class, "attrs");

    private volatile ConcurrentMap<String, Object> attrs;
    /**
     * 结果保存
     */
    private volatile ResultHolder<T> resultHolder;
    /**
     * 锁
     */
    private final CountDownLatch latch = new CountDownLatch(1);
    /**
     * 当前任务的回调
     */
    private final List<Callback<T>> callbacks = new LinkedList<>();

    /**
     * 设置当前的成功
     * @param result
     * @return
     */
    @Override
    public Promise<T> setSuccess(T result) {
        if (!trySuccess(result)) {
            throw new IllegalStateException("Already completed future: " + this);
        }
        return this;
    }

    /**
     * 设置当前任务失败
     * @param throwable
     * @return
     */
    @Override
    public Promise<T> setFailure(Throwable throwable) {
        if (!tryFailure(throwable)) {
            throw new IllegalStateException("Already completed future: " + this);
        }
        return this;
    }

    @Override
    public boolean trySuccess(T result) {
        if (setCompleted(new ResultHolder<>(result))) {
            executeCallbacks();
            return true;
        }
        return false;
    }

    private void executeCallbacks() {
        Iterator<Callback<T>> callbackIter = this.callbacks.iterator();
        TaskFuture<T> f = this;
        while (callbackIter.hasNext()) {
            f = callbackIter.next().apply(f);
            callbackIter.remove();
        }
    }

    @Override
    public boolean tryFailure(Throwable throwable) {
        if (setCompleted(new ResultHolder<>(throwable))) {
            executeCallbacks();
            return true;
        }
        return false;
    }

    @Override
    public boolean isSuccess() {
        return isDone() && resultHolder.isSuccess();
    }

    @Override
    public boolean isFailure() {
        return isDone() && !resultHolder.isSuccess();
    }

    @Override
    public TaskFuture<T> onSuccess(Callback<T> callback) {
        return onCompleted(f -> {
            if (f.isSuccess()) {
                callback.apply(f);
            }
            return f;
        });
    }

    @Override
    public TaskFuture<T> onFailure(Callback<T> callback) {
        return onCompleted(f -> {
            if (f.isFailure()) {
                callback.apply(f);
            }
            return f;
        });
    }

    private boolean setCompleted(ResultHolder<T> resultHolder) {
        if (isDone()) {
            return false;
        }
        synchronized (this) {
            if (isDone()) {
                return false;
            }
            this.resultHolder = resultHolder;
            latch.countDown();
        }
        return true;
    }

    @Override
    public TaskFuture<T> onCompleted(Callback<T> callback) {
        if (isDone()) {
            return callback.apply(this);
        }
        synchronized (this) {
            if (isDone()) {
                return callback.apply(this);
            }
            callbacks.add(callback);
        }
        return this;
    }

    @Override
    public TaskFuture<T> await() throws InterruptedException {
        if (isDone()) {
            return this;
        }

        synchronized (this) {
            if (isDone()) {
                return this;
            }
        }
        this.latch.await();
        return this;
    }

    @Override
    public boolean await(int timeout, TimeUnit timeUnit) throws InterruptedException {
        if (isDone()) {
            return true;
        }

        synchronized (this) {
            if (isDone()) {
                return true;
            }
        }
        return this.latch.await(timeout, timeUnit);
    }

    @Override
    public T getNow() {
        ResultHolder<T> result = this.resultHolder;
        if (result != null && result.isSuccess()) {
            return result.getResult();
        }
        return null;
    }

    @Override
    public boolean hasAttr(String key) {
        ConcurrentMap<String, Object> attributes = this.attrs;
        if (attributes == null || key == null) {
            return false;
        }
        return attributes.containsKey(key);
    }

    @Override
    public Object getAttr(String key) {
        ConcurrentMap<String, Object> attributes = this.attrs;
        if (attributes == null || key == null) {
            return null;
        }
        return attributes.get(key);
    }

    @Override
    public TaskFuture<T> addAttr(String key, Object value) {
        ConcurrentMap<String, Object> attributes = this.attrs;
        if (attributes == null) {
            attributes = new ConcurrentHashMap<>();
            if (!updater.compareAndSet(this, null, attributes)) {
                attributes = this.attrs;
            }
        }

        if (value == null) {
            attributes.remove(key);
            return this;
        }
        attributes.put(key, value);
        return this;
    }

    @Override
    public Object removeAttr(String key) {
        ConcurrentMap<String, Object> attributes = this.attrs;
        if (attributes == null || key == null) {
            return null;
        }
        return attributes.remove(key);
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        await();
        if (!isSuccess()) {
            throw new ExecutionException(resultHolder.getCause());
        }
        return getNow();
    }

    @Override
    public boolean isDone() {
        return this.resultHolder != null;
    }
}
