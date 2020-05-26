package com.lgren.pool;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import lombok.Getter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 将office转化为PDF;
 */
public class TestConnectionHandle {
    @Getter
    private TestConnectionPool poolHandle;

    private void init() {
        GenericObjectPoolConfig<TestConnection> poolConfig = new GenericObjectPoolConfig<>();
        // 最大连接数
        poolConfig.setMaxTotal(4);// openoffice.maxTotal
        // 最大空闲连接数
        poolConfig.setMaxIdle(4);// openoffice.maxIdle
        // 最小空闲连接数
        poolConfig.setMinIdle(2);// openoffice.minIdle

        poolConfig.setTestOnBorrow(true);
        poolHandle = new TestConnectionPool(new TestConnectionFactory("192.168.28.224", 8100), poolConfig);
    }

    public TestConnectionHandle() {
        this.init();
    }

    public void statistics() {
        System.out.printf("创建数:%1$s, 总共借入数:%2$s\n",
                poolHandle.getCreatedCount(),
                poolHandle.getBorrowedCount(),
                poolHandle.getDestroyedByBorrowValidationCount());
    }

    public String invoke() {
        TestConnection connection = poolHandle.borrowObject();
        String result = connection.invoke();
        poolHandle.returnObject(connection);
        return result;
    }

    public String invokeNotReturn() {
        TestConnection connection = poolHandle.borrowObject();
        String result = connection.invoke();
        // poolHandle.returnObject(connection);
        return result;
    }

    public String invokeNotTimeReturn(long timeout) {
        TestConnection connection = poolHandle.borrowObject();
        String result = connection.invoke();
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) { e.printStackTrace(); }
        poolHandle.returnObject(connection);
        statistics();
        return result;
    }

    public static void main(String[] args) throws Exception {
        TestConnectionHandle testConnectionHandle = new TestConnectionHandle();
        TestConnectionPool poolHandle = testConnectionHandle.getPoolHandle();
        testConnectionHandle.invokeNotTimeReturn(1);
        poolHandle.clear();
        // System.out.println();
        // int num = 5;
        // ExecutorService exe = Executors.newFixedThreadPool(4);
        // CountDownLatch run = new CountDownLatch(num);// 5个运动员
        // while (num > 0) {
        //     exe.submit(() -> {
        //         testConnectionHandle.invokeNotTimeReturn(2);
        //         run.countDown();
        //     });
        //     num--;
        // }
        // run.await();
        // exe.shutdown();
        // testConnectionHandle.getPoolHandle().clear();
        // testConnectionHandle.statistics();
        // System.out.println("完成");
        // System.out.println();
    }
}
