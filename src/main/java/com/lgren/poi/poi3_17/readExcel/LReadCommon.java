package com.lgren.poi.poi3_17.readExcel;

import com.lgren.common.CommResult;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

/**
 * 读取excel公共类
 * @author Lgren
 * @create 2018-11-30 10:51
 **/
public class LReadCommon {
    private static DecimalFormat df = new DecimalFormat("#.#########");// 格式化数字

    /** 通过输入流inp获取Workbook */
    public static Workbook getWorkbook(InputStream inp) throws IOException {
        Workbook workbook;
        CommResult<Workbook> result = new CommResult<>();
        try (InputStream mpbinp = inp.markSupported() ? inp : new PushbackInputStream(inp, 8);
             InputStream newInp = FileMagic.prepareToCheckMagic(mpbinp)) {
            FileMagic fileMagic = FileMagic.valueOf(newInp);
            if (fileMagic == FileMagic.OLE2) {
                try {
                    POIFSFileSystem fs = new POIFSFileSystem(newInp);
                    workbook = new HSSFWorkbook(fs);
                    result.setErrorMsg("xls");
                } catch (Exception e) {
                    throw new RuntimeException("读取失败！");
                }
            } else if (fileMagic == FileMagic.OOXML) {
                try {
                    workbook = new XSSFWorkbook(newInp);
                    result.setErrorMsg("xlsx");
                } catch (Exception e) {
                    throw new RuntimeException("读取失败！");
                }
            } else {
                throw new RuntimeException("不支持的文件类型！");
            }
        }
        return workbook;
    }

    //region 获取一个workbook的值

