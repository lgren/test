package com.lgren.pool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * OpenOffice工厂类
 * @author lgren
 * @since 2020-04-17 11:14 上午
 */
public class TestConnectionFactory implements PooledObjectFactory<TestConnection> {
    private String host;// openoffice.ip
    private Integer port;// openoffice.port

    public TestConnectionFactory(String host, Integer post) {
        this.host = host;
        this.port = post;
    }

    // 产生一个连接对象
    @Override
    public PooledObject<TestConnection> makeObject() throws Exception {
        TestConnection connection = new TestConnection(this.host, this.port);
        return new DefaultPooledObject<>(connection.connection());
    }

    // 销毁一个连接对象
    @Override
    public void destroyObject(PooledObject<TestConnection> pooledObject) {
        TestConnection connection = pooledObject.getObject();
        connection.destroy();
        // try {
        //     connection.disconnect();
        // } catch (Exception e) {
        //     log.error("OpenOffice连接关闭失败", e);
        //     throw new RuntimeException("关闭OpenOffice连接失败:" + e);
        // }
    }

    // 验证对象是否可用
    @Override
    public boolean validateObject(PooledObject<TestConnection> pooledObject) {
        TestConnection connection = pooledObject.getObject();
        return connection != null && connection.isActive() && !connection.isPassivate();

    }

    // 重新激活一个对象
    @Override
    public void activateObject(PooledObject<TestConnection> pooledObject) {
        TestConnection connection = pooledObject.getObject();
        connection.activate();
    }

    // 钝化一个对象
    @Override
    public void passivateObject(PooledObject<TestConnection> pooledObject) {
        TestConnection connection = pooledObject.getObject();
        connection.passivate();
    }
}
