package com.lgren.util;

import java.io.*;

/**
 * 上传工具类
 * @author lgren
 * @since 2019-12-16 18:43
 */
public class UpOrDownUtil {
    public static final String FTP = "1";
    public static final String LOCAL = "2";
    public static final String UNITE = "3";

    // TODO 需要全局配置
    public static final String PATH = "/Users/lgren/上传测试/document/";
    public static final String SAVE_TYPE = LOCAL;


    private static final File PATH_FILE = new File(PATH);

    /** 统一上传处理 */
    public static String upload(InputStream in, String name, String uploadType, OutputStream... out) throws IOException {
        String pathname = null;
        if (LOCAL.equals(uploadType)) {
            pathname = PATH + name;
            if (!PATH_FILE.exists()) {
                PATH_FILE.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(pathname);
            copyMoreOut(in, fileOut, out);
            try {
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pathname;
    }

    /** 将一个输入流同时写入多个输出流 */
    public static void copyMoreOut(InputStream in, OutputStream out, OutputStream... otherOut) throws IOException {
        try {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                for (OutputStream outVar : otherOut) {
                    outVar.write(buffer, 0, bytesRead);
                }
            }
            out.flush();
            for (OutputStream outVar : otherOut) {
                outVar.flush();
            }
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static InputStream download(String pathname, String uploadType) throws FileNotFoundException {
        InputStream in = null;
        if (LOCAL.equals(uploadType)) {
            in = new FileInputStream(pathname);
        }
        return in;
    }
}
