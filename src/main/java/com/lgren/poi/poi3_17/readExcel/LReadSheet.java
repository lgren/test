package com.lgren.poi.poi3_17.readExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;

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

    /** 获取sheet下的所有cell的数据 通过row分组 */
    public Map<Object, Map<Object, Object>> getSheetValue() {
        return LReadCommon.getSheetValue(sheet);
    }

    /** 获取sheet下的所有cell的数据 通过row分组 */
    public Map<Object, Map<Object, Object>> getSheetValueByCol() {
        return LReadCommon.getSheetValueByCol(sheet);
    }
    //endregion

    //region 其他需求

    /** 获取sheet下的第rowIndex行的Row */
    public LReadRow row(int rowIndex) {
        return new LReadRow(ofNullable(sheet).map(s -> s.getRow(rowIndex)).orElse(null));
    }

    public LReadCol col(int colIndex) {
        return new LReadCol(sheet, colIndex);
    }
    //endregion

}
