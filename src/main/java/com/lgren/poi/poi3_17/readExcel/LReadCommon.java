package com.lgren.poi.poi3_17.readExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

/**
 * TODO
 * @author Lgren
 * @create 2018-11-30 10:51
 **/
public class LReadCommon {
    private static DecimalFormat df = new DecimalFormat("0");// 格式化 number String 字符
    private static DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字

    /** 获取workbook下的所有cell的数据 通过sheet分组 */
    public static Map<String, Map<Object, Map<Object, Object>>> getWorkbookValue(Workbook workbook) {
        return ofNullable(workbook).map(wb -> {
            Map<String, Map<Object, Map<Object, Object>>> allFile = null;
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                Sheet sheet = wb.getSheetAt(i);
                Map<Object, Map<Object, Object>> sheetMap = getSheetValue(sheet);
                if (sheetMap != null) {
                    if (allFile == null) {
                        allFile = new LinkedHashMap<>(wb.getNumberOfSheets());
                    }
                    allFile.put(sheet.getSheetName(), sheetMap);
                }
            }
            return allFile;
        }).orElse(null);
    }

    /** 获取sheet下的所有cell的数据 通过row分组 */
    public static Map<Object, Map<Object, Object>> getSheetValue(Sheet sheet) {
        return ofNullable(sheet).map(s -> {
            Map<Object, Map<Object, Object>> result = null;
            for (int j = s.getFirstRowNum(); j <= s.getLastRowNum(); j++) {
                Row row = s.getRow(j);
                Map<Object, Object> cellMap = getRowValue(row);
                if (cellMap != null) {
                    if (result == null) {
                        result = new LinkedHashMap<>(s.getPhysicalNumberOfRows());
                    }
                    result.put(j, cellMap);
                }
            }
            return result;
        }).orElse(null);
    }

    /** 获取sheet下的所有cell的数据 通过row分组 */
    public static Map<Object, Map<Object, Object>> getSheetValueByCol(Sheet sheet) {
        return ofNullable(sheet).map(s -> {
            Map<Object, Map<Object, Object>> result = new LinkedHashMap<>(s.getRow(s.getFirstRowNum()).getPhysicalNumberOfCells());
            for (int j = s.getFirstRowNum(); j <= s.getLastRowNum(); j++) {
                Row row = s.getRow(j);
                if (row == null) {
                    continue;
                }
                for (int k = row.getFirstCellNum(); k < row.getLastCellNum(); k++) {
                    Cell cell = row.getCell(k);
                    Object cellValue = getCellValue(cell);

                    Map<Object, Object> cellMap;
                    if (cellValue != null) {
                        cellMap = result.get(k);
                        if (cellMap == null) {
                            cellMap = new LinkedHashMap<>(sheet.getPhysicalNumberOfRows());
                            result.put(k, cellMap);
                        }
                        cellMap.put(j, getCellValue(cell));
                    }
                }
            }
            return result;
        }).orElse(null);
    }

    /** 获取row下的所有cell的数据 */
    public static Map<Object, Object> getRowValue(Row row) {
        return ofNullable(row).map(r -> {
            Map<Object, Object> cellMap = null;
            for (int k = r.getFirstCellNum(); k < r.getLastCellNum(); k++) {
                Cell cell = r.getCell(k);
                Object cellValue = getCellValue(cell);
                if (cellValue != null) {
                    if (cellMap == null) {
                        cellMap = new LinkedHashMap<>(r.getPhysicalNumberOfCells());
                    }
                    cellMap.put(k, getCellValue(cell));
                }
            }
            return cellMap;
        }).orElse(null);
    }

    /**
     * 获取sheet下的第colIndex列的所有cell的数据
     * {@link LReadCol#getColValue()} new LReadCol(sheet, colIndex).getColValue()
     */
    @Deprecated
    public static Map<Object, Object> getColValue(Sheet sheet, int colIndex) {
        return ofNullable(sheet).map(s -> {
            // 否则输出所有cell的值
            Map<Object, Object> result = null;
            for (int j = s.getFirstRowNum(); j <= s.getLastRowNum(); j++) {
                Row row = s.getRow(j);
                if (row == null) {
                    continue;
                }
                // 如果 colIndex 不在对应 row的cell范围内 则输出null
                if (colIndex < row.getFirstCellNum() || colIndex > row.getLastCellNum() - 1) {
                    break;
                }
                Object cellValue = getCellValue(s.getRow(j), colIndex);
                if (cellValue != null) {
                    if (result == null) {
                        result = new LinkedHashMap<>(s.getPhysicalNumberOfRows());
                    }
                    result.put(j, cellValue);
                }
            }
            return result;
        }).orElse(null);
    }

    /** 获取cell下的数据 */
    public static Object getCellValue(Cell cell) {
        return ofNullable(cell).map(c -> {
            Object result;
            switch (c.getCellTypeEnum()) {
                case STRING:
                    result = c.getStringCellValue();
                    break;
                case NUMERIC:
                    if ("@".equals(c.getCellStyle().getDataFormatString())) {
                        result = df.format(c.getNumericCellValue());
                    } else if ("General".equals(c.getCellStyle().getDataFormatString())) {
                        result = nf.format(c.getNumericCellValue());
                    } else {
                        result = c.getNumericCellValue();
                    }
                    break;
                case BOOLEAN:
                    result = c.getBooleanCellValue();
                    break;
                case BLANK:
                    result = "";
                    break;
                default:
                    result = c.toString();
            }
            return result;
        }).orElse(null);
    }

    /** 获取row下的第cellIndex行的cell的数据 */
    public static Object getCellValue(Row row, int cellIndex) {
        return getCellValue(getCell(row, cellIndex));
    }

    /** 获取sheet下的第cellIndex行第colIndex列的cell的数据 */
    public static Object getCellValue(Sheet sheet, int rowIndex, int colIndex) {
        return getCellValue(getCell(sheet, rowIndex, colIndex));

    }

    /** 获取workbook下的第sheetIndex个sheet的第rowIndex行第colIndex列的cell的数据 */
    public static Object getCellValue(Workbook workbook, int sheetIndex, int rowIndex, int colIndex) {
        return getCellValue(getCell(workbook, sheetIndex, rowIndex, colIndex));

    }

    /** 获取workbook下的名字叫做sheetName的sheet的第rowIndex行第colIndex列的cell的数据 */
    public static Object getCellValue(Workbook workbook, String sheetName, int rowIndex, int colIndex) {
        return getCellValue(getCell(workbook, sheetName, rowIndex, colIndex));

    }

    /** 获取row下的第cellIndex行的cell */
    public static Cell getCell(Row row, int cellIndex) {
        // 如果 cellIndex大于row的最后边的cell 则返回null
        return ofNullable(row).filter(r -> cellIndex < r.getLastCellNum())
                // 否则输出对应的Cell的
                .map(r -> r.getCell(cellIndex)).orElse(null);
    }

    /** 获取sheet下的第cellIndex行第colIndex列的 */
    public static Cell getCell(Sheet sheet, int rowIndex, int colIndex) {
        // 如果 rowIndex大于sheet的最后边的row 则返回null
        return ofNullable(sheet).filter(s -> rowIndex <= s.getLastRowNum())
                // 否则输出对应的Cell的
                .map(s -> getCell(s.getRow(rowIndex), colIndex)).orElse(null);
    }

    /** 获取workbook下的第sheetIndex个sheet的第rowIndex行第colIndex列的cell */
    public static Cell getCell(Workbook workbook, int sheetIndex, int rowIndex, int colIndex) {
        // 如果 sheetIndex大于等于workbook所有sheet数量 则返回null
        return ofNullable(workbook).filter(w -> w.getNumberOfSheets() > sheetIndex)
                // 否则输出对应的Cell的
                .map(w -> getCell(w.getSheetAt(sheetIndex), rowIndex, colIndex)).orElse(null);
    }

    /** 获取workbook下的名字叫做sheetName的sheet的第rowIndex行第colIndex列的cell */
    public static Cell getCell(Workbook workbook, String sheetName, int rowIndex, int colIndex) {
        return getCell(workbook.getSheet(sheetName), rowIndex, colIndex);
    }
}
