package com.lgren.office.openoffice.lgren;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author LinHuang
 * @time 2019-12-03 15:54
 */
public class OfficeConnPoolFactory implements PooledObjectFactory<OfficeConnection> {
    private String host;// openoffice.ip
    private Integer post;// openoffice.port

    public OfficeConnPoolFactory(String host, Integer post) {
        this.host = host;
        this.post = post;
    }

    // 产生一个连接对象
    @Override
    public PooledObject<OfficeConnection> makeObject() throws Exception {
        return new DefaultPooledObject<>(new OfficeConnection(host, post));
    }

    // 销毁一个连接对象
    @Override
    public void destroyObject(PooledObject<OfficeConnection> pooledObject) throws Exception {
        pooledObject.getObject().destroy();
    }

    // 校验方法
    @Override
    public boolean validateObject(PooledObject<OfficeConnection> pooledObject) {
        return pooledObject.getObject().isActive();
    }

    // 重新激活一个对象
    @Override
    public void activateObject(PooledObject<OfficeConnection> pooledObject) throws Exception {

    }

    // 钝化一个对象
    @Override
    public void passivateObject(PooledObject<OfficeConnection> pooledObject) throws Exception {

    }
}
