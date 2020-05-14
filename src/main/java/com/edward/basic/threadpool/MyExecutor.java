/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     MyExecutor
 * CreationDate: 2020/5/13
 * Author edward
 */
package com.edward.basic.threadpool;

import com.edward.basic.future.Callable;
import com.edward.basic.future.Future;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public interface MyExecutor {

    void submit(Runnable runnable);

    <E> Future<E> submit(Callable<E> callable);

    void shutdown() throws InterruptedException;

}
