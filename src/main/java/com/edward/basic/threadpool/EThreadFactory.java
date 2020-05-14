/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     EThreadFactory
 * CreationDate: 2020/5/13
 * @Author edward
 */
package com.edward.basic.threadpool;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public interface EThreadFactory {

    Thread create(Runnable runnable);
}
