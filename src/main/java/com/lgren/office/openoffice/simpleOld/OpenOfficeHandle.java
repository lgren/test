package com.lgren.office.openoffice.simpleOld;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import lombok.Getter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 将office转化为PDF;
 */
public class OpenOfficeHandle {
    @Getter
    private OpenOfficePool pool;

    private void init() {
        GenericObjectPoolConfig<OpenOfficeConnection> poolConfig = new GenericObjectPoolConfig<>();
        // 最大连接数
        poolConfig.setMaxTotal(8);// openoffice.maxTotal
        // 最大空闲连接数
        poolConfig.setMaxIdle(8);// openoffice.maxIdle
        // 最小空闲连接数
        poolConfig.setMinIdle(1);// openoffice.minIdle
        pool = new OpenOfficePool(new OpenOfficeConnectionFactory("192.168.79.34", 8100));
    }

    public OpenOfficeHandle() {
        this.init();
    }

    /**
     * office文档转html, pdf等
     * @param sourceFile office文档绝对路径
     * @param destFile   pdf文件绝对路径
     */
    public void convert(String sourceFile, String destFile) {
        OpenOfficeConnection connection = pool.borrowObject();
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
        OpenOfficeConnection connection = pool.borrowObject();
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

    public static void main(String[] args) {
        OpenOfficeHandle handle = new OpenOfficeHandle();
        handle.convert(
                "/Users/lgren/Desktop/文档管理计划.xlsx",
                "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/office/out/OpenOffice/pdf/文档管理计划.pdf");
        handle.getPool().clear();
    }
}
