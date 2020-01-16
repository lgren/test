package com.lgren.office.openoffice.lgren;

import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.net.ConnectException;

/**
 * TODO
 * @author lgren
 * @since 2019-12-23 11:54
 */
@Slf4j
public class OpenOfficeConnFactory implements PooledObjectFactory<OpenOfficeConnection> {
    private String host;
    private Integer post;

    public OpenOfficeConnFactory(String host, Integer post) {
        this.host = host;
        this.post = post;
    }

    // 产生一个连接对象
    @Override
    public PooledObject<OpenOfficeConnection> makeObject() throws Exception {
        // 连接OpenOffice服务
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(host, post);
        try {
            connection.connect();
        } catch (ConnectException e) {
            log.error("OpenOffice连接建立失败", e);
            throw new RuntimeException("OpenOffice连接建立失败:" + e.getMessage());
        }
        return new DefaultPooledObject<>(connection);
    }

    // 销毁一个连接对象
    @Override
    public void destroyObject(PooledObject<OpenOfficeConnection> pooledObject) throws Exception {
        try {
            pooledObject.getObject().disconnect();
        } catch (Exception e) {
            log.error("OpenOffice连接关闭失败", e);
            throw new RuntimeException("关闭OpenOffice连接失败:" + e.getMessage());
        }
    }

    // 校验方法
    @Override
    public boolean validateObject(PooledObject<OpenOfficeConnection> pooledObject) {
        return pooledObject.getObject() != null && pooledObject.getObject().isConnected();
    }

    // 重新激活一个对象
    @Override
    public void activateObject(PooledObject<OpenOfficeConnection> pooledObject) throws Exception {

    }

    // 钝化一个对象
    @Override
    public void passivateObject(PooledObject<OpenOfficeConnection> pooledObject) throws Exception {

    }
}
