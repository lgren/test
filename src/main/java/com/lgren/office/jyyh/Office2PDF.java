package com.lgren.office.jyyh;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * 将office转化为PDF;
 */
@Slf4j
public class Office2PDF {
    private static final String HOST = OfficeConnection.HOST;
    private static final Integer PORT = OfficeConnection.PORT;

    /**
     * office文档转pdf文件
     *
     * @param sourceFile office文档绝对路径
     * @param destFile   pdf文件绝对路径
     * @return
     */
    public static void office2PDF(String sourceFile, String destFile) {
        // OfficeConnection officeConnection = OfficeUtil.getOfficeConnection();
        OfficeConnection officeConnection = new OfficeConnection();
        OpenOfficeConnection connection = officeConnection.getConnection();
        File inputFile = new File(sourceFile);
        if (!inputFile.exists()) {
            throw new RuntimeException("找不到源文件");
        }
        if (!connection.isConnected()) {
            throw new RuntimeException("OpenOffice服务未连接!");
        }
        // 如果目标路径不存在, 则新建该路径
        File outputFile = new File(destFile);
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }

//        try {
//            System.out.println("开始连接:"+System.currentTimeMillis());
//            // 连接openoffice服务
//            connection = new SocketOpenOfficeConnection(HOST, PORT);
//            connection.connect();
//            System.out.println("连接成功:"+System.currentTimeMillis());
//        } catch (IOException e) {
//            LOGGER.error(e.getMessage(),e);
////            e.printStackTrace();
//            throw new AppException("openoffice服务连接失败!");
//        }
        try {
            // 转换
            DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
//            e.printStackTrace();
            throw new RuntimeException("文件转换错误");
        } finally {
            // 关闭连接和服务
            officeConnection.destroy();
//             OfficeUtil.returnOfficeConnection(officeConnection);
        }


        // try {
        //     File inputFile = new File(sourceFile);
        //     if (!inputFile.exists()) {
        //         throw new AppException("找不到源文件");
        //     }
        //     // 如果目标路径不存在, 则新建该路径
        //     File outputFile = new File(destFile);
        //     if (!outputFile.getParentFile().exists()) {
        //         outputFile.getParentFile().mkdirs();
        //     }
        //     // 启动OpenOffice的服务
        //     //String command = OpenOffice_HOME+ "program\\soffice.exe -headless -accept=\"socket,host=" + host_Str + ",port=" + port_Str + ";urp;\"";
        //     //System.out.println("###\n" + command);
        //     //pro = Runtime.getRuntime().exec(command);
        //     // 连接openoffice服务
        //     connection = new SocketOpenOfficeConnection(HOST, PORT);
        //     connection.connect();
        //     // 转换
        //     DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
        //     converter.convert(inputFile, outputFile);
        // } catch (IOException e) {
        //     e.printStackTrace();
        //     throw new AppException("文件转换错误");
        // } finally {
        //     // 关闭连接和服务
        //     if (connection != null) {
        //         connection.disconnect();
        //     }
        // }
    }
}
