package com.lgren.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * TODO
 * @author lgren
 * @since 2020-04-08 3:53 下午
 */
public class JDBCPooledFactory extends BasePooledObjectFactory<Connection> {
    private final static String URL = "jdbc:mysql://192.168.17.104:3306/zsk?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&allowMultiQueries=true";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "111111";
    private final static String DRIVER = "com.mysql.jdbc.Driver";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection create() throws Exception {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @Override
    public PooledObject<Connection> wrap(Connection connection) {
        return new DefaultPooledObject<>(connection);
    }
}
