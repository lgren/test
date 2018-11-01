package com.lgren.poi.poi3_17;

import com.google.common.collect.Iterables;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-10-30 9:03
 **/
public class InitialModel {
    //poi导出excel
    public static void main(String[] args) {
//        XSSFWorkbook xwb = new XSSFWorkbook();
//        Sheet sheetOne = xwb.createSheet("one");
//        Row row_1 = sheetOne.createRow(0);
//        Cell cell_1 = row_1.createCell(0);
//        cell_1.setCellValue("随便测试一下吧");
//
//        try (FileOutputStream fileOut = new FileOutputStream("e:\\"+System.currentTimeMillis()+".xlsx")) {
//            xwb.write(fileOut);
//        } catch (IOException e) {
//            System.out.println("异常");
//        }
//        LXSSFWorkbook lxwb = new LXSSFWorkbook();
//        lxwb.sheet("sheetOne").row(0).cell(0).setCellValue("测试一下吧");
//        lxwb.sheet("sheetOne").row(1).cell(0).setCellValue("测试一下吧");
//        lxwb.sheet("sheetOne").row(2).cell(0).setCellValue("测试一下吧");
//        lxwb.sheet("sheetOne").row(3).cell(0).setCellValue("测试一下吧");
//        try (FileOutputStream fileOut = new FileOutputStream("e:\\"+System.currentTimeMillis()+".xlsx")) {
//            lxwb.write(fileOut);
//        } catch (IOException e) {
//            System.out.println("异常");
//        }


        Map<String, Map<Integer, Map<Integer, String>>> map = new HashMap<>(1);
        Map<Integer, Map<Integer, String>> sheetMap = new HashMap<>(1);
        Map<Integer, String> rowMap0 = new HashMap<>(1);
        rowMap0.put(0, "用户ID");
        Map<Integer, String> rowMap1 = new HashMap<>(1);
        rowMap1.put(0, "this is one");
        Map<Integer, String> rowMap2 = new HashMap<>(1);
        rowMap2.put(0, "this is two");
        Map<Integer, String> rowMap3 = new HashMap<>(1);
        rowMap3.put(0, "this is three");
        sheetMap.put(0, rowMap0);
        sheetMap.put(1, rowMap1);
        sheetMap.put(2, rowMap2);
        sheetMap.put(3, rowMap3);
        map.put("sheetOne", sheetMap);
        Workbook xwb = exportExcel1(map);
        try (FileOutputStream fileOut = new FileOutputStream("e:\\"+System.currentTimeMillis()+".xlsx")) {
            xwb.write(fileOut);
        } catch (IOException e) {
            System.out.println("异常");
        }
    }

    // Map<String, Map<Integer, List<Object>>> 中 key为sheet value为sheetData(Map<Integer, List<Object>>)
    // sheetData(Map<Integer, List<Object>>) 中 key为row value为colData(Map<Integer ,Object>)
    // colData(Map<Integer ,Object>) 中 key为多少列 value为值
    private static Workbook exportExcel1(Map<String, Map<Integer, Map<Integer, String>>> map) {
        Workbook xwb = new XSSFWorkbook();
        map.forEach((sheetName,sheetData) -> {
            Sheet sheet = xwb.createSheet(sheetName);
            sheetData.forEach((rowNum, colData) -> {
                Row row = sheet.createRow(rowNum);
                colData.forEach((colNum, colValue) ->{
                    Cell cell = row.createCell(colNum);
                    cell.setCellValue(colValue);
                });
            });
        });
        return xwb;
    }
    private <K,V> void forEach(Map<K, V> map, ThiConsumer<? super K, ? super V, Integer> action) {
        Objects.requireNonNull(map);
        Objects.requireNonNull(action);
        int index = 0;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            action.accept(entry.getKey(), entry.getValue(), index++);
        }
    }
    private <K,V> void forEach(Map<K, V> map, BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(map);
        Objects.requireNonNull(action);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            action.accept(entry.getKey(), entry.getValue());
        }
    }
    @FunctionalInterface
    interface ThiConsumer<T,U,W>{
        void accept(T t, U u, W w);

        default ThiConsumer<T,U,W> andThen(ThiConsumer<? super T,? super U,? super W> consumer){
            return (t, u, w)->{
                accept(t, u, w);
                consumer.accept(t, u, w);
            };
        }
    }

    public void exportExcel() throws Exception{
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet name");

        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 5));

        //设置第一列单元格宽度
        sheet.setColumnWidth(0,100*100);
        //设置第二列单元格宽度
        sheet.setColumnWidth(1,100*100);

        //创建第一行
        HSSFRow row0 = sheet.createRow(0);

        //创建第二行
        HSSFRow row1 = sheet.createRow(1);
        //设置第一行单元格高度
        row0.setHeight((short) 400);

        //创建第一行第一列单元格
        HSSFCell cell0_1 = row0.createCell(0);

        //创建第二行第一列单元格
        HSSFCell cell0_2 = row1.createCell(0);

        //设置单元格的值
        cell0_1.setCellValue("项目施工进度管理系统");

        //改变字体样式，步骤
        HSSFFont hssfFont = wb.createFont();

        //设置字体,红色
        hssfFont.setColor(HSSFFont.COLOR_RED);

        //字体粗体显示
        hssfFont.setBold(true);

        hssfFont.setFontName("宋体");

        // 字体大小
        hssfFont.setFontHeightInPoints((short) 22);

        //设置样式
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(hssfFont);

        //设置单元格背景色
        cellStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //设置居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中

        //设置边框
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.DASH_DOT_DOT);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框

        //3.单元格使用样式，设置第一行第一列单元格样式
        cell0_1.setCellStyle(cellStyle);
        cell0_2.setCellStyle(cellStyle);
        //生成excel文件
        FileOutputStream fileOut = new FileOutputStream("e:\\"+System.currentTimeMillis()+".xls");
        wb.write(fileOut);
        fileOut.close();

/*//         struts导出excel，前端需要是提交form形式，否则，点击导出不会弹出框
              HSSFWorkbook wb = exportExcel(projectId, blockId, buildingId,buildingCode);
             // 生成excel文件
             String fileName = String.valueOf(Calendar.getInstance().getTimeInMillis()).concat(".xls");
             // 清空response
             this.getResponse().reset();
             this.getResponse().addHeader("Content-Disposition","attachment;filename=" + new                              String(fileName.getBytes()));
             this.getResponse().setContentType("application/vnd.ms-excel;charset=utf-8");
             OutputStream os = this.getResponse().getOutputStream();
             wb.write(os);
             if (os != null) {
             os.close();
             os = null;
             }
             wb.close();*/
    }
}
