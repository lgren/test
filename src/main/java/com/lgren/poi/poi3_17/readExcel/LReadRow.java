package com.lgren.poi.poi3_17.readExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.text.DecimalFormat;
import java.util.Map;

import static java.util.Optional.ofNullable;

/**
 * TODO
 * @author Lgren
 * @create 2018-11-30 9:31
 **/
public class LReadRow {
    private Row row;

    //region 构造区
    public LReadRow(Row row) {
        this.row = row;
    }
    //endregion

    //region 获取Row全部数据 或者Cell数据
    /** 获取row下的所有cell的数据 */
    public Map<Object, Object> getRowValue() {
        return LReadCommon.getRowValue(row);
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
}
