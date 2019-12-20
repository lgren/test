package com.lgren.office.aspose;

import com.aspose.pdf.Document;
import com.aspose.pdf.License;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By 顾东城
 * Date: 2019/2/22
 * Time: 9:24
 * Desc: 使用Aspose.Pdf将多个pdf合并为一个pdf文件
 */
public class AsposePdfTest {

    /**
     *
     * @param pdfPaths 要合并的pdf文件路径
     * @param mergeSavePath 最终合并完成输出的pdf文件路径
     */
    public static void pdfMergePdf(List<String> pdfPaths,String mergeSavePath){
        try {
            Document mergeDocument = new Document();
            for (String pdfPath : pdfPaths) {
                Document document = new Document(new FileInputStream(pdfPath));
                mergeDocument.getPages().add(document.getPages());
            }
            mergeDocument.save(mergeSavePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //将需要合并的pdf文件放入list
        List<String> pdfPaths = Arrays.asList("H:/testDoc.pdf","H:/testExcel.pdf");
        //在硬盘直接创建一个空白pdf即可
        AsposePdfTest.pdfMergePdf(pdfPaths,"H:/merge.pdf");
    }
}
