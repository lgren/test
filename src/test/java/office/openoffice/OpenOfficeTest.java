package office.openoffice;

import com.lgren.office.openoffice.simpleNew.OpenOfficeHandle;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.io.*;
import java.util.Optional;

/**
 * TODO
 * @author lgren
 * @since 2020-04-09 9:29 上午
 */
public class OpenOfficeTest {
    private static final String FILEPATH = "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/office/";
    private static final String TARGET_HTML = "html";
    private static final String TARGET_PDF = "pdf";
    private static final String HTML_OUT_FILEPATH = "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/office/out/OpenOffice/" + TARGET_HTML + "/";
    private static final String PDF_OUT_FILEPATH = "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/office/out/OpenOffice/" + TARGET_PDF + "/";

    private static final String DOC = "doc.doc";
    private static final String DOCX = "docx.docx";
    private static final String XLS = "xls.xls";
    private static final String XLSX = "xlsx.xlsx";
    private static final String PPT = "ppt.ppt";
    private static final String PPTX = "pptx.pptx";

    // 初始化新建地址
    static {
        Optional.of(new File(FILEPATH)).filter(f -> !f.exists()).ifPresent(File::mkdirs);
        Optional.of(new File(HTML_OUT_FILEPATH)).filter(f -> !f.exists()).ifPresent(File::mkdirs);
        Optional.of(new File(PDF_OUT_FILEPATH)).filter(f -> !f.exists()).ifPresent(File::mkdirs);
    }

    //region 转html
    @Test
    public void xls2Html() throws FileNotFoundException {
        convertBase(XLS, TARGET_HTML);
    }

    @Test
    public void xlsx2Html() throws FileNotFoundException {
        convertBase(XLSX, TARGET_HTML);
    }

    @Test
    public void doc2Html() throws FileNotFoundException {
        convertBase(DOC, TARGET_HTML);
    }

    @Test
    public void docx2Html() throws FileNotFoundException {
        convertBase(DOCX, TARGET_HTML);
    }

    // @Test
    // public void ppt2Html() throws FileNotFoundException {
    //     convertBase(PPT, TARGET_HTML);
    // }
    //
    // @Test
    // public void pptx2Html() throws FileNotFoundException {
    //     convertBase(PPTX, TARGET_HTML);
    //
    // }
    //endregion

    //region 转pdf
    @Test
    public void xls2Pdf() throws FileNotFoundException {
        convertBase(XLS, TARGET_PDF);
    }

    @Test
    public void xlsx2Pdf() throws FileNotFoundException {
        convertBase(XLSX, TARGET_PDF);
    }

    @Test
    public void doc2Pdf() throws FileNotFoundException {
        convertBase(DOC, TARGET_PDF);
    }

    @Test
    public void docx2Pdf() throws FileNotFoundException {
        convertBase(DOCX, TARGET_PDF);
    }

    // @Test
    // public void ppt2Pdf() throws FileNotFoundException {
    //     convertBase(PPT, TARGET_PDF);
    // }
    //
    // @Test
    // public void pptx2Pdf() throws FileNotFoundException {
    //     convertBase(PPTX, TARGET_PDF);
    //
    // }
    //endregion

    private static void convertBase(String ori, String target) throws FileNotFoundException {
        InputStream in = new FileInputStream(FILEPATH + ori);
        OutputStream out = new FileOutputStream(HTML_OUT_FILEPATH + ori + "." + target);

        OpenOfficeHandle handle = new OpenOfficeHandle().connect("192.168.79.34", 8100);
        handle.convert(in, FilenameUtils.getExtension(ori), out, target);
        handle.destroy();
    }
}
