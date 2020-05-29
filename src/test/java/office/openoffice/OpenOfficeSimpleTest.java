package office.openoffice;

import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.google.common.collect.Sets;
import com.lgren.office.openoffice.simpleNew.OpenOfficeHandle;
import com.lgren.pool.Simple.SimpleConnection;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO
 * @author lgren
 * @since 2020-04-09 9:29 上午
 */
public class OpenOfficeSimpleTest {
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
    private OpenOfficeHandle handle = new OpenOfficeHandle().connect("192.168.79.34", 8100);

    private void convertBase(String ori, String target) {
        convertBase(ori, target, true);
    }

    private void convertBase(String ori, String target, boolean destroy) {
        String pathName = (Objects.equals(target, TARGET_HTML) ? HTML_OUT_FILEPATH : PDF_OUT_FILEPATH) + ori + "." + target;
        try(InputStream in = new FileInputStream(FILEPATH + ori);
            OutputStream out = new FileOutputStream(pathName)) {
            handle.convert(in, FilenameUtils.getExtension(ori), out, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("完成: %s\n", pathName);
        if (destroy) {
            handle.destroy();
        }
    }

    @Test
    public void control() throws InterruptedException {
        handle = new OpenOfficeHandle();
        GenericObjectPoolConfig<OpenOfficeConnection> poolConfig = handle.getPoolConfig();
        poolConfig.setMaxTotal(8);// 最大连接数
        handle.connect("192.168.79.34", 8100);

        Map<String, Set<String>> map = new HashMap<>(3);
        map.put(TARGET_HTML, Sets.newHashSet(XLS, XLSX, DOC, DOCX));
        map.put(TARGET_PDF, Sets.newHashSet(XLS, XLSX, DOC, DOCX));
        ExecutorService exe = Executors.newFixedThreadPool(8);
        CountDownLatch run = new CountDownLatch(8);
        map.forEach((target, set) -> set.forEach(type -> {
            exe.submit(() -> {
                convertBase(type, target, false);
                run.countDown();
            });
        }));
        run.await();
        exe.shutdown();
        handle.destroy();
    }
}
