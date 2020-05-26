package com.lgren.office.openoffice.old;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * OpenOffice工具类
 * @since 2020/4/8 4:35 下午
 * @author Lgren
 */
public class OfficeHandle {
    // public static final String HOST = "192.168.17.214";// openoffice.ip
    public static final String HOST = "192.168.28.224";// openoffice.ip
    public static final Integer PORT = 8100;// openoffice.port
    private static final int OPENOFFICE_MAXIDLE = 5;// openoffice.maxIdle
    /** OpenOffice最大连接数 */
    private static final int OPENOFFICE_MAXTOTAL = 70;// openoffice.maxTotal
    /** OpenOffice最小空闲连接数 */
    private static final int OPENOFFICE_MINIDLE = 5;// openoffice.minIdle

    private static class Holder {
        private static final OfficeHandle DEFAULT_POOL = new OfficeHandle();
    }
    private GenericObjectPool<OfficeConnection> officePool;

    private OfficeHandle() {
        officePool = new GenericObjectPool<>(new OfficeConnPoolFactory(HOST, PORT), getDefaultConfig());
    }

    private OfficeHandle(GenericObjectPoolConfig<OfficeConnection> config) {
        officePool = new GenericObjectPool<>(new OfficeConnPoolFactory(HOST, PORT), config);
    }

    public static OfficeHandle getPool() {
        return Holder.DEFAULT_POOL;
    }

    public static OfficeHandle getPool(GenericObjectPoolConfig<OfficeConnection> config) {
        return new OfficeHandle(config);
    }


    /** 获取OpenOffice连接 */
    public OfficeConnection getConnection() {
        try {
            return officePool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 还回OpenOffice连接 */
    public void returnConnection(OfficeConnection officeConnection) {
        officePool.returnObject(officeConnection);
    }

    /** 获取默认连接池配置 */
    private GenericObjectPoolConfig<OfficeConnection> getDefaultConfig() {
        // 设置对象池的相关参数
        GenericObjectPoolConfig<OfficeConnection> poolConfig = new GenericObjectPoolConfig<>();
        // 最大空闲连接数
        poolConfig.setMaxIdle(OPENOFFICE_MAXIDLE);
        // 最大连接数
        poolConfig.setMaxTotal(OPENOFFICE_MAXTOTAL);
        // 最小空闲连接数
        poolConfig.setMinIdle(OPENOFFICE_MINIDLE);
        return poolConfig;
    }
}
