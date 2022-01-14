package com.lgren.office.aspose;

import cn.hutool.core.io.FileUtil;
import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.google.common.base.Stopwatch;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 顾东城
 * Date: 2019/2/21
 * Time: 14:01
 * Desc: 使用Aspose.Words将word文件转换为pdf文件
 */
public class AsposeWordTest {
  /**
   * word 转 pdf
   *
   * @param docPath 要转换的word文件路径
   * @param pdfPath 转换完成后输出的pdf文件路径
   */
  public static void doc2pdf(String docPath, String pdfPath) {
    if (!AsposeComm.getLicense()) {
      System.out.println("获取凭证失败");
      return;
    }
    try {
      Document convertDoc = new Document(new FileInputStream(docPath));
      Stopwatch watch = Stopwatch.createStarted();
      // convertDoc.save(pdfPath, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
      convertDoc.save(pdfPath, SaveFormat.HTML);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
      System.out.println(" 用时: " + watch);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * word 转 pdf
   *
   * @param srcStream  源文件的地址
   * @param tarPath    生成后的地址
   * @param tarName    生成后的名称
   * @param saveFormat {@link SaveFormat} 转换后的格式
   */
  public static void doc2pdfNew(InputStream srcStream, String tarPath, String tarName, int saveFormat) {
    if (!AsposeComm.getLicense()) {
      System.out.println("获取凭证失败");
      return;
    }
    try {
      Document convertDoc = new Document(srcStream);
      Stopwatch watch = Stopwatch.createStarted();
      //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
      convertDoc.save(tarPath + tarName + WORD_TYPE_MAP.get(saveFormat), saveFormat);
      System.out.println(" 用时: " + watch.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 名称对应的后缀
  private static final Map<Integer, String> WORD_TYPE_MAP = new HashMap<>(4);

  static {
    WORD_TYPE_MAP.put(SaveFormat.HTML, ".html");
    WORD_TYPE_MAP.put(SaveFormat.PDF, ".pdf");
    WORD_TYPE_MAP.put(SaveFormat.TEXT, ".txt");
    WORD_TYPE_MAP.put(SaveFormat.PNG, ".png");
    // WORD_TYPE_MAP.put(SaveFormat.UNKNOWN, );
    // WORD_TYPE_MAP.put(SaveFormat.DOC, );
    // WORD_TYPE_MAP.put(SaveFormat.DOT, );
    // WORD_TYPE_MAP.put(SaveFormat.DOCX, );
    // WORD_TYPE_MAP.put(SaveFormat.DOCM, );
    // WORD_TYPE_MAP.put(SaveFormat.DOTX, );
    // WORD_TYPE_MAP.put(SaveFormat.DOTM, );
    // WORD_TYPE_MAP.put(SaveFormat.FLAT_OPC, );
    // WORD_TYPE_MAP.put(SaveFormat.FLAT_OPC_MACRO_ENABLED, );
    // WORD_TYPE_MAP.put(SaveFormat.FLAT_OPC_TEMPLATE, );
    // WORD_TYPE_MAP.put(SaveFormat.FLAT_OPC_TEMPLATE_MACRO_ENABLED, );
    // WORD_TYPE_MAP.put(SaveFormat.RTF, );
    // WORD_TYPE_MAP.put(SaveFormat.WORD_ML, );
    // WORD_TYPE_MAP.put(SaveFormat.XPS, );
    // WORD_TYPE_MAP.put(SaveFormat.XAML_FIXED, );
    // WORD_TYPE_MAP.put(SaveFormat.SWF, );
    // WORD_TYPE_MAP.put(SaveFormat.SVG, );
    // WORD_TYPE_MAP.put(SaveFormat.HTML_FIXED, );
    // WORD_TYPE_MAP.put(SaveFormat.OPEN_XPS, );
    // WORD_TYPE_MAP.put(SaveFormat.PS, );
    // WORD_TYPE_MAP.put(SaveFormat.MHTML, );
    // WORD_TYPE_MAP.put(SaveFormat.EPUB, );
    // WORD_TYPE_MAP.put(SaveFormat.ODT, );
    // WORD_TYPE_MAP.put(SaveFormat.OTT, );
    // WORD_TYPE_MAP.put(SaveFormat.XAML_FLOW, );
    // WORD_TYPE_MAP.put(SaveFormat.XAML_FLOW_PACK, );
    // WORD_TYPE_MAP.put(SaveFormat.TIFF, );
    // WORD_TYPE_MAP.put(SaveFormat.BMP, );
    // WORD_TYPE_MAP.put(SaveFormat.JPEG, );
    // WORD_TYPE_MAP.put(SaveFormat.length, );
  }


  private static final String PATH = "/Users/lgren/Project/Java/0My/test/src/main/resource/office/";

  @SneakyThrows
  public static void main(String[] args) {
    // //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
    String name = "docx.docx";
    File file = new File(PATH + name);
    AsposeWordTest.doc2pdfNew(new FileInputStream(file), PATH + "aspose/", FileUtil.mainName(file), SaveFormat.HTML);
  }
}
