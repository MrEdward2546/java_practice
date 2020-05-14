/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     BlockingQueue
 * CreationDate: 2020/5/13
 * @Author edward
 */
package com.edward.basic.queue.blocking;

import com.edward.basic.queue.Queue;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public interface BlockingQueue<E> extends Queue<E> {

    E take() throws InterruptedException;

    void put(E ele) throws InterruptedException;

    boolean offer(E ele);

    E poll();

}
