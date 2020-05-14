/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     Promise
 * CreationDate: 2020/5/14
 * @Author edward
 */
package com.edward.basic.promise;

/**
 * promise抽象类，扩展
 *
 * @author edward
 * @see
 * @since
 */
public interface Promise<T> extends TaskFuture<T> {
    /**
     * 使当前的任务完成
     *
     * @param result
     * @return
     */
    Promise<T> setSuccess(T result);

    /**
     * 让当前任务失败
     *
     * @param throwable
     * @return
     */
    Promise<T> setFailure(Throwable throwable);

    boolean trySuccess(T result);

    boolean tryFailure(Throwable throwable);


}
