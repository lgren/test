package com.lgren.office.openoffice.old;

import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.ConnectException;

/**
 * OpenOffice连接
 * @since 2020/4/8 4:16 下午
 * @author Lgren
 */
@Slf4j
public class OfficeConnection {
    @Getter
    private OpenOfficeConnection connection;

    // 连接
    public OfficeConnection(String host, int port) {
        connection = new SocketOpenOfficeConnection(host, port);
        try {
            connection.connect();
        } catch (ConnectException e) {
            log.error("OpenOffice连接建立失败", e);
        }
    }

    // 销毁连接
    public void destroy() {
        try {
            connection.disconnect();
        } catch (Exception e) {
            log.error("OpenOffice连接关闭失败", e);
            throw new RuntimeException("关闭OpenOffice连接失败:" + e.getMessage());
        }
    }

    // 是否正在连接
    public boolean isActive() {
        return connection != null && connection.isConnected();
    }
}
