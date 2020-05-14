/*
 * Copyright (c)  2018. 天津小多科技有限公司.
 * Site:         http://xiaoduo.io
 * Email:        shj@xiaoduo.io
 * FileName:     ResultHolder
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
public class ResultHolder<T> {

    private T result;
    private Throwable cause;
    private boolean success;

    public ResultHolder(T result) {
        this.result = result;
        this.success = true;
    }

    public ResultHolder(Throwable cause) {
        this.cause = cause;
        this.success = false;
    }

    public void setResult(T result) {
        this.success = true;
        this.result = result;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
        this.success = false;
    }

    public T getResult() {
        return result;
    }

    public Throwable getCause() {
        return cause;
    }

    public boolean isSuccess() {
        return success;
    }
}
