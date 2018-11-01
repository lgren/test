package com.lgren.poi.poi3_17;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-10-30 10:31
 **/
public class LgrenExcelTest {
    public static void main(String[] args) {
        LWorkbook lxwb = new LWorkbook(LWorkbook.XLSX);
        //region 字体样式
        LWorkbook.LFont font = lxwb.font()
                .setColor(Font.COLOR_RED)// 设置字体,红色
                .setBold(true)// 字体粗体显示
                .setFontName("宋体")// 字体类型
                .setFontHeightInPoints((short) 22);// 字体大小
        //endregion

        //region 设置样式
        LWorkbook.LCellStyle cellStyle = lxwb.cellStyle()
                .setFont(font)
                //设置单元格背景色
                .setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex())
                .setFillPattern(FillPatternType.SOLID_FOREGROUND)
                //设置居中
                .setAlignment(HorizontalAlignment.CENTER)//水平居中
                .setVerticalAlignment(VerticalAlignment.CENTER)//垂直居中
                //设置边框
                .setBorderBottom(BorderStyle.THIN) //下边框
                .setBorderLeft(BorderStyle.THIN)//左边框
                .setBorderTop(BorderStyle.THIN)//上边框
                .setBorderRight(BorderStyle.THIN);//右边框
        //endregion

        lxwb.row("one", 10).setRowStyle(cellStyle);
        lxwb.cell("one", 11, 0).setCellStyle(cellStyle);

        //region 赋值
        int[] rowArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] cellArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int rowNum : rowArr) {
            for (int cellNum : cellArr) {
                lxwb.cell("one", rowNum, cellNum).setCellValue("测试一下吧1");
            }
        }
        //endregion

        lxwb.sheet("one").autoSizeColumn(cellArr);
//        lxwb.sheet("one").addMergedRegion(new CellRangeAddress(10, 10, 12, 12));


        try {
            lxwb.write("e:\\测试");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
