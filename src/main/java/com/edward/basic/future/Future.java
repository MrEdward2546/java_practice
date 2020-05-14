/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     Future
 * CreationDate: 2020/5/13
 * Author edward
 */
package com.edward.basic.future;

import java.util.concurrent.ExecutionException;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public interface Future<E> {
    /**
     * 获取到结果，此过程会阻塞
     *
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    E get() throws InterruptedException, ExecutionException;

    /**
     * 任务是否已经完成
     *
     * @return
     */
    boolean isDone();
}
