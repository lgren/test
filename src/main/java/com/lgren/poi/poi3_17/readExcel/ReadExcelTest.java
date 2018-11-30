package com.lgren.poi.poi3_17.readExcel;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * TODO
 * @author Lgren
 * @create 2018-11-30 10:25
 **/
public class ReadExcelTest {
    public static void main(String[] args) throws IOException {
        InputStream wbInp = new FileInputStream("D:\\Documents\\Desktop\\测试(2).xlsx");
        Workbook wb = LReadWorkbook.getWorkbook(wbInp).getData();
        LReadWorkbook lrwb = new LReadWorkbook(wb);

        InputStream wbInp2 = new FileInputStream("E:\\usr\\testttt.xlsx");
        Workbook wb2 = LReadWorkbook.getWorkbook(wbInp2).getData();
        LReadWorkbook lrwb2 = new LReadWorkbook(wb2);

        lrwb2.sheet("sheet").row(2).cell(1);
        System.out.println();
    }
}
