package com.lgren.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.sql.Connection;
import java.util.Optional;

/**
 * TODO
 * @author lgren
 * @since 2020-04-08 1:35 下午
 */
public class TestPool {
    private static GenericObjectPool<Connection> connPool;

    private static class Holder {
        private static final TestPool POOL = new TestPool();
    }

    private TestPool() {
        connPool = new GenericObjectPool<>(new JDBCPooledFactory(), getDefaultConfig());
    }

    public TestPool(GenericObjectPoolConfig<Connection> config) {
        connPool = new GenericObjectPool<>(new JDBCPooledFactory(),
                Optional.ofNullable(config).orElseGet(this::getDefaultConfig));
    }

    private GenericObjectPoolConfig<Connection> getDefaultConfig() {
        GenericObjectPoolConfig<Connection> conf = new GenericObjectPoolConfig<>();
        // TODO -- 默认8,8,0
        conf.setMaxTotal(50);
        conf.setMaxIdle(50);
        conf.setMinIdle(0);
        conf.setMaxWaitMillis(60000);
        return conf;
    }

    public static TestPool getInstance() {
        return Holder.POOL;
    }
}
