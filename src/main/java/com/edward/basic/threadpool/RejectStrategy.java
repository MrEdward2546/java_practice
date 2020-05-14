package com.edward.basic.threadpool;

public interface RejectStrategy {
    void reject(Runnable runnable);
}