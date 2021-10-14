//package com.lgren.office.aspose;
//
//import com.aspose.cells.SaveFormat;
//import com.aspose.cells.Workbook;
//import com.google.common.base.Stopwatch;
//import org.apache.commons.lang3.StringUtils;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created with IntelliJ IDEA
// * Created By 顾东城
// * Date: 2019/2/22
// * Time: 9:01
// * Desc: 使用Aspose.Cells将excel文件转换为pdf文件
// */
//public class AsposeExcelTest {
//
//    /**
//     * excel 转 pdf
//     * @param excelPath 要转换的excel文件路径
//     * @param pdfPath   转换完成后输出的pdf文件路径
//     */
//    public static void excel2pdf(String excelPath,String pdfPath) {
//        // if (!AsposeComm.getLicense()) {
//        //     System.out.println("获取凭证失败");
//        //     return;
//        // }
//        try {
//            Workbook convertExcel = new Workbook(new FileInputStream(excelPath));
//            convertExcel.save(pdfPath, SaveFormat.PDF);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * excel 转 pdf
//     * @param srcStream 源文件的地址
//     * @param tarPath 生成后的地址
//     * @param tarName 生成后的名称
//     * @param saveFormat {@link SaveFormat} 转换后的格式
//     */
//    public static void excel2pdfNew(InputStream srcStream, String tarPath, String tarName, int saveFormat) {
//        if (!AsposeComm.getLicense()) {
//            System.out.println("获取凭证失败");
//            return;
//        }
//        try {
//            Workbook convertExcel = new Workbook(srcStream);
//            Stopwatch watch = Stopwatch.createStarted();
//            //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
//            convertExcel.save(tarPath + tarName + EXCEL_TYPE_MAP.get(saveFormat), saveFormat);
//            System.out.println(" 用时: " + watch.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    // 名称对应的后缀
//    private static final Map<Integer, String> EXCEL_TYPE_MAP = new HashMap<>(4);
//    static {
//        EXCEL_TYPE_MAP.put(SaveFormat.HTML, ".html");
//        EXCEL_TYPE_MAP.put(SaveFormat.PDF, ".pdf");
//        // EXCEL_TYPE_MAP.put(SaveFormat.TEXT, ".txt");// word
//        // EXCEL_TYPE_MAP.put(SaveFormat.PNG, ".png");// word
//
//        // EXCEL_TYPE_MAP.put(SaveFormat.CSV, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.XLSX, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.XLSM, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.XLTX, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.XLTM, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.XLAM, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.TAB_DELIMITED, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.HTML, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.M_HTML, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.ODS, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.EXCEL_97_TO_2003, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.SPREADSHEET_ML, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.XLSB, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.AUTO, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.UNKNOWN, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.PDF, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.XPS, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.TIFF, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.SVG, );
//        // EXCEL_TYPE_MAP.put(SaveFormat.DIF, );
//
//    }
//}
