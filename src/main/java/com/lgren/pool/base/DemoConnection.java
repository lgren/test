package com.lgren.pool.base;

import lombok.Setter;

import java.io.Closeable;
import java.io.IOException;

/**
 * TODO
 * @author lgren
 * @since 2020-04-17 2:19 下午
 */
public class DemoConnection {
    private String host;
    private Integer port;
    @Setter
    private boolean isActive;
    @Setter
    private boolean isPassivate;

    public DemoConnection(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public DemoConnection connection() {
        System.out.println("1.连接成功");
        this.isActive = true;
        return this;
    }

    public void destroy() {
        this.isActive = false;
        System.out.println("销毁连接");
    }

    public boolean isActive() {
        System.out.println("-1.是否有效");
        return isActive;
    }

    public boolean isPassivate() {
        System.out.println("-2.是否钝化");
        return isPassivate;
    }

    public void activate() {
        System.out.println("2.激活");
        this.isPassivate = false;
    }

    public void passivate() {
        System.out.println("3.钝化");
        this.isPassivate = true;
    }

    public String invoke() {
        System.out.println("调用");
        return "调用";
    }

}
