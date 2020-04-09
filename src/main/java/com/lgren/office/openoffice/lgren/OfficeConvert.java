package com.lgren.office.openoffice.lgren;

import com.artofsolving.jodconverter.*;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 将office转化为PDF;
 */
@Slf4j
public class OfficeConvert {
    // 获取连接的方式 1.直接连接 2.通过线程池 默认1
    public static final int CONN_TYPE_LINK = 1;
    public static final int CONN_TYPE_POOL = 2;
    @Getter@Setter
    private static int connType = CONN_TYPE_LINK;
    /**
     * office文档转html, pdf等
     * @param sourceFile office文档绝对路径
     * @param destFile   pdf文件绝对路径
     */
    public static void convert(String sourceFile, String destFile) {
        OfficeConnection officeConnection = Objects.equals(connType, CONN_TYPE_POOL)
                ? OfficeHandle.getPool().getConnection()
                : new OfficeConnection(OfficeHandle.HOST, OfficeHandle.PORT);
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
        Optional.of(outputFile.getParentFile()).filter(f -> !f.exists()).ifPresent(File::mkdirs);
        try {
            // 转换
            DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, getDocumentFormat(inputFile),
                    outputFile, getDocumentFormat(outputFile));
        } catch (Exception e) {
            throw new RuntimeException("文件转换错误", e);
        } finally {
            // 关闭连接和服务
            if (Objects.equals(connType, CONN_TYPE_POOL)) {
                OfficeHandle.getPool().returnConnection(officeConnection);
            } else {
                officeConnection.destroy();
            }
        }
    }

    /**
     * office文档转html, pdf等
     * @param in 源输入流
     * @param inExtension 源类型 例如: xls, xlsx, doc, docx
     * @param out 目标输出流
     * @param outExtension 目标类型 例如: html, pdf
     */
    public static void convert(InputStream in, String inExtension, OutputStream out, String outExtension) {
        OfficeConnection officeConnection = Objects.equals(connType, CONN_TYPE_POOL)
                ? OfficeHandle.getPool().getConnection()
                : new OfficeConnection(OfficeHandle.HOST, OfficeHandle.PORT);
        OpenOfficeConnection connection = officeConnection.getConnection();
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
            // 关闭连接和服务
            if (Objects.equals(connType, CONN_TYPE_POOL)) {
                OfficeHandle.getPool().returnConnection(officeConnection);
            } else {
                officeConnection.destroy();
            }
        }
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

    private static DocumentFormat getDocumentFormat(File file) {
        String extension = FilenameUtils.getExtension(file.getName());
        return Holder.FORMAT_MAP.get(extension);
    }
}
