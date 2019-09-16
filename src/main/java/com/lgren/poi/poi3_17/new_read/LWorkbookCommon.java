package com.lgren.poi.poi3_17.new_read;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 * TODO
 * @author lgren
 * @create 2019-05-23 15:41
 **/
public class LWorkbookCommon {
    public static Workbook getWorkbook(String name) throws IOException {
        return getWorkbook(new FileInputStream(name));
    }

    public static Workbook getWorkbook(InputStream inp) throws IOException {
        Workbook workbook;
        try (InputStream mpbinp = inp.markSupported() ? inp : new PushbackInputStream(inp, 8);
             InputStream newInp = FileMagic.prepareToCheckMagic(mpbinp)) {
            FileMagic fileMagic = FileMagic.valueOf(newInp);
            if (fileMagic == FileMagic.OLE2) {
                try {
                    POIFSFileSystem fs = new POIFSFileSystem(newInp);
                    workbook = new HSSFWorkbook(fs);
                } catch (Exception e) {
                    throw new RuntimeException("xls读取失败！");
                }
            } else if (fileMagic == FileMagic.OOXML) {
                try {
                    workbook = new XSSFWorkbook(newInp);
                } catch (Exception e) {
                    throw new RuntimeException("xlsx读取失败！");
                }
            } else {
                throw new RuntimeException("不支持的文件类型！");
            }
        }
        return workbook;
    }

}
