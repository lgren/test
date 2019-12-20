package com.lgren.office.jyyh;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author LinHuang
 * @time 2019-12-03 15:54
 */
public class OfficeConnectionFactory implements PooledObjectFactory<OfficeConnection> {
    // 产生一个连接对象
    @Override
    public PooledObject<OfficeConnection> makeObject() throws Exception {
        return new DefaultPooledObject<>(new OfficeConnection());
    }

    // 销毁一个连接对象
    @Override
    public void destroyObject(PooledObject<OfficeConnection> pooledObject) throws Exception {
        pooledObject.getObject().destroy();
    }

    // 校验方法
    @Override
    public boolean validateObject(PooledObject<OfficeConnection> pooledObject) {
        return pooledObject.getObject().active();
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
