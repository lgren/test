package com.lgren.office.openoffice.jyyh;

import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import lombok.extern.slf4j.Slf4j;

import java.net.ConnectException;

/**
 * @author LinHuang
 * @time 2019-12-03 15:54
 */
@Slf4j
public class OfficeConnection {
    //openoffice.ip=192.168.17.60
    //openoffice.ip=192.168.10.137


    // openoffice.port=8100
    public static final String HOST = "192.168.28.94";// openoffice.ip
    public static final Integer PORT = 8100;// openoffice.port

    private OpenOfficeConnection connection;

    public OfficeConnection() {
        // 连接OpenOffice服务
        connection = new SocketOpenOfficeConnection(HOST, PORT);
        try {
            connection.connect();
        } catch (ConnectException e) {
            log.error("OpenOffice连接建立失败", e);
        }
    }

    public void destroy() {
        try {
            connection.disconnect();
        } catch (Exception e) {
            log.error("OpenOffice连接关闭失败", e);
            throw new RuntimeException("关闭OpenOffice连接失败:" + e.getMessage());
        }
    }

    public boolean active() {
        return connection != null && connection.isConnected();
    }

    public OpenOfficeConnection getConnection() {
        return connection;
    }

    public void setConnection(OpenOfficeConnection connection) {
        this.connection = connection;
    }
}
