package com.lgren.office.aspose;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * TODO
 * @author lgren
 * @since 2019-12-20 14:10
 */
public class Main {
    private static final String PATH = "/Users/lgren/Project/JavaIdeaSpace/test/src/main/resource/aspose/";

    @Test
    public void name1() throws FileNotFoundException {
        // //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
        String name = "新版社保卡+公积金卡办理攻略.docx";
        File file = new File(PATH + name);
        String fileName = file.getName();
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        AsposeWordTest.doc2pdfNew(new FileInputStream(file), PATH, fileName, com.aspose.words.SaveFormat.HTML);
    }

    @Test
    public void name2() throws FileNotFoundException {
        String name = "文档管理设计.xlsx";
        File file = new File(PATH + name);
        String fileName = file.getName();
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        AsposeExcelTest.excel2pdfNew(new FileInputStream(file), PATH, fileName, com.aspose.cells.SaveFormat.HTML);
    }

    @Test
    public void name3() throws FileNotFoundException {
        String name = "3-配置文件详解.ppt";
        File file = new File(PATH + name);
        String fileName = file.getName();
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        AsposePptTest.ppt2pdfNew(new FileInputStream(file), PATH, fileName, com.aspose.slides.SaveFormat.Html);
    }

    // @Test
    // public void name3() {
    //     //将需要合并的pdf文件放入list
    //     List<String> pdfPaths = Arrays.asList("H:/testDoc.pdf","H:/testExcel.pdf");
    //     //在硬盘直接创建一个空白pdf即可
    //     AsposePdfTest.pdfMergePdf(pdfPaths,"H:/merge.pdf");
    // }

}
