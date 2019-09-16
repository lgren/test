package com.lgren.poi.poi3_17.new_read;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * TODO
 * @author lgren
 * @create 2019-05-17 16:56
 **/
public class ReadTest {

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Workbook wb = LWorkbookCommon.getWorkbook("/Users/lgren/Desktop/临时存放/test.xlsx");
        // Map<Integer, String> rowV = getRowV(wb.getSheetAt(0).getRow(1));
        Map<String, Object> rowV1 = LRowCommon.getRowV(wb.getSheetAt(0), 1, 0, (int[]) null);
        BaseClass rowV = LRowCommon.getRowV(wb.getSheetAt(0), 1, 0, BaseClass.class);


        System.out.println(wb.toString());/**/
        // int[] arr = {1, 2, 3, 4};
        int[] arr = null;
        ArrayUtils.contains(arr, 4);
    }


}
