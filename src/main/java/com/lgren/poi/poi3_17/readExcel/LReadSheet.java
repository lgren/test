package com.lgren.poi.poi3_17.readExcel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 * @author Lgren
 * @create 2018-11-30 9:31
 **/
public class LReadSheet {
    private Sheet sheet;

    //region 构造区
    public LReadSheet(Sheet sheet) {
        this.sheet = sheet;
    }
    //endregion

    //region 获取Sheet所有数据
    //region 以一行为单位
    /** 获取sheet下的所有cell的数据 通过row分组 */
    public Map<Object, Map<Object, Object>> getValue() {
        return getValue(false, null);
    }

    /** 获取sheet下的所有cell的数据 通过row分组 每一行的第一列作为row的key值 第一行对应的值作为cell的key */
    public Map<Object, Map<Object, Object>> getValueWithFirstKey() {
        if (sheet == null) {
            return null;
        }
        return getValue(true, LReadCommon.getRowValue(sheet.getRow(sheet.getFirstRowNum()), null));
    }

    /** 获取sheet下的所有cell的数据 通过row分组
     *  @param firstColToKey 是否将每一行的第一列作为整个rowValue的key值
     *  @param cellKeyMap 每个cell的key值 如果为空则是对应的编号
     */
    public Map<Object, Map<Object, Object>> getValue(boolean firstColToKey, Map<Object, Object> cellKeyMap) {
        return LReadCommon.getSheetValue(sheet, firstColToKey, cellKeyMap);
    }
    //endregion

    //region 以一列为单位
    /** 获取sheet下的所有cell的数据 通过row分组 */
    public Map<Object, Map<Object, Object>> getValueByCol() {
        return getValueByCol(false, null);
    }

    /** 获取sheet下的所有cell的数据 通过row分组 */
    public Map<Object, Map<Object, Object>> getValueWithFirstKeyByCol() {
        if (sheet == null) {
            return null;
        }
        int lastRowNum = sheet.getLastRowNum();
        Map<Object, Object> cellKeyMap = new HashMap<>(sheet.getPhysicalNumberOfRows());
        for (int i = sheet.getFirstRowNum(); i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            cellKeyMap.put(i, LReadCommon.getCellValue(row, row.getFirstCellNum()));
        }
        return getValueByCol(true, cellKeyMap);
    }

    /** 获取sheet下的所有cell的数据 通过row分组
     *  @param firstRowToKey 是否将第一行的值作为整个colValue的key值
     *  @param cellKeyMap 每个cell的key值 如果为空则是对应的编号
     */
    public Map<Object, Map<Object, Object>> getValueByCol(boolean firstRowToKey, Map<Object, Object> cellKeyMap) {
        return LReadCommon.getSheetValueByCol(sheet, firstRowToKey, cellKeyMap);
    }
    //endregion
    //endregion

    //region 其他需求

    /** 获取sheet下的第rowIndex行的Row */
    public LReadRow row(int rowIndex) {
        return new LReadRow(sheet, rowIndex);
    }

    public LReadCol col(int colIndex) {
        return new LReadCol(sheet, colIndex);
    }
    //endregion

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }
}
