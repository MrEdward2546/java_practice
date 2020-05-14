/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     Lock
 * CreationDate: 2020/5/13
 * @Author edward
 */
package com.edward.basic.lock;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public interface Lock {

    void lock() throws Exception;

    void lock(long timeout) throws Exception;

    void unlock() throws Exception;

}
