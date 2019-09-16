package com.lgren.poi.poi3_17.new_read;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import static java.util.Optional.ofNullable;

/**
 * @author lgren
 * @create 2019-05-23 15:41
 **/
public class LCellCommon {
    //region 获取 Cell的值 通过cell,row,sheet,workbook
    public static <V> V getCellV(Cell cell) {
        return ofNullable(cell).map(c -> {
            Object result;
            switch (c.getCellTypeEnum()) {
                case STRING:
                    result = c.getStringCellValue();
                    break;
                case NUMERIC:
                    // result = df.format(c.getNumericCellValue());
                    result = c.getNumericCellValue();
                    // if ("@".equals(c.getCellStyle().getDataFormatString())) {
                    //     result = df.format(c.getNumericCellValue());
                    // } else if ("General".equals(c.getCellStyle().getDataFormatString())) {
                    //     result = nf.format(c.getNumericCellValue());
                    // } else {
                    //     result = c.getNumericCellValue();
                    // }
                    break;
                case BOOLEAN:
                    result = c.getBooleanCellValue();
                    break;
                case BLANK:
                    result = null;
                    break;
                default:
                    result = c.toString();
            }
            return (V) result;
        }).orElse(null);
    }

    public static <V> V getCellV(Row row, int cellIndex) {
        return getCellV(getCell(row, cellIndex));
    }

    public static <V> V getCellV(Sheet sheet, int rowIndex, int colIndex) {
        return getCellV(getCell(sheet, rowIndex, colIndex));

    }

    public static <V> V getCellV(Workbook workbook, int sheetIndex, int rowIndex, int colIndex) {
        return getCellV(getCell(workbook, sheetIndex, rowIndex, colIndex));

    }

    public static <V> V getCellV(Workbook workbook, String sheetName, int rowIndex, int colIndex) {
        return getCellV(getCell(workbook, sheetName, rowIndex, colIndex));

    }
    //endregion

    //region 获取 Cell 通过row,sheet,workbook
    public static Cell getCell(Row row, int cellIndex) {
        // 如果 cellIndex大于row的最后边的cell 则返回null
        return ofNullable(row).filter(r -> cellIndex >= r.getFirstCellNum() && cellIndex < r.getLastCellNum())
                // 否则输出对应的Cell的
                .map(r -> r.getCell(cellIndex)).orElse(null);
    }

    public static Cell getCell(Sheet sheet, int rowIndex, int colIndex) {
        // 如果 rowIndex大于sheet的最后边的row 则返回null
        return ofNullable(sheet).filter(s -> rowIndex >= s.getFirstRowNum() && rowIndex <= s.getLastRowNum())
                // 否则输出对应的Cell的
                .map(s -> getCell(s.getRow(rowIndex), colIndex)).orElse(null);
    }

    public static Cell getCell(Workbook workbook, int sheetIndex, int rowIndex, int colIndex) {
        // 如果 sheetIndex大于等于workbook所有sheet数量 则返回null
        return ofNullable(workbook).filter(w -> sheetIndex > -1 && sheetIndex < w.getNumberOfSheets())
                // 否则输出对应的Cell的
                .map(w -> getCell(w.getSheetAt(sheetIndex), rowIndex, colIndex)).orElse(null);
    }

    public static Cell getCell(Workbook workbook, String sheetName, int rowIndex, int colIndex) {
        return getCell(workbook.getSheet(sheetName), rowIndex, colIndex);
    }
    //endregion
}
