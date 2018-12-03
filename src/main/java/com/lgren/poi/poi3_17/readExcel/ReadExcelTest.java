package com.lgren.poi.poi3_17.readExcel;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 测试
 * @author Lgren
 * @create 2018-11-30 10:25
 **/
public class ReadExcelTest {
    @Test
    public void LRWorkbookTest() throws IOException {
        LRWorkbook lrwb = new LRWorkbook(new FileInputStream("E:\\usr\\测试1.xlsx"));
        LRWorkbook lrwb2 = new LRWorkbook(new FileInputStream("E:\\usr\\测试2.xlsx"));

        lrwb2.sheet("one").getValueWithFirstKey();
        System.out.println();
    }
}
