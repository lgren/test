package com.lgren.office.openoffice.lgren;

import com.artofsolving.jodconverter.*;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.*;
import java.net.ConnectException;

@Slf4j
public class OpenOfficeUtil {
    private GenericObjectPool<OpenOfficeConnection> objectPool;
    private static class OpenOfficeUtilGet {
        private static final OpenOfficeUtil INSTANCE;
        static {
            INSTANCE = new OpenOfficeUtil();
            OpenOfficeConnFactory officeConnectionFactory = new OpenOfficeConnFactory(
                    DocumentProperties.getOpenOfficeHost(),
                    DocumentProperties.getOpenOfficePost());
            // 设置对象池的相关参数
            GenericObjectPoolConfig<OpenOfficeConnection> poolConfig = new GenericObjectPoolConfig<>();
            // 最大连接数
            poolConfig.setMaxTotal(DocumentProperties.getOpenOfficeMaxTotal());
            // 最大空闲连接数
            poolConfig.setMaxIdle(DocumentProperties.getOpenOfficeMaxIdle());
            // 最小空闲连接数
            poolConfig.setMinIdle(DocumentProperties.getOpenOfficeMinIdle());
            // 新建一个对象池,传入对象工厂和配置
            INSTANCE.objectPool = new GenericObjectPool<>(officeConnectionFactory, poolConfig);
        }
    }
    public static OpenOfficeUtil getInstance() {
        return OpenOfficeUtilGet.INSTANCE;
    }


    /**
     * office文档转pdf文件
     * @param sourceFile office文档绝对路径
     * @param outName    pdf文件绝对路径
     */
    public String office2Pdf(String sourceFile, String outName) throws Exception {
        OpenOfficeConnection connection;
        try {
            connection = objectPool.borrowObject();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("获取连接错误!");
        }
        File inputFile = new File(sourceFile);
        if (!inputFile.exists()) {
            throw new RuntimeException("找不到源文件");
        }
        if (!connection.isConnected()) {
            throw new RuntimeException("OpenOffice服务未连接!");
        }
        String outPathname = DocumentProperties.getOfficeToViewOutPath() + outName + DocumentProperties.getOfficeToViewOutType();
        File outFile = new File(outPathname);
        // 如果目标路径不存在, 则新建该路径
        if (!outFile.getParentFile().exists()) {
            outFile.getParentFile().mkdirs();
        }
        try {
            // 转换
            DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outFile);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("文件转换错误!");
        } finally {
            // 归还连接和服务
            objectPool.returnObject(connection);
        }
        return outPathname;
    }


    /**
     * office文档转pdf文件
     */
    public String office2Pdf(InputStream in, OutputStream out) throws Exception {
        OpenOfficeConnection connection;
        try {
            // connection = objectPool.borrowObject();
            connection = new SocketOpenOfficeConnection(
                    DocumentProperties.getOpenOfficeHost(),
                    DocumentProperties.getOpenOfficePost());
            connection.connect();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("获取连接错误!");
        }
        if (!connection.isConnected()) {
            throw new RuntimeException("OpenOffice服务未连接!");
        }
        try {
            // 转换
            DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
            DocumentFormatRegistry factory = new DefaultDocumentFormatRegistry();
            DocumentFormat inFormat = factory.getFormatByFileExtension("doc");
            DocumentFormat outFormat = factory.getFormatByFileExtension("pdf");
            converter.convert(in, inFormat, out, outFormat);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("文件转换错误!");
        } finally {
            // 归还连接和服务
            // objectPool.returnObject(connection);
            connection.disconnect();
        }
        return "ok";
    }
}
