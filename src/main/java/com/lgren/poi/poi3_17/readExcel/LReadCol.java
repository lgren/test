package com.lgren.poi.poi3_17.readExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

/**
 * TODO
 * @author Lgren
 * @create 2018-11-30 11:17
 **/
public class LReadCol {
    private Map<Integer, Cell> cellMap;
    private Sheet sheet;

    //region 构造区
    public LReadCol(Sheet sheet, int colIndex) {
        if (sheet != null) {
            this.sheet = sheet;
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
                if (cell != null) {
                    if (cellMap == null) {
                        cellMap = new LinkedHashMap<>(sheet.getPhysicalNumberOfRows());
                    }
                    cellMap.put(j, cell);
                }
            }
        }
    }
    //endregion

    //region 获取Row全部数据 或者Cell数据

    /** 获取col下的所有cell的数据 */
    public Map<Object, Object> getValue() {
        return getValue(null);
    }

    /** 获取col下的所有cell的数据 第一列的值作为key值 */
    public Map<Object, Object> getValueWithFirstColKey() {
        if (sheet == null || cellMap == null || cellMap.isEmpty()) {
            return null;
        }
        int lastRowNum = sheet.getLastRowNum();
        Map<Object, Object> cellKeyMap = new HashMap<>(sheet.getPhysicalNumberOfRows());
        for (int i = sheet.getFirstRowNum(); i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                cellKeyMap.put(i, LReadCommon.getCellValue(row, row.getFirstCellNum()));
            }
        }
        return getValue(cellKeyMap);
    }

    private Map<Object, Object> getValue(Map<Object, Object> cellKeyMap) {
        Map<Object, Object> colValueMap = new LinkedHashMap<>(sheet.getPhysicalNumberOfRows());
        cellMap.forEach((k,v) ->
                colValueMap.put(cellKeyMap == null ? k : ofNullable(cellKeyMap.get(k)).orElse(k), LReadCommon.getCellValue(v))
        );
        return colValueMap;
    }
    //endregion

    //region 其他需求

    /** 获取row下的第cellIndex行的cell的数据 */
    public Object getCellValue(int colIndex) {
        return LReadCommon.getCellValue(cellMap.get(colIndex));
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


    public Map<Integer, Cell> getCellMap() {
        return cellMap;
    }
}
