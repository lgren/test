package com.lgren.office.aspose;

import com.aspose.slides.License;
import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import com.google.common.base.Stopwatch;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Created By 顾东城
 * Date: 2019/2/21
 * Time: 14:01
 * Desc: 使用Aspose.Words将word文件转换为pdf文件
 */
public class AsposePptTest {
    /**
     * word 转 pdf
     * @param srcStream 源文件的地址
     * @param tarPath 生成后的地址
     * @param tarName 生成后的名称
     * @param saveFormat {@link SaveFormat} 转换后的格式
     */
    public static void convert(InputStream srcStream, String tarPath, String tarName, int saveFormat) {
        try {
            //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            new Presentation(srcStream).save(tarPath + tarName + PPT_TYPE_MAP.get(saveFormat), saveFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 名称对应的后缀
    private static final Map<Integer, String> PPT_TYPE_MAP = new HashMap<>(4);
    static {
        PPT_TYPE_MAP.put(SaveFormat.Html, ".html");
        PPT_TYPE_MAP.put(SaveFormat.Pdf, ".pdf");

        // PPT_TYPE_MAP.put(SaveFormat.Ppt, );
        // PPT_TYPE_MAP.put(SaveFormat.Pdf, );
        // PPT_TYPE_MAP.put(SaveFormat.Xps, );
        // PPT_TYPE_MAP.put(SaveFormat.Pptx, );
        // PPT_TYPE_MAP.put(SaveFormat.Ppsx, );
        // PPT_TYPE_MAP.put(SaveFormat.Tiff, );
        // PPT_TYPE_MAP.put(SaveFormat.Odp, );
        // PPT_TYPE_MAP.put(SaveFormat.Pptm, );
        // PPT_TYPE_MAP.put(SaveFormat.Ppsm, );
        // PPT_TYPE_MAP.put(SaveFormat.Potx, );
        // PPT_TYPE_MAP.put(SaveFormat.Potm, );
        // PPT_TYPE_MAP.put(SaveFormat.Html, );
        // PPT_TYPE_MAP.put(SaveFormat.Swf, );
        // PPT_TYPE_MAP.put(SaveFormat.Otp, );
        // PPT_TYPE_MAP.put(SaveFormat.Pps, );
    }
}
