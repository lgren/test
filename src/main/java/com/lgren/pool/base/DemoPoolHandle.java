package com.lgren.pool.base;

import lombok.Getter;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import javax.annotation.PreDestroy;
import java.io.Closeable;
import java.io.IOException;

/**
 * 将office转化为PDF;
 */
@Getter
public class DemoPoolHandle extends GenericObjectPoolConfig<DemoConnection> {
    private GenericObjectPool<DemoConnection> pool;

    /** 连接 */
    public DemoPoolHandle connect(String host, int port) {
        System.out.println(this.getClass().getName() + " 初始化开始");
        pool = new GenericObjectPool<>(new DemoConnectionFactory(host, port), this);
        System.out.println(this.getClass().getName() + " 初始化结束");
        return this;
    }

    /** 摧毁连接 */
    @PreDestroy
    public void destroy(){
        System.out.println(this.getClass().getName() + " 摧毁开始");
        pool.close();
        System.out.println(this.getClass().getName() + " 摧毁结束");
    }

    /** 借用连接 */
    public DemoConnection getConnection() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** 退还连接 */
    public void returnConnect(DemoConnection connection) {
        pool.returnObject(connection);
    }
}
