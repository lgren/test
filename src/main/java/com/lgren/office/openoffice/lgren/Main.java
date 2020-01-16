package com.lgren.office.openoffice.lgren;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * TODO
 * @author lgren
 * @since 2019-12-24 11:18
 */
public class Main {
    @Test
    public void name1() throws Exception {
        // OpenOfficeUtil.getInstance().office2Pdf(
        //         "/Users/lgren/Project/JavaIdeaSpace/test/src/main/resource/office/word_doc.doc",
        //         "word_doc");

        FileInputStream fileIn = new FileInputStream("/Users/lgren/Project/JavaIdeaSpace/test/src/main/resource/office/word_doc.doc");
        byte[] bytes = IOUtils.toByteArray(fileIn);
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        String outPathname = DocumentProperties.getOfficeToViewOutPath() + "word_doc" + DocumentProperties.getOfficeToViewOutType();
        File outFile = new File(outPathname);
        OpenOfficeUtil.getInstance().office2Pdf(
                in,
                new FileOutputStream(outFile));
        // 如果目标路径不存在, 则新建该路径
        if (!outFile.getParentFile().exists()) {
            outFile.getParentFile().mkdirs();
        }
    }

}
