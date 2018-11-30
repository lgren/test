package com.lgren.poi.poi3_17.readExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;

/**
 * TODO
 * @author Lgren
 * @create 2018-11-30 11:17
 **/
public class LReadCol {
    private Map<Object, Cell> cellMap;
    private Map<Object, Object> colValueMap;

    //region 构造区
    public LReadCol(Sheet sheet, int colIndex) {
        if (sheet != null) {
            // 否则输出所有cell的值
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                // 如果 colIndex 不在对应 row的cell范围内 则输出null
                if (colIndex < row.getFirstCellNum() || colIndex > row.getLastCellNum() - 1) {
                    break;
                }
                Cell cell = LReadCommon.getCell(sheet, j, colIndex);
                if (cellMap == null) {
                    cellMap = new LinkedHashMap<>(sheet.getPhysicalNumberOfRows());
                }
                if (cell != null) {
                    cellMap.put(j, cell);
                }
                Object cellValue = LReadCommon.getCellValue(cell);
                if (colValueMap == null) {
                    colValueMap = new LinkedHashMap<>(sheet.getPhysicalNumberOfRows());
                }
                if (cellValue != null) {
                    colValueMap.put(j, cellValue);
                }
            }
        }
    }
    //endregion

    //region 获取Row全部数据 或者Cell数据

    /** 获取col下的所有cell的数据 */
    public Map<Object, Object> getColValue() {
        return colValueMap;
    }
    //endregion

    //region 其他需求

    /** 获取row下的第cellIndex行的cell的数据 */
    public Object getCellValue(int colIndex) {
        return colValueMap.get(colIndex);
    }

    /** 获取row下的第cellIndex行的cell的数据 */
    public Object cell(int colIndex) {
        return getCellValue(colIndex);
    }

    /** 获取col下的第colIndex列的cell的数据 */
    public Cell getCell(int colIndex) {
        return cellMap.get(colIndex);
    }
    //endregion
}
