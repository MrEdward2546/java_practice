/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     TaskFuture
 * CreationDate: 2020/5/14
 * Author edward
 */
package com.edward.basic.promise;

import com.edward.basic.future.Future;

import java.util.concurrent.TimeUnit;

/**
 * 任务future，
 * 扩展future的功能，增加了阶段的回调，当任务失败，或者任务成功会有响应的回调方法
 *
 * @author edward
 * @see
 * @since
 */
public interface TaskFuture<T> extends Future<T> {
    /**
     * 当前的任务是否执行成功
     *
     * @return
     */
    boolean isSuccess();

    /**
     * 当前的线程是否执行失败
     *
     * @return
     */
    boolean isFailure();

    /**
     * 成功的回调
     *
     * @param callback
     * @return
     */
    TaskFuture<T> onSuccess(Callback<T> callback);

    /**
     * 失败的回调
     *
     * @param callback
     * @return
     */
    TaskFuture<T> onFailure(Callback<T> callback);

    /**
     * 完成状态的回调（失败或者成功都可以）
     *
     * @param callback
     * @return
     */
    TaskFuture<T> onCompleted(Callback<T> callback);

    /**
     * 挂起当前的任务
     *
     * @return
     * @throws InterruptedException
     */
    TaskFuture<T> await() throws InterruptedException;

    /**
     * 挂起当前的任务
     *
     * @param timeout
     * @param timeUnit
     * @return
     * @throws InterruptedException
     */
    boolean await(int timeout, TimeUnit timeUnit) throws InterruptedException;

    /**
     * 立即返回任务的结果，如果任务没执行完，则返回null
     *
     * @return
     */
    T getNow();

    boolean hasAttr(String key);

    Object getAttr(String key);

    TaskFuture<T> addAttr(String key, Object value);

    Object removeAttr(String key);


}
