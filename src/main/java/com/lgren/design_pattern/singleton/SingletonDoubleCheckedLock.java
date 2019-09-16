package com.lgren.design_pattern.singleton;

/**
 * 双重检查加锁（double-checked locking）：只有第一次才进行同步检查，大大减少了getInstance的时间 (推荐使用方式)
 * @author lgren
 * @since 2019-09-12 14:32
 */
public class SingletonDoubleCheckedLock {
    // private SingletonDoubleCheckedLock singletonDoubleCheckedLock;
    //
    // private SingletonDoubleCheckedLock() { }
    //
    // public SingletonDoubleCheckedLock getInstance() {
    //     if (singletonDoubleCheckedLock == null) {
    //         // synchronized ()
    //     }
    // }
}
