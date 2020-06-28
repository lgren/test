package com.lgren.pool.base;

import lombok.Data;

import javax.annotation.PostConstruct;

/**
 * 将office转化为PDF;
 * 优点: 连接池连接和一般连接转换方便
 */
@Data
public class DemoService {
    // 连接池
    private final DemoPoolHandle poolHandle;

    public void method() {
        DemoConnection connection = poolHandle.getConnection();
        System.out.println("OK");
        poolHandle.returnConnect(connection);
    }

    // @PostConstruct
    // public void init() {
    //     poolHandle = new DemoPoolHandle();// 使用此方式去掉poolHandle的final
    //     poolHandle.setMaxTotal(5);// 最大连接数
    //     poolHandle.setMaxIdle(5);// 最大空闲连接数
    //     poolHandle.setMinIdle(1);// 最小空闲连接数
    //     poolHandle.setTestOnBorrow(true);// 每次拿连接都会验证对象是否可用
    //     poolHandle.connect();// 连接
    // }

    public static void main(String[] args) {
        DemoPoolHandle poolHandle = new DemoPoolHandle();
        poolHandle.setMaxTotal(4);
        poolHandle.connect("", 8100);

        DemoService handle = new DemoService(poolHandle);
        handle.method();

        poolHandle.destroy();
    }
}
