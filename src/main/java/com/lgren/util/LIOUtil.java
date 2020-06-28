package com.lgren.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * 文件工具类
 * @author lgren
 * @since 2020-06-18 4:41 下午
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class LIOUtil {
    /**
     * 将一个输入流同时写入多个输出流
     * @since 2020/6/18 10:13 上午
     * @author Lgren
     * @param in 输入流 会关闭
     * @param out 输出流 不会关闭
     * @param otherOutArr 输出流 都不会关闭
     */
    public static void copy(InputStream in, OutputStream out, OutputStream... otherOutArr) throws IOException {
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            for (OutputStream otherOut : otherOutArr) {
                if (otherOut != null) {
                    otherOut.write(buffer, 0, bytesRead);
                }
            }
        }
        out.flush();
        for (OutputStream otherOut : otherOutArr) {
            if (otherOut != null) {
                otherOut.flush();
            }
        }

    }

    public static byte[] readBytes(InputStream in, boolean isClose) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            copy(in, out);
            return out.toByteArray();
        } catch (IOException e) {
            return new byte[0];
        } finally {
            if (isClose) {
                close(in);
            }
        }
    }

    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {
                // 静默关闭
            }
        }
    }
}
