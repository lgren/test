package com.lgren.pool.base;

import lombok.Data;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 将office转化为PDF; 此版本主要是连接池和普通连接进行切换很容易(多半是改需求)
 * 优点: 连接池连接和一般连接转换方便
 */
@Data
public class DemoServiceNotUsePool {
    // 获取连接方法
    private final Supplier<DemoConnection> getConnection;
    // 钝化连接方法
    private final Consumer<DemoConnection> returnConnection;

    public void method() {
        DemoConnection connection = getConnection.get();
        System.out.println("OK");
        Optional.ofNullable(returnConnection).ifPresent(r -> r.accept(connection));
    }

    // @PostConstruct
    // public void init() {
    //     poolConfig.setMaxTotal(5);// 最大连接数
    //     poolConfig.setMaxIdle(5);// 最大空闲连接数
    //     poolConfig.setMinIdle(1);// 最小空闲连接数
    //     connect(host, port);
    // }

    private static final DemoConnection CONNECTION = new DemoConnection("", 8100);
    /** 不使用连接池 */
    public static void main(String[] args) {
        DemoServiceNotUsePool handle = new DemoServiceNotUsePool(() -> CONNECTION, null);
        handle.method();

        // CONNECTION.destroy();
    }
}
