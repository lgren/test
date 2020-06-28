package com.lgren.pool.base;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * 连接池工厂类
 *
 * 如果config.setTestOnBorrow(false); 则每次获取连接不会验证链接
 * 流程: {@link #makeObject}(没有连接则创建连接)
 *      -> {@link #activateObject}(每次获取连接都会激活连接)
 *      -> 调用方法
 *      -> {@link #passivateObject}(每次返回连接都会钝化连接)
 *
 * 如果config.setTestOnBorrow(true); 则每次获取连接都会验证链接
 * 流程: {@link #makeObject}(没有连接则创建连接)
 *      -> {@link #activateObject}(每次获取连接都会激活连接)
 *      -> {@link #validateObject}(每次获取连接都会校验连接) 如果校验失败则会{@link #destroyObject}(摧毁连接再重新走流程)
 *      -> 调用方法
 *      -> {@link #passivateObject}(每次返回连接都会钝化连接)
 * @author lgren
 * @since 2020-04-17 11:14 上午
 */
public class DemoConnectionFactory implements PooledObjectFactory<DemoConnection> {
    private String host;
    private Integer port;

    public DemoConnectionFactory(String host, Integer post) {
        this.host = host;
        this.port = post;
    }

    // 产生一个连接对象
    @Override
    public PooledObject<DemoConnection> makeObject() throws Exception {
        DemoConnection connection = new DemoConnection(this.host, this.port);
        return new DefaultPooledObject<>(connection.connection());
    }

    // 销毁一个连接对象
    @Override
    public void destroyObject(PooledObject<DemoConnection> pooledObject) {
        DemoConnection connection = pooledObject.getObject();
        connection.destroy();
    }

    // 验证对象是否可用 设置config.setTestOnBorrow(true);在借连接时调用 如果返回false则#destroyObject并#makeObject
    @Override
    public boolean validateObject(PooledObject<DemoConnection> pooledObject) {
        DemoConnection connection = pooledObject.getObject();
        return connection != null && connection.isActive() && !connection.isPassivate();

    }

    // 重新激活一个对象
    @Override
    public void activateObject(PooledObject<DemoConnection> pooledObject) {
        DemoConnection connection = pooledObject.getObject();
        connection.activate();
    }

    // 钝化一个对象
    @Override
    public void passivateObject(PooledObject<DemoConnection> pooledObject) {
        DemoConnection connection = pooledObject.getObject();
        connection.passivate();
    }
}
