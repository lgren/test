package com.lgren.pool;

/**
 * TODO
 * @author lgren
 * @since 2020-04-17 2:19 下午
 */
public class TestConnection {
    private String host;
    private Integer port;
    private boolean isActive;
    private boolean isPassivate;

    public TestConnection(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public TestConnection connection() {
        System.out.println("连接成功");
        this.isActive = true;
        return this;
    }

    public void destroy() {
        this.isActive = false;
        System.out.println("销毁连接");
    }

    public boolean isActive() {
        System.out.println("检查是否有效");
        return isActive;
    }

    public boolean isPassivate() {
        System.out.println("检查是否处于休眠");
        return isPassivate;
    }

    public void activate() {
        System.out.println("激活");
        this.isPassivate = false;
    }

    public void passivate() {
        System.out.println("钝化");
        this.isPassivate = true;
    }

    public String invoke() {
        System.out.println("调用");
        return "调用";
    }


}
