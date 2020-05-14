/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     Callback
 * CreationDate: 2020/5/14
 * Author edward
 */
package com.edward.basic.promise;

/**
 * TODO
 *
 * @author edward
 * @see
 * @since
 */
public interface Callback<T> {

    TaskFuture<T> apply(TaskFuture<T> callback);

    default Callback<T> compose(Callback<T> before) {
        return callback -> apply(before.apply(callback));
    }

    default Callback<T> andThen(Callback<T> after) {
        return callback -> after.apply(this.apply(callback));
    }
}
