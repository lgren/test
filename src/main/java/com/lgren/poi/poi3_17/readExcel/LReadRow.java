package com.lgren.poi.poi3_17.readExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

import static java.util.Optional.ofNullable;

/**
 * TODO
 * @author Lgren
 * @create 2018-11-30 9:31
 **/
public class LReadRow {
    private Row row;
    private Sheet sheet;

    //region 构造区
    public LReadRow(Sheet sheet, int rowIndex) {
        this.sheet = sheet;
        this.row = ofNullable(sheet).map(s -> s.getRow(rowIndex)).orElse(null);
    }
    //endregion

    //region 获取Row全部数据
    /** 获取row下的所有cell的数据 */
    public Map<Object, Object> getValue() {
        return getValue(null);
    }

    /** 获取row下的所有cell的数据 第一行的值作为key值 */
    public Map<Object, Object> getValueWithFirstColKey() {
        if (sheet == null || row == null) {
            return null;
        }
        Map<Object, Object> firstColKeyMap = LReadCommon.getRowValue(sheet.getRow(sheet.getFirstRowNum()), null);
        return getValue(firstColKeyMap);
    }

    /** 获取row下的所有cell的数据 指定一个cellKeyMap作为key值 如果cell对应的key为空则将其编号作为key */
    public Map<Object, Object> getValue(Map<Object, Object> firstColKeyMap) {
        return LReadCommon.getRowValue(row, firstColKeyMap);
    }
    //endregion

    //region 其他需求

    /** 获取row下的第cellIndex行的cell的数据 */
    public Object getCellValue(int cellIndex) {
        return LReadCommon.getCellValue(getCell(cellIndex));
    }

    /** 获取row下的第cellIndex行的cell的数据 */
    public Object cell(int cellIndex) {
        return getCellValue(cellIndex);
    }

    /** 获取row下的第cellIndex行的cell的数据 */
    public Cell getCell(int cellIndex) {
        return ofNullable(row).filter(r -> cellIndex < r.getLastCellNum())
                // 否则输出对应的Cell的值
                .map(r -> r.getCell(cellIndex)).orElse(null);
    }
    //endregion


    public Row getRow() {
        return row;
    }
}
