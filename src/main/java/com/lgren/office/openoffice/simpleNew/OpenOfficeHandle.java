package com.lgren.office.openoffice.simpleNew;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import lombok.Getter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 将office转化为PDF;
 */
@Component
public class OpenOfficeHandle {
    @Getter
    private final GenericObjectPoolConfig<OpenOfficeConnection> poolConfig = new GenericObjectPoolConfig<>();
    {
        poolConfig.setMaxTotal(5);// 最大连接数
        poolConfig.setMaxIdle(5);// 最大空闲连接数
        poolConfig.setMinIdle(1);// 最小空闲连接数
    }
    private GenericObjectPool<OpenOfficeConnection> pool;

    /**
     * office文档转html, pdf等
     * @param sourceFile office文档绝对路径
     * @param destFile   pdf文件绝对路径
     */
    public void convert(String sourceFile, String destFile) {
        OpenOfficeConnection connection = getConnection();
        File inputFile = new File(sourceFile);
        if (!inputFile.exists()) {
            throw new RuntimeException("找不到源文件");
        }
        if (!connection.isConnected()) {
            throw new RuntimeException("OpenOffice服务未连接!");
        }
        // 如果目标路径不存在, 则新建该路径
        File outputFile = new File(destFile);
        Optional.of(outputFile.getParentFile()).filter(f -> !f.exists()).ifPresent(File::mkdirs);
        try {
            // 转换
            DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, getDocumentFormat(inputFile),
                    outputFile, getDocumentFormat(outputFile));
        } catch (Exception e) {
            throw new RuntimeException("文件转换错误", e);
        } finally {
            pool.returnObject(connection);
        }
    }

    /**
     * office文档转html, pdf等
     * @param in 源输入流
     * @param inExtension 源类型 例如: xls, xlsx, doc, docx
     * @param out 目标输出流
     * @param outExtension 目标类型 例如: html, pdf
     */
    public void convert(InputStream in, String inExtension, OutputStream out, String outExtension) {
        OpenOfficeConnection connection = getConnection();
        if (in == null) {
            throw new RuntimeException("源数据流为空");
        }
        if (!connection.isConnected()) {
            throw new RuntimeException("OpenOffice服务未连接!");
        }
        if (out == null) {
            throw new RuntimeException("目标数据流为空");
        }
        try {
            // 转换
            DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
            converter.convert(in, Holder.FORMAT_MAP.get(inExtension),
                    out, Holder.FORMAT_MAP.get(outExtension));
        } catch (Exception e) {
            throw new RuntimeException("文件转换错误", e);
        } finally {
            pool.returnObject(connection);
        }
    }

    // @PostConstruct
    // public void init() {
    //     poolConfig.setMaxTotal(5);// 最大连接数
    //     poolConfig.setMaxIdle(5);// 最大空闲连接数
    //     poolConfig.setMinIdle(1);// 最小空闲连接数
    //     connect(host, port);
    // }

    public OpenOfficeHandle connect(String host, Integer port) {
        System.out.println("OpenOfficeHandle 初始化开始");
        pool = new GenericObjectPool<>(new OpenOfficeConnectionFactory(host, port), poolConfig);
        System.out.println("OpenOfficeHandle 初始化结束");
        return this;
    }

    @PreDestroy
    public void destroy(){
        System.out.println("OpenOfficeHandle 摧毁开始");
        pool.close();
        System.out.println("OpenOfficeHandle 摧毁结束");
    }

    private OpenOfficeConnection getConnection() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private DocumentFormat getDocumentFormat(File file) {
        String extension = FilenameUtils.getExtension(file.getName());
        return Holder.FORMAT_MAP.get(extension);
    }

    private static class Holder extends DefaultDocumentFormatRegistry {
        private static final Map<String, DocumentFormat> FORMAT_MAP;
        static {
            Holder registry = new Holder();
            List documentFormats = registry.getDocumentFormats();
            Map<String, DocumentFormat> map = new HashMap<>(documentFormats.size());
            for (Object documentFormatVar : documentFormats) {
                DocumentFormat documentFormat = (DocumentFormat) documentFormatVar;
                map.put(documentFormat.getFileExtension(), documentFormat);
            }
            DocumentFormat xlsx = new DocumentFormat("Microsoft Excel 2007 XML", DocumentFamily.SPREADSHEET, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx");
            map.put("xlsx", xlsx);
            DocumentFormat docx = new DocumentFormat("Microsoft Word 2007 XML", DocumentFamily.TEXT, "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx");
            map.put("docx", docx);
            DocumentFormat pptx = new DocumentFormat("Microsoft PowerPoint 2007 XML", DocumentFamily.PRESENTATION, "application/vnd.openxmlformats-officedocument.presentationml.presentation", "pptx");
            map.put("pptx", pptx);
            FORMAT_MAP = map;
        }
    }
}
