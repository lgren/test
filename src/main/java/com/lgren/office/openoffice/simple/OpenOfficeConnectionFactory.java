package com.lgren.office.openoffice.simple;

import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * OpenOffice工厂类
 * @author lgren
 * @since 2020-04-17 11:14 上午
 */
public class OpenOfficeConnectionFactory implements PooledObjectFactory<OpenOfficeConnection> {
    private String host;// openoffice.ip
    private Integer port;// openoffice.port

    public OpenOfficeConnectionFactory(String host, Integer post) {
        this.host = host;
        this.port = post;
    }

    // 产生一个连接对象
    @Override
    public PooledObject<OpenOfficeConnection> makeObject() throws Exception {
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(this.host, this.port);
        connection.connect();
        return new DefaultPooledObject<>(connection);
    }

    // 销毁一个连接对象
    @Override
    public void destroyObject(PooledObject<OpenOfficeConnection> pooledObject) {
        OpenOfficeConnection connection = pooledObject.getObject();
        connection.disconnect();
        // try {
        //     connection.disconnect();
        // } catch (Exception e) {
        //     log.error("OpenOffice连接关闭失败", e);
        //     throw new RuntimeException("关闭OpenOffice连接失败:" + e);
        // }
    }

    // 校验方法
    @Override
    public boolean validateObject(PooledObject<OpenOfficeConnection> pooledObject) {
        OpenOfficeConnection connection = pooledObject.getObject();
        return connection != null && connection.isConnected();

    }

    // 重新激活一个对象
    @Override
    public void activateObject(PooledObject<OpenOfficeConnection> pooledObject) {

    }

    // 钝化一个对象
    @Override
    public void passivateObject(PooledObject<OpenOfficeConnection> pooledObject) {

    }
}
