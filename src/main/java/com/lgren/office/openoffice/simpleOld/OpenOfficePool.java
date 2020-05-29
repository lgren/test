package com.lgren.office.openoffice.simpleOld;

import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
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
public class OpenOfficePool extends GenericObjectPool<OpenOfficeConnection> {
    public OpenOfficePool(PooledObjectFactory<OpenOfficeConnection> factory) {
        super(factory);
    }

    public OpenOfficePool(PooledObjectFactory<OpenOfficeConnection> factory, GenericObjectPoolConfig<OpenOfficeConnection> config) {
        super(factory, config);
    }

    public OpenOfficePool(PooledObjectFactory<OpenOfficeConnection> factory, GenericObjectPoolConfig<OpenOfficeConnection> config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }

    /** 获取OpenOffice连接 */
    @Override
    public OpenOfficeConnection borrowObject() {
        try {
            return super.borrowObject();
        } catch (Exception e) {
            log.error("OpenOffice连接建立失败", e);
            throw new RuntimeException("OpenOffice连接建立失败", e);
        }
    }
}
