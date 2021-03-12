package com.lgren.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 多线程测试主类
 * @author Lgren
 * @create 2018-11-01 14:18
 **/
public class ThreadCommonTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // testMyThread();
        // testThreadRunnable();
        // testThreadCallable();
        testExecutorService();
        // testExecutorServiceSimple();

    }

    private static void testMyThread() {
        MyThread myThread1 = new MyThread("这是继承Thread1");
        MyThread myThread2 = new MyThread("这是继承Thread2");
        myThread1.start();
        myThread2.start();
    }

    private static void testThreadRunnable() {
        Thread thread = new Thread(new ThreadRunnable());
        thread.start();
    }

    private static void testThreadCallable() throws ExecutionException, InterruptedException {
        Callable<String> callable = new ThreadCallable<>("this is ThreadCallable's return value");
        FutureTask<String> oneTask = new FutureTask<>(callable);
        Thread oneThread = new Thread(oneTask);
        oneThread.start();
        System.out.println(oneTask.get());
    }

    private static void testExecutorService() {
        ExecutorService pool = Executors.newCachedThreadPool();
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Callable<String> c = new ThreadCallable<>(i + " ");
            // 执行任务并获取Future对象
            Future<String> f = pool.submit(c);
            futureList.add(f);
        }

        futureList.forEach(o -> {
            try {
                System.out.println(o.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private static void testExecutorServiceSimple() {
        ExecutorService pool = Executors.newCachedThreadPool();
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            // 执行任务并获取Future对象
            Future<String> f = pool.submit(() -> {
                System.out.println("this is ThreadCallable");
                return finalI + " ";
            });
            futureList.add(f);
        }

        futureList.forEach(o -> {
            try {
                System.out.println(o.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