    /** 获取workbook下的所有cell的数据 通过sheet分组 */
    public static Map<String, Map<Object, Map<Object, Object>>> getWorkbookV(Workbook workbook) {
        return ofNullable(workbook).map(wb -> {
            Map<String, Map<Object, Map<Object, Object>>> allSheet = new LinkedHashMap<>(wb.getNumberOfSheets());
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                Sheet sheet = wb.getSheetAt(i);
                allSheet.put(sheet.getSheetName(), getSheetV(sheet));
            }
            return allSheet;
        }).orElse(null);
    }
    //endregion

    //region 获取一个sheet的值 通过行分组

    /**
     * 获取sheet下的所有cell的数据 通过row分组
     * @return sheet下所有的cell的值的集合 通过row(行)为单位分组
     */
    public static Map<Object, Map<Object, Object>> getSheetV(Sheet sheet) {
        return getSheetV(sheet, false);
    }

    /**
     * 获取sheet下的所有cell的数据 通过row分组
     * @param firstToKeyAndSkip 1.将第一列作为每一个row分组的key值
     *                          2.跳过哪一列
     *                          3.将第一行作为每一个cell的key值
     *                          4.跳过哪一行
     * @return sheet下所有的cell的值的集合 通过row(行)为单位分组
     */
    public static Map<Object, Map<Object, Object>> getSheetV(Sheet sheet, boolean firstToKeyAndSkip) {
        return firstToKeyAndSkip ? getSheetV(sheet, getColV(sheet, 0), new int[]{0}, getRowV(sheet, 0), new int[]{0})
                : getSheetV(sheet, null, null, null, null);
    }

    /**
     * 获取sheet下的所有cell的数据 通过row分组
     * @param whichColToRowKey  将whichColToRowKey列作为每一个row分组的key值 (从0计数)
     * @param skipWhichRow      跳过哪一行 如果小于0则不跳过
     * @param whichRowToCellKey 将第 whichRowToCellKey行作为每一个cell的key值 (从0计数)
     * @param skipWhichCol      跳过哪一列 如果小于0则不跳过
     * @return sheet下所有的cell的值的集合 通过row(行)为单位分组
     */
    public static Map<Object, Map<Object, Object>> getSheetV(Sheet sheet, int whichColToRowKey, int skipWhichRow, int whichRowToCellKey, int skipWhichCol) {
        int[] skipRowArr = skipWhichRow < 0 ? null : new int[]{skipWhichRow};
        int[] skipColArr = skipWhichCol < 0 ? null : new int[]{skipWhichCol};
        return getSheetV(sheet, getColV(sheet, whichColToRowKey), skipRowArr, getRowV(sheet, whichRowToCellKey), skipColArr);
    }

    /**
     * 获取sheet下的所有cell的数据 通过row分组
     * @param whichColToRowKey  将whichColToRowKey列作为每一个row分组的key值 (从0计数)
     * @param skipRowArr        跳过哪几列
     * @param whichRowToCellKey 将第 whichRowToCellKey行作为每一个cell的key值 (从0计数)
     * @param skipColArr        跳过哪几行
     * @return sheet下所有的cell的值的集合 通过row(行)为单位分组
     */
    public static Map<Object, Map<Object, Object>> getSheetV(Sheet sheet, int whichColToRowKey, int[] skipRowArr, int whichRowToCellKey, int[] skipColArr) {
        return getSheetV(sheet, getColV(sheet, whichColToRowKey), skipRowArr, getRowV(sheet, whichRowToCellKey), skipColArr);
    }

    /**
     * 获取sheet下的所有cell的数据 通过row分组
     * @param rowKeyMap  每个row分组的key值 如果为空则是对应的编号
     * @param skipRowArr 跳过哪几列
     * @param cellKeyMap 每个cell的key值 如果为空则是对应的编号
     * @param skipColArr 跳过哪几行
     * @return sheet下所有的cell的值的集合 通过row(行)为单位分组
     */
    public static Map<Object, Map<Object, Object>> getSheetV(Sheet sheet, Map<Object, Object> rowKeyMap, int[] skipRowArr, Map<Object, Object> cellKeyMap, int[] skipColArr) {
        return ofNullable(sheet).map(s -> {
            Map<Object, Map<Object, Object>> result = null;
            for (int i = s.getFirstRowNum(); i <= s.getLastRowNum(); i++) {
                if (ArrayUtils.contains(skipRowArr, i)) {
                    continue;
                }
                Row row = s.getRow(i);
                Map<Object, Object> cellMap = getRowV(row, cellKeyMap, skipColArr);
                if (cellMap != null) {
                    if (result == null) {
                        result = new LinkedHashMap<>(s.getPhysicalNumberOfRows());
                    }
                    Object rowKey = (rowKeyMap == null || rowKeyMap.isEmpty()) ? i : ofNullable(rowKeyMap.get(i)).orElse(i);
                    result.put(rowKey, cellMap);
                }
            }
            return result;
        }).orElse(null);
    }
    //endregion

    //region 获取一个sheet的值 通过列分组

    /**
     * 获取sheet下的所有cell的数据 通过row分组
     * @return sheet下所有的cell的值的集合 通过col(列)为单位分组
     */
    public static Map<Object, Map<Object, Object>> getSheetVByCol(Sheet sheet) {
        return getSheetVByCol(sheet, false);
    }

    /**
     * 获取sheet下的所有cell的数据 通过row分组
     * @param firstToKeyAndSkip 1.将第一行作为每一个col分组的key值
     *                          2.跳过哪一行
     *                          3.将第一列作为每一个cell的key值
     *                          4.跳过哪一列
     * @return sheet下所有的cell的值的集合 通过col(列)为单位分组
     */
    public static Map<Object, Map<Object, Object>> getSheetVByCol(Sheet sheet, boolean firstToKeyAndSkip) {
        return firstToKeyAndSkip ? getSheetVByCol(sheet, getRowV(sheet, 0), new int[]{0}, getColV(sheet, 0), new int[]{0})
                : getSheetVByCol(sheet, null, null, null, null);
    }

    /**
     * 获取sheet下的所有cell的数据 通过row分组
     * @param whichRowToColKey  将 whichRowToColKey行作为每一个col分组的key值 (从0计数)
     * @param skipWhichCol      跳过哪一列 如果小于0则不跳过
     * @param whichColToCellKey 将第 whichColToCellKey列作为每一个cell的key值 (从0计数)
     * @param skipWhichRow      跳过哪一行 如果小于0则不跳过
     * @return sheet下所有的cell的值的集合 通过col(列)为单位分组
     */
    public static Map<Object, Map<Object, Object>> getSheetVByCol(Sheet sheet, int whichRowToColKey, int skipWhichCol, int whichColToCellKey, int skipWhichRow) {
        int[] skipColArr = skipWhichCol < 0 ? null : new int[]{skipWhichCol};
        int[] skipRowArr = skipWhichRow < 0 ? null : new int[]{skipWhichRow};
        return getSheetVByCol(sheet, getRowV(sheet, whichRowToColKey), skipRowArr, getColV(sheet, whichColToCellKey), skipColArr);
    }

    /**
     * 获取sheet下的所有cell的数据 通过row分组
     * @param whichRowToColKey  将 whichRowToColKey行作为每一个col分组的key值 (从0计数)
     * @param skipColArr        跳过哪几列
     * @param whichColToCellKey 将第 whichColToCellKey列作为每一个cell的key值 (从0计数)
     * @param skipRowArr        跳过哪几行
     * @return sheet下所有的cell的值的集合 通过col(列)为单位分组
     */
    public static Map<Object, Map<Object, Object>> getSheetVByCol(Sheet sheet, int whichRowToColKey, int[] skipColArr, int whichColToCellKey, int[] skipRowArr) {
        return getSheetVByCol(sheet, getRowV(sheet, whichRowToColKey), skipRowArr, getColV(sheet, whichColToCellKey), skipColArr);
    }

    /**
     * 获取sheet下的所有cell的数据 通过row分组
     * @param colKeyMap  每个col分组的key值 如果为空则是对应的编号
     * @param skipColArr 跳过哪几列
     * @param cellKeyMap 每个cell的key值 如果为空则是对应的编号
     * @param skipRowArr 跳过哪几行
     * @return sheet下所有的cell的值的集合 通过col(列)为单位分组
     */
    public static Map<Object, Map<Object, Object>> getSheetVByCol(Sheet sheet, Map<Object, Object> colKeyMap, int[] skipColArr, Map<Object, Object> cellKeyMap, int[] skipRowArr) {
        return ofNullable(sheet).map(s -> {
            Map<Object, Map<Object, Object>> result = null;
            for (int i = s.getFirstRowNum(); i <= s.getLastRowNum(); i++) {
                if (ArrayUtils.contains(skipRowArr, i)) {
                    continue;
                }
                Row row = s.getRow(i);
                if (row == null) {
                    continue;
                }
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    if (ArrayUtils.contains(skipColArr, j)) {
                        continue;
                    }
                    Cell cell = row.getCell(j);
                    Object cellValue = getCellV(cell);
                    Map<Object, Object> cellMap;
                    if (cellValue != null) {
                        if (result == null) {
                            result = new LinkedHashMap<>(s.getRow(s.getFirstRowNum()).getPhysicalNumberOfCells());
                        }
                        Object colKey = colKeyMap == null ? j : ofNullable(colKeyMap.get(j)).orElse(j);
                        cellMap = result.get(colKey);
                        if (cellMap == null) {
                            cellMap = new LinkedHashMap<>(sheet.getPhysicalNumberOfRows());
                            result.put(colKey, cellMap);
                        }
                        Object cellKey = cellKeyMap == null || cellKeyMap.isEmpty() ? i : ofNullable(cellKeyMap.get(i)).orElse(i);
                        cellMap.put(cellKey, getCellV(cell));
                    }
                }
            }
            return result;
        }).orElse(null);
    }
    //endregion

    //region 获取一行所有cell的值

    /**
     * 获取sheet下的第rowIndex行的所有cell的数据 指定一个cellKeyMap作为key值 如果cell对应的key为空则将其编号作为key
     * @param rowIndex 第 rowIndex行 (从0计数)
     * @return 第 rowIndex行的所有cell的值的集合
     */
    public static Map<Object, Object> getRowV(Sheet sheet, int rowIndex) {
        return getRowV(sheet, rowIndex, null);
    }

    /**
     * 获取sheet下的第rowIndex行的所有cell的数据 指定一个cellKeyMap作为key值 如果cell对应的key为空则将其编号作为key
     * @param rowIndex          第 rowIndex行 (从0计数)
     * @param whichRowToCellKey 将第 whichRowToCellKey行作为每一个cell的key值 (从0计数)
     * @param skipColArr        跳过哪几列
     * @return 第 rowIndex行的所有cell的值的集合
     */
    public static Map<Object, Object> getRowV(Sheet sheet, int rowIndex, int whichRowToCellKey, int... skipColArr) {
        return getRowV(sheet, rowIndex, getRowV(sheet, whichRowToCellKey, null), skipColArr);
    }

    /**
     * 获取sheet下的第rowIndex行的所有cell的数据 指定一个cellKeyMap作为key值 如果cell对应的key为空则将其编号作为key
     * @param rowIndex   第 rowIndex行 (从0计数)
     * @param cellKeyMap 每一个cell的key值
     *                   cellKeyMap的key对应cell的key
     *                   cellKeyMap的value成为cell新的Key
     *                   注意!: 是一对一对应 如果对应的cellKeyMap的值为空则cell的key不变
     * @param skipColArr 跳过哪几列
     * @return 第 rowIndex行的所有cell的值的集合
     */
    public static Map<Object, Object> getRowV(Sheet sheet, int rowIndex, Map<Object, Object> cellKeyMap, int... skipColArr) {
        return ofNullable(sheet).map(s -> getRowV(s.getRow(rowIndex), cellKeyMap, skipColArr)).orElse(null);
    }

    /**
     * 获取row下的所有cell的数据 指定一个cellKeyMap作为key值 如果cell对应的key为空则将其编号作为key
     * @param cellKeyMap 每一个cell的key值
     *                   cellKeyMap的key对应cell的key
     *                   cellKeyMap的value成为cell新的Key
     *                   注意!: 是一对一对应 如果对应的cellKeyMap的值为空则cell的key不变
     * @param skipColArr 跳过哪几列
     * @return row的所有cell的值的集合
     */
    public static Map<Object, Object> getRowV(Row row, Map<Object, Object> cellKeyMap, int... skipColArr) {
        return ofNullable(row).map(r -> {
            Map<Object, Object> cellMap = null;
            for (int i = r.getFirstCellNum(); i < r.getLastCellNum(); i++) {
                if (ArrayUtils.contains(skipColArr, i)) {
                    continue;
                }
                Object cellValue = getCellV(r.getCell(i));
                if (cellValue != null) {
                    if (cellMap == null) {
                        cellMap = new LinkedHashMap<>(r.getPhysicalNumberOfCells());
                    }
                    Object cellKey = (cellKeyMap == null || cellKeyMap.isEmpty()) ? i : ofNullable(cellKeyMap.get(i)).orElse(i);
                    cellMap.put(cellKey, cellValue);
                }
            }
            return cellMap;
        }).orElse(null);
    }
    //endregion

    //region 获取一列所有cell的值

    /**
     * 获取sheet下的第colIndex列的所有cell的数据 指定一个list作为key值 如果cell对应的key为空则将其编号作为key
     * @param colIndex 第 colIndex列 (从0计数)
     * @return 一列的所有cell的值的集合
     */
    public static Map<Object, Object> getColV(Sheet sheet, int colIndex) {
        return getColV(sheet, colIndex, null);
    }

    /**
     * 获取sheet下的第colIndex列的所有cell的数据 指定一个list作为key值 如果cell对应的key为空则将其编号作为key
     * @param colIndex          第 colIndex列 (从0计数)
     * @param whichColToCellKey 将第 whichColToCellKey列作为每一个cell的key值 (从0计数)
     * @param skipRowArr        跳过哪几行
     * @return 一列的所有cell的值的集合
     */
    public static Map<Object, Object> getColV(Sheet sheet, int colIndex, int whichColToCellKey, int... skipRowArr) {
        return getColV(sheet, colIndex, getColV(sheet, whichColToCellKey, null), skipRowArr);
    }

    /**
     * 获取sheet下的第colIndex列的所有cell的数据 指定一个list作为key值 如果cell对应的key为空则将其编号作为key
     * @param colIndex   第 colIndex列 (从0计数)
     * @param cellKeyMap 每一个cell的key值
     *                   cellKeyMap的key对应cell的key
     *                   cellKeyMap的value成为cell新的Key
     *                   注意!: 是一对一对应 如果对应的cellKeyMap的值为空则cell的key不变
     * @param skipRowArr 跳过哪几行
     * @return 第 colIndex列的所有cell的值的集合
     */
    public static Map<Object, Object> getColV(Sheet sheet, int colIndex, Map<Object, Object> cellKeyMap, int... skipRowArr) {
        return ofNullable(sheet).map(s -> {
            // 否则输出所有cell的值
            Map<Object, Object> cellMap = null;
            for (int i = s.getFirstRowNum(); i <= s.getLastRowNum(); i++) {
                if (ArrayUtils.contains(skipRowArr, i)) {
                    continue;
                }
                Row row = s.getRow(i);
                if (row == null) {
                    continue;
                }
                // 如果 colIndex 不在对应 row的cell范围内 则输出null
                if (colIndex < row.getFirstCellNum() || colIndex > row.getLastCellNum() - 1) {
                    break;
                }
                Object cellValue = getCellV(s.getRow(i), colIndex);
                if (cellValue != null) {
                    if (cellMap == null) {
                        cellMap = new LinkedHashMap<>(s.getPhysicalNumberOfRows());
                    }
                    Object cellKey = (cellKeyMap == null || cellKeyMap.isEmpty()) ? i : ofNullable(cellKeyMap.get(i)).orElse(i);
                    cellMap.put(cellKey, cellValue);
                }
            }
            return cellMap;
        }).orElse(null);
    }
    //endregion

    //region 获取 Cell的值 通过cell,row,sheet,workbook

    /** 获取cell下的数据 */
    public static Object getCellV(Cell cell) {
        return ofNullable(cell).map(c -> {
            Object result;
            switch (c.getCellTypeEnum()) {
                case STRING:
                    result = c.getStringCellValue();
                    break;
                case NUMERIC:
                    result = df.format(c.getNumericCellValue());
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
            return result;
        }).orElse(null);
    }

    /**
     * 获取 Cell的
     * 获取row下的第cellIndex行的cell的数据
     * @param cellIndex 第 cellIndex个cell (从0计数)
     * @return Cell对象的值
     */
    public static Object getCellV(Row row, int cellIndex) {
        return getCellV(getCell(row, cellIndex));
    }

    /**
     * 获取 Cell的值
     * 获取sheet下的第cellIndex行第colIndex列的cell的数据
     * @param rowIndex 第 rowIndex行 (从0计数)
     * @param colIndex 第 colIndex列 (从0计数)
     * @return Cell对象的值
     */
    public static Object getCellV(Sheet sheet, int rowIndex, int colIndex) {
        return getCellV(getCell(sheet, rowIndex, colIndex));

    }

    /**
     * 获取 Cell的值
     * 获取workbook下的第sheetIndex个sheet的第rowIndex行第colIndex列的cell的数据
     * @param sheetIndex sheet的编号 (从0计数)
     * @param rowIndex   第 rowIndex行 (从0计数)
     * @param colIndex   第 colIndex列 (从0计数)
     * @return Cell对象的值
     */
    public static Object getCellV(Workbook workbook, int sheetIndex, int rowIndex, int colIndex) {
        return getCellV(getCell(workbook, sheetIndex, rowIndex, colIndex));

    }

    /**
     * 获取 Cell的值
     * 获取workbook下的名字叫做sheetName的sheet的第rowIndex行第colIndex列的cell的数据
     * @param sheetName sheet的名字
     * @param rowIndex  第 rowIndex行 (从0计数)
     * @param colIndex  第 colIndex列 (从0计数)
     * @return Cell对象的值
     */
    public static Object getCellV(Workbook workbook, String sheetName, int rowIndex, int colIndex) {
        return getCellV(getCell(workbook, sheetName, rowIndex, colIndex));

    }
    //endregion

    //region 获取 Cell 通过row,sheet,workbook

    /**
     * 获取 Cell的
     * 获取row下的第cellIndex行的Cell
     * @param cellIndex 第 cellIndex个cell (从0计数)
     * @return Cell对象 {@link Cell}
     */
    public static Cell getCell(Row row, int cellIndex) {
        // 如果 cellIndex大于row的最后边的cell 则返回null
        return ofNullable(row).filter(r -> cellIndex >= r.getFirstCellNum() && cellIndex < r.getLastCellNum())
                // 否则输出对应的Cell的
                .map(r -> r.getCell(cellIndex)).orElse(null);
    }

    /**
     * 获取 Cell的
     * 获取sheet下的第cellIndex行第colIndex列的Cell
     * @param rowIndex 第 rowIndex行 (从0计数)
     * @param colIndex 第 colIndex列 (从0计数)
     * @return Cell对象 {@link Cell}
     */
    public static Cell getCell(Sheet sheet, int rowIndex, int colIndex) {
        // 如果 rowIndex大于sheet的最后边的row 则返回null
        return ofNullable(sheet).filter(s -> rowIndex >= s.getFirstRowNum() && rowIndex <= s.getLastRowNum())
                // 否则输出对应的Cell的
                .map(s -> getCell(s.getRow(rowIndex), colIndex)).orElse(null);
    }

    /**
     * 获取 Cell的
     * 获取workbook下的第sheetIndex个sheet的第rowIndex行第colIndex列的Cell
     * @param sheetIndex sheet的编号 (从0计数)
     * @param rowIndex   第 rowIndex行 (从0计数)
     * @param colIndex   第 colIndex列 (从0计数)
     * @return Cell对象 {@link Cell}
     */
    public static Cell getCell(Workbook workbook, int sheetIndex, int rowIndex, int colIndex) {
        // 如果 sheetIndex大于等于workbook所有sheet数量 则返回null
        return ofNullable(workbook).filter(w -> sheetIndex > -1 && sheetIndex < w.getNumberOfSheets())
                // 否则输出对应的Cell的
                .map(w -> getCell(w.getSheetAt(sheetIndex), rowIndex, colIndex)).orElse(null);
    }

    /**
     * 获取 Cell的
     * 获取workbook下的名字叫做sheetName的sheet的第rowIndex行第colIndex列的Cell
     * @param sheetName sheet的名字
     * @param rowIndex  第 rowIndex行 (从0计数)
     * @param colIndex  第 colIndex列 (从0计数)
     * @return Cell对象 {@link Cell}
     */
    public static Cell getCell(Workbook workbook, String sheetName, int rowIndex, int colIndex) {
        return getCell(workbook.getSheet(sheetName), rowIndex, colIndex);
    }
    //endregion
}
