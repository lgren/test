package com.lgren.office.openoffice.jyyh;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author LinHuang
 * @time 2019-12-03 16:20
 */
public class OfficeUtil {
    // openoffice.maxIdle=15
    // openoffice.maxTotal=70
    // openoffice.minIdle=5
    public static final String OPENOFFICE_MAXIDLE = "5";// openoffice.maxIdle
    /** OpenOffice最大连接数 */
    public static final String OPENOFFICE_MAXTOTAL = "70";// openoffice.maxTotal
    /** OpenOffice最小空闲连接数 */
    public static final String OPENOFFICE_MINIDLE = "5";// openoffice.minIdle

    private static GenericObjectPool<OfficeConnection> objectPool = null;

    static {
        OfficeConnectionFactory officeConnectionFactory =  new OfficeConnectionFactory();
        // 设置对象池的相关参数
        GenericObjectPoolConfig<OfficeConnection> poolConfig = new GenericObjectPoolConfig<>();
        // 最大空闲连接数
        poolConfig.setMaxIdle(Integer.valueOf(OPENOFFICE_MAXIDLE));
        // 最大连接数
        poolConfig.setMaxTotal(Integer.valueOf(OPENOFFICE_MAXTOTAL));
        // 最小空闲连接数
        poolConfig.setMinIdle(Integer.valueOf(OPENOFFICE_MINIDLE));
        // 新建一个对象池,传入对象工厂和配置
        objectPool = new GenericObjectPool<>(officeConnectionFactory, poolConfig);
    }

    /**
     * 获取OpenOffice连接
     * @return
     */
    public static OfficeConnection getOfficeConnection() {
        try {
            return objectPool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 还回OpenOffice连接
     * @param officeConnection
     */
    public static void returnOfficeConnection(OfficeConnection officeConnection) {
        objectPool.returnObject(officeConnection);
    }
}
