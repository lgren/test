package com.lgren.pool.Simple;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * 将office转化为PDF;
 */
public class SimpleConnectionHandle {
    @Getter
    private final GenericObjectPoolConfig<SimpleConnection> poolConfig = new GenericObjectPoolConfig<>();
    @Getter
    private GenericObjectPool<SimpleConnection> pool;

    // @PostConstruct
    // public void init() {
    //     poolConfig.setMaxTotal(4);// 最大连接数
    //     poolConfig.setMaxIdle(4);// 最大空闲连接数
    //     poolConfig.setMinIdle(2);// 最小空闲连接数
    //     poolConfig.setTestOnBorrow(true);// 每次拿连接都会验证对象是否可用
    //     connect(ip, port);
    // }

    public SimpleConnectionHandle connect(String ip, int port) {
        System.out.println("初始化连接池开始...");
        pool = new GenericObjectPool<>(new SimpleConnectionFactory(ip, port), poolConfig);
        System.out.println("初始化连接池结束!");
        return this;
    }

    public void destroy(){
        System.out.println("连接池摧毁开始...");
        // pool.clear();
        pool.close();
        System.out.println("连接池摧毁结束!");
    }

    // 统计方法
    public void statistics() {
        System.out.printf("创建数:%1$s, 总共借入数:%2$s\n",
                pool.getCreatedCount(),
                pool.getBorrowedCount(),
                pool.getDestroyedByBorrowValidationCount());
    }

    private SimpleConnection getConnection() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String invokeMethod(boolean isReturn) {
        SimpleConnection connection = getConnection();
        String result = connection.invoke();
        if (isReturn) {
            pool.returnObject(connection);
        }
        return result;
    }

    public String invokeMethod(long timeout) {
        SimpleConnection connection = getConnection();
        String result = connection.invoke();
        try { TimeUnit.SECONDS.sleep(timeout); } catch (InterruptedException e) { e.printStackTrace(); }
        pool.returnObject(connection);
        statistics();
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleConnectionHandle handle = new SimpleConnectionHandle();
        GenericObjectPoolConfig<SimpleConnection> poolConfig = handle.getPoolConfig();
        poolConfig.setMaxTotal(2);// 最大连接数
        // poolConfig.setMaxIdle(2);// 最大空闲连接数
        // poolConfig.setMinIdle(1);// 最小空闲连接数
        // poolConfig.setTestOnBorrow(true);// 每次拿连接都会验证对象是否可用
        handle.connect("", 0);

        Stream.of(1, 2, 3, 4, 5, 6, 7, 8).parallel().forEach(v -> handle.invokeMethod(2));

        // int num = 5;
        // ExecutorService exe = Executors.newFixedThreadPool(4);
        // CountDownLatch run = new CountDownLatch(num);// 5个运动员
        // while (num > 0) {
        //     exe.submit(() -> {
        //         handle.invokeMethod(2);
        //         run.countDown();
        //     });
        //     num--;
        // }
        // run.await();
        // exe.shutdown();
        //
        // handle.destroy();
        handle.statistics();
        System.out.println("完成");
        System.out.println();
    }
}
