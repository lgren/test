package com.lgren.thread.concurrentTest;

import java.util.concurrent.Semaphore;

/**
 * 可以维护当前访问自身的线程个数，并提供了同步机制。使用Semaphore可以控制同时访问资源的线程个数，例如，实现一个文件允许的并发访问数。
 * @author lgren
 * @since 2019-09-10 16:51
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore sp = new Semaphore(20);
    }
}
