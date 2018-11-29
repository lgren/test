package com.lgren.thread;

import java.util.concurrent.Callable;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-11-01 14:21
 **/
public class ThreadCallable<V> implements Callable<String> {
    private V returnValue;
    ThreadCallable() {}

    ThreadCallable(V returnValue) {
        this.returnValue = returnValue;
    }

    @Override
    public String call() {
        System.out.println("this is ThreadCallable");
        return returnValue == null ? null : returnValue.toString();
    }
}
