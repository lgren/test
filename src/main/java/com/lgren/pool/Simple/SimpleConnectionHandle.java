package com.lgren.pool.Simple;

import lombok.Getter;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 将office转化为PDF;
 */
public class SimpleConnectionHandle {
    @Getter
    private GenericObjectPool<SimpleConnection> poolHandle;

    public SimpleConnectionHandle() {
        // 初始化构造
        GenericObjectPoolConfig<SimpleConnection> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(4);// 最大连接数
        poolConfig.setMaxIdle(4);// 最大空闲连接数
        poolConfig.setMinIdle(2);// 最小空闲连接数
        poolConfig.setTestOnBorrow(true);// 每次拿连接都会验证对象是否可用
        poolHandle = new GenericObjectPool<>(new SimpleConnectionFactory("192.168.28.224", 8100), poolConfig);

    }

    // 统计方法
    public void statistics() {
        System.out.printf("创建数:%1$s, 总共借入数:%2$s\n",
                poolHandle.getCreatedCount(),
                poolHandle.getBorrowedCount(),
                poolHandle.getDestroyedByBorrowValidationCount());
    }

    private SimpleConnection getConnection() {
        try {
            return poolHandle.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String borrowAndReturn() {
        SimpleConnection connection = getConnection();
        String result = connection.invoke();
        poolHandle.returnObject(connection);
        return result;
    }

    public String borrowAndNotReturn() {
        SimpleConnection connection = getConnection();
        String result = connection.invoke();
        // poolHandle.returnObject(connection);
        return result;
    }

    public String borrowAndTimeoutReturn(long timeout) {
        SimpleConnection connection = getConnection();
        String result = connection.invoke();
        try { TimeUnit.SECONDS.sleep(timeout); } catch (InterruptedException e) { e.printStackTrace(); }
        poolHandle.returnObject(connection);
        statistics();
        return result;
    }

    @Test
    public void test1() throws InterruptedException {
        CountDownLatch run = new CountDownLatch(5);
        for (long i = 0, count = run.getCount(); i < count; i++) {
            System.out.println(run.getCount());
            run.countDown();
        }
        run.await();
        // run.await(1L, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        SimpleConnectionHandle handle = new SimpleConnectionHandle();
        ExecutorService exe = Executors.newFixedThreadPool(4);
        for (long i = 0; i < 10; i++) {
            exe.submit(() -> handle.borrowAndTimeoutReturn(1));
        }
        exe.shutdown();
        List<Runnable> notSubmitTaskList = null;
        // try {
        //     if(!exe.awaitTermination(2, TimeUnit.SECONDS)){
        //         // 超时的时候向线程池中所有的线程发出中断(interrupted)。
        //         notSubmitTaskList = exe.shutdownNow();
        //     }
        // } catch (InterruptedException e) {
        //     notSubmitTaskList = exe.shutdownNow();
        // }
        System.out.println("OK");
        System.out.println(notSubmitTaskList);
    }

    public static void main1(String[] args) throws Exception {
        SimpleConnectionHandle handle = new SimpleConnectionHandle();
        // GenericObjectPool<SimpleConnection> poolHandle = handle.getPoolHandle();
        ExecutorService exe = Executors.newFixedThreadPool(1);
        CountDownLatch run = new CountDownLatch(5);
        for (long i = 0, count = run.getCount(); i < count; i++) {
            exe.submit(() -> {
                handle.borrowAndTimeoutReturn(1);
                run.countDown();
            });
        }
        run.await();
        exe.shutdown();
        System.out.println(exe.awaitTermination(2, TimeUnit.SECONDS));
        System.out.println("完成");
        // handle.getPoolHandle().clear();
        // handle.statistics();
        System.out.println();
    }
}
