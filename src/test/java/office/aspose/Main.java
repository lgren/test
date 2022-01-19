package office.aspose;

import cn.hutool.core.io.FileUtil;
import com.aspose.cells.SaveOptions;
import com.aspose.cells.Workbook;
import com.aspose.slides.Html5Options;
import com.aspose.slides.HtmlOptions;
import com.aspose.slides.Presentation;
import com.aspose.words.Document;
import com.aspose.words.HtmlSaveOptions;
import com.aspose.words.SaveFormat;
import com.lgren.office.aspose.*;
import com.lgren.util.LgrenUtil;
import lombok.SneakyThrows;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
* TODO
* @author lgren
* @since 2019-12-20 14:10
*/
public class Main {
    private static final String PATH = "/Users/lgren/Project/Java/0My/test/src/main/resource/office/";

    @Test
    public void wordConvert() throws Exception {
        LgrenUtil.timer(d -> com.aspose.words.License.licenseGo());
        String name = "docx.docx";
        File file = new File(PATH + name);

        InputStream in = new BufferedInputStream(new FileInputStream(file));
        in.mark(Math.toIntExact(file.length() + 1));
        Document doc = new Document(in);
        // 图片使用base64形式
        HtmlSaveOptions options = new HtmlSaveOptions(SaveFormat.HTML);
        options.setExportImagesAsBase64(true);

        // 使用1: 转换成字节数组 再转换成字符串
        try (ByteArrayOutputStream out = new ByteArrayOutputStream(2048)) {
            doc.save(out, options);
            String content = new String(out.toByteArray(), StandardCharsets.UTF_8);
            in.reset();
        }

        // 使用2: 保存到本地
        doc.save(String.format("%saspose/%s%s", PATH, FileUtil.mainName(name), ".html"), options);

        in.close();
    }

   @Test
   public void excelConvert() throws Exception {
       LgrenUtil.timer(d -> com.aspose.cells.License.licenseGo());
       String name = "xlsx.xlsx";
       File file = new File(PATH + name);

       InputStream in = new BufferedInputStream(new FileInputStream(file));
       in.mark(Math.toIntExact(file.length() + 1));
       Workbook wb = new Workbook(in);
       com.aspose.cells.HtmlSaveOptions options = new com.aspose.cells.HtmlSaveOptions(com.aspose.cells.SaveFormat.HTML);
       options.setExportImagesAsBase64(true);

       // options.setAttachedFilesDirectory("/Users/lgren/Project/Java/0My/test/src/main/resource/office/aspose/test/");
       // // 使用1: 转换成字节数组 再转换成字符串
       // try (ByteArrayOutputStream out = new ByteArrayOutputStream(2048)) {
       //     wb.save(out, options);
       //     System.out.println(new String(out.toByteArray(), StandardCharsets.UTF_8));
       //     in.reset();
       // }

       // 使用2: 保存到本地
       wb.save(String.format("%saspose/%s%s", PATH, FileUtil.mainName(name), ".html"), options);

       in.close();

   }

    @Test
    public void pptConvert() throws Exception {
        LgrenUtil.timer(d -> com.aspose.slides.License.licenseGo());
        String name = "ppt.ppt";
        File file = new File(PATH + name);

        InputStream in = new BufferedInputStream(new FileInputStream(file));
        in.mark(Math.toIntExact(file.length() + 1));
        Presentation doc = new Presentation(in);

        // 使用1: 转换成字节数组 再转换成字符串
        try (ByteArrayOutputStream out = new ByteArrayOutputStream(2048)) {
            doc.save(out, com.aspose.slides.SaveFormat.Html5);
            String content = new String(out.toByteArray(), StandardCharsets.UTF_8);
            in.reset();
        }

        // 使用2: 保存到本地
        doc.save(String.format("%saspose/%s%s", PATH, FileUtil.mainName(name), ".html"), com.aspose.slides.SaveFormat.Html5);

        in.close();
    }

    @Test
    public void ocr() throws FileNotFoundException {
        LgrenUtil.timer(d -> com.aspose.ocr.License.licenseGo());
        String name = "ocr_chi.png";
        File file = new File(PATH + name);
        System.out.println(AsposeOcr.ocr(new FileInputStream(file)));
    }

    @Test
    public void name() {
        System.out.println();
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        AsposeBase.setLicense();
    }
}
