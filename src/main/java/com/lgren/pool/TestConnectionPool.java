package com.lgren.pool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * OpenOffice连接池处理类
 * @author lgren
 * @since 2020-04-17 10:08 上午
 */
@Slf4j
public class TestConnectionPool extends GenericObjectPool<TestConnection> {
    public TestConnectionPool(PooledObjectFactory<TestConnection> factory) {
        super(factory);
    }

    public TestConnectionPool(PooledObjectFactory<TestConnection> factory, GenericObjectPoolConfig<TestConnection> config) {
        super(factory, config);
    }

    public TestConnectionPool(PooledObjectFactory<TestConnection> factory, GenericObjectPoolConfig<TestConnection> config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }

    /** 获取OpenOffice连接 */
    @Override
    public TestConnection borrowObject() {
        try {
            return super.borrowObject();
        } catch (Exception e) {
            log.error("连接建立失败", e);
            throw new RuntimeException("连接建立失败", e);
        }
    }
}
