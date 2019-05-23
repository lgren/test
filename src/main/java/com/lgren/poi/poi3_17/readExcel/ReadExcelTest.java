package com.lgren.poi.poi3_17.readExcel;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试
 * @author Lgren
 * @create 2018-11-30 10:25
 **/
public class ReadExcelTest {
    public ReadExcelTest() throws IOException {}

    Workbook wb = LReadCommon.getWorkbook(new FileInputStream("E:\\usr\\测试1.xlsx"));
    Workbook wb2 = LReadCommon.getWorkbook(new FileInputStream("E:\\usr\\测试2.xlsx"));


    private Map<Object, Object> cellKeyMap = new HashMap<>(11);

    {
        cellKeyMap.put(0, "自定义1");
        cellKeyMap.put(1, "自定义1");
        cellKeyMap.put(2, "自定义2");
        // cellKeyMapOfCol.put(3, "自定义3");
        // cellKeyMapOfCol.put(4, "自定义4");
        cellKeyMap.put(5, "自定义5");
        cellKeyMap.put(6, "自定义6");
        cellKeyMap.put(7, "自定义7");
        cellKeyMap.put(8, "自定义8");
        cellKeyMap.put(9, "自定义9");
        cellKeyMap.put(10, "自定义10");
        cellKeyMap.put(11, "自定义11");
        cellKeyMap.put(12, "自定义12");
    }

    private int[] skipIntArr = {0, 1, 2, 6};


    //region 公共工具类测试
    @Test
    public void LReadCommonCellTest() {
        System.out.println("---------------------获取cell值↓-----------------------");
        System.out.println("1:" + LReadCommon.getCellV(wb, "one", 1, 1));
        System.out.println("2:" + LReadCommon.getCellV(wb.getSheet("one"), 1, 2));
        System.out.println("3:" + LReadCommon.getCellV(wb.getSheet("one").getRow(1), 3));
        System.out.println("4:" + LReadCommon.getCellV(wb.getSheet("one").getRow(1).getCell(4)));
        System.out.println("---------------------获取cell值↑-----------------------");
    }

    @Test
    public void LReadCommonRowTest() {
        System.out.println("---------------------获取row(行)值↓-----------------------");
        System.out.println("1:" + LReadCommon.getRowV(wb.getSheet("one"), 1));// 获取"one"sheet的第二行
        System.out.println("2.1:" + LReadCommon.getRowV(wb.getSheet("one"), 1, cellKeyMap));// 自定义cell的key值
        System.out.println("2.2:" + LReadCommon.getRowV(wb.getSheet("one"), 1, 0));// 将第一行的值作为cell的key值
        System.out.println("3.1:" + LReadCommon.getRowV(wb.getSheet("one"), 1, 0, 0));// 将第一行的值作为cell的key值 + 跳过第一行
        System.out.println("3.2:" + LReadCommon.getRowV(wb.getSheet("one"), 1, 0, 0, 1, 2));// 将第一行的值作为cell的key值 + 跳过第一,二,三行
        System.out.println("3.3:" + LReadCommon.getRowV(wb.getSheet("one"), 1, 0, skipIntArr));// 将第一行的值作为cell的key值 + 跳过第一,二,三,七行
        System.out.println("---------------------获取row(行)值↑-----------------------");
    }

    @Test
    public void LReadCommonColTest() {
        System.out.println("---------------------获取col(列)值↓-----------------------");
        System.out.println("1:" + LReadCommon.getColV(wb.getSheet("one"), 1));// 获取"one"sheet的第二列
        System.out.println("2.1:" + LReadCommon.getColV(wb.getSheet("one"), 1, cellKeyMap));// 自定义cell的key值
        System.out.println("2.2:" + LReadCommon.getColV(wb.getSheet("one"), 1, 0));// 将第一列的值作为cell的key值
        System.out.println("3.1:" + LReadCommon.getColV(wb.getSheet("one"), 1, 0, 0));// 将第一列的值作为cell的key值 + 跳过第一列
        System.out.println("3.2:" + LReadCommon.getColV(wb.getSheet("one"), 1, 0, 0, 1, 2));// 将第一列的值作为cell的key值 + 跳过第一,二,三列
        System.out.println("3.3:" + LReadCommon.getRowV(wb.getSheet("one"), 1, 0, skipIntArr));// 将第一列的值作为cell的key值 + 跳过第一,二,三,七列
        System.out.println("---------------------获取Row值↑-----------------------");
    }

    @Test
    public void LReadCommonSheetGroupByRowTest() {
        System.out.println("---------------------获取sheet(通过行分组)值↓-----------------------");
        System.out.println("1:" + LReadCommon.getSheetV(wb.getSheet("one")));// 获取"one"sheet的所有值
        System.out.println("2:" + LReadCommon.getSheetV(wb.getSheet("one"), true));// 获取"one"sheet的所有值 + 将第一行, 第一列作为key值并排除掉第一行第一列
        System.out.println("2:" + LReadCommon.getSheetV(wb.getSheet("one"), 0, 1, 2, 3));// 将第一行作为row分组的key, 排除第二行, 将三列作为cell的key, 排除第四列
        System.out.println("2:" + LReadCommon.getSheetV(wb.getSheet("one"), 0, new int[]{1}, 2, new int[]{3}));// 将第一行作为row分组的key, 排除第二行, 将三列作为cell的key, 排除第四列
        System.out.println("2:" + LReadCommon.getSheetV(wb.getSheet("one"), cellKeyMap, skipIntArr, cellKeyMap, skipIntArr));// 将cellKeyMap作为row分组以及cell的key 并排除skipIntArr数组内容
        System.out.println("---------------------获取sheet(通过行分组)值↑-----------------------");
    }

    @Test
    public void LReadCommonSheetGroupByColTest() {
        System.out.println("---------------------获取sheet(通过列分组)值↓-----------------------");
        System.out.println("1:" + LReadCommon.getSheetVByCol(wb.getSheet("one")));// 获取"one"sheet的所有值
        System.out.println("2:" + LReadCommon.getSheetVByCol(wb.getSheet("one"), true));// 获取"one"sheet的所有值 + 将第一列, 第一行作为key值并排除掉第一列第一行
        System.out.println("2:" + LReadCommon.getSheetVByCol(wb.getSheet("one"), 0, 1, 2, 3));// 将第一列作为row分组的key, 排除第二列, 将三行作为cell的key, 排除第四行
        System.out.println("2:" + LReadCommon.getSheetVByCol(wb.getSheet("one"), 0, new int[]{1}, 2, new int[]{3}));// 将第一列作为row分组的key, 排除第二列, 将三行作为cell的key, 排除第四行
        System.out.println("2:" + LReadCommon.getSheetVByCol(wb.getSheet("one"), cellKeyMap, skipIntArr, cellKeyMap, skipIntArr));// 将cellKeyMap作为row分组以及cell的key 并排除skipIntArr数组内容
        System.out.println("---------------------获取sheet(通过列分组)值↑-----------------------");
    }
    //endregion

    @Test
    public void LRWorkbookTest() {
        LRWorkbook lrwb = new LRWorkbook(wb);

        System.out.println();
    }
}
