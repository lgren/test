package com.lgren.poi.poi3_17.readExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * 读取workbook的类
 * @author Lgren
 * @create 2018-12-03 11:37
 **/
public class LRWorkbook {
    //region LRWorkbook类部分
    private Workbook workbook;

    //region 构造区
    public LRWorkbook(Workbook workbook) {
        Objects.requireNonNull(workbook);
        this.workbook = workbook;
    }

    public LRWorkbook(InputStream inp) throws IOException {
        workbook = LReadCommon.getWorkbook(inp);
    }
    //endregion

    //region 获取全部数据

    /** 获取workbook下的所有cell的数据 通过sheet分组 */
    public Map<String, Map<Object, Map<Object, Object>>> getValue() {
        return LReadCommon.getWorkbookValue(workbook);
    }
    //endregion

    //region 其他需求
    public LReadSheet sheet(int sheetIndex) {
        return new LReadSheet(ofNullable(workbook).filter(w -> w.getNumberOfSheets() > sheetIndex).map(w -> w.getSheetAt(sheetIndex)).orElse(null));
    }

    /** 获取workbook下的名称叫做sheetName的sheet的所有cell的数据 通过row分组 */
    public LReadSheet sheet(String sheetName) {
        return new LReadSheet(workbook.getSheet(sheetName));
    }
    //endregion


    public Workbook getWorkbook() {
        return workbook;
    }

    @Override
    public String toString() {
        return getValue().toString();
    }
    //endregion

    /**
     * 读取sheet的类
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
            return LReadCommon.getSheetValue(sheet, false);
        }

        /**
         * 获取sheet下的所有cell的数据 通过row分组
         * @param firstToKeyAndSkip 1.将一列作为每一个row分组的key值
         *                          2.跳过哪一列
         *                          3.将第一行作为每一个cell的key值
         *                          3.跳过哪一行
         * @return sheet下所有的cell的值的集合 通过row(行)为单位分组
         */
        public Map<Object, Map<Object, Object>> getValueWithFirstKey(boolean firstToKeyAndSkip) {
            return LReadCommon.getSheetValue(sheet, firstToKeyAndSkip);
        }

        /**
         * 获取sheet下的所有cell的数据 通过row分组
         * @param rowKeyMap  每个row分组的key值 如果为空则是对应的编号
         * @param skipRowArr 跳过哪几列
         * @param cellKeyMap 每个cell的key值 如果为空则是对应的编号
         * @param skipColArr 跳过哪几行
         * @return sheet下所有的cell的值的集合 通过row(行)为单位分组
         */
        public Map<Object, Map<Object, Object>> getValue(Map<Object, Object> rowKeyMap, int[] skipRowArr, Map<Object, Object> cellKeyMap, int[] skipColArr) {
            return LReadCommon.getSheetValue(sheet, rowKeyMap, skipRowArr, cellKeyMap, skipColArr);
        }
        //endregion

        //region 以一列为单位

        /** 获取sheet下的所有cell的数据 通过row分组 */
        public Map<Object, Map<Object, Object>> getValueByCol() {
            return LReadCommon.getSheetValueByCol(sheet, false);
        }

        /**
         * 获取sheet下的所有cell的数据 通过row分组
         * @param firstToKeyAndSkip 1.将第一行作为每一个col分组的key值
         *                          2.跳过哪一行
         *                          3.将第一列作为每一个cell的key值
         *                          4.跳过哪一列
         * @return sheet下所有的cell的值的集合 通过col(列)为单位分组
         */
        public Map<Object, Map<Object, Object>> getValueWithFirstKeyByCol(boolean firstToKeyAndSkip) {
            return LReadCommon.getSheetValueByCol(sheet, firstToKeyAndSkip);
        }

        /**
         * 获取sheet下的所有cell的数据 通过row分组
         * @param colKeyMap  每个col分组的key值 如果为空则是对应的编号
         * @param skipColArr 跳过哪几列
         * @param cellKeyMap 每个cell的key值 如果为空则是对应的编号
         * @param skipRowArr 跳过哪几行
         * @return sheet下所有的cell的值的集合 通过col(列)为单位分组
         */
        public Map<Object, Map<Object, Object>> getValueByCol(Map<Object, Object> colKeyMap, int[] skipColArr, Map<Object, Object> cellKeyMap, int[] skipRowArr) {
            return LReadCommon.getSheetValueByCol(sheet, colKeyMap, skipColArr, cellKeyMap, skipRowArr);
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

        @Override
        public String toString() {
            return getValue().toString();
        }
    }

    /**
     * 读取row的类
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

        /**
         * 获取row下的所有cell的数据 指定一个cellKeyMap作为key值 如果cell对应的key为空则将其编号作为key
         * @param cellKeyMap 每一个cell的key值
         *                   cellKeyMap的key对应cell的key
         *                   cellKeyMap的value成为cell新的Key
         *                   注意!: 是一对一对应 如果对应的cellKeyMap的值为空则cell的key不变
         * @param skipColArr 跳过哪几列
         * @return row的所有cell的值的集合
         */        public Map<Object, Object> getValue(Map<Object, Object> cellKeyMap, int... skipColArr) {
            return LReadCommon.getRowValue(row, cellKeyMap, skipColArr);
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

        @Override
        public String toString() {
            return getValue().toString();
        }
    }

    /**
     * 读取col的类
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
            return getValue(LReadCommon.getColValue(sheet, 0, null));
        }

        private Map<Object, Object> getValue(Map<Object, Object> cellKeyMap) {
            if (cellMap == null) {
                return null;
            }
            Map<Object, Object> colValueMap = new LinkedHashMap<>(sheet.getPhysicalNumberOfRows());
            cellMap.forEach((k, v) -> {
                Object value = LReadCommon.getCellValue(v);
                if (value != null) {
                    colValueMap.put(cellKeyMap == null || cellKeyMap.isEmpty() ? k : ofNullable(cellKeyMap.get(k)).orElse(k), value);
                }
            });
            return colValueMap.isEmpty() ? null : colValueMap;
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

        public Map<Integer, Cell> getCol() {
            return cellMap;
        }

        @Override
        public String toString() {
            return getValue().toString();
        }
    }
}
