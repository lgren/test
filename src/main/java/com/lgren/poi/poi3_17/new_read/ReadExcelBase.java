package com.lgren.poi.poi3_17.new_read;

import com.google.common.collect.BiMap;
import com.lgren.反射.LReflectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ReflectionUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static java.util.Optional.ofNullable;

/**
 * TODO
 * @author lgren
 * @create 2019-05-17 16:22
 **/
public class ReadExcelBase {
    private static DecimalFormat df = new DecimalFormat("#.#########");// 格式化数字

    //region 获取Workbook
    public static Workbook getWorkbook(String name) throws IOException {
        return getWorkbook(new FileInputStream(name));
    }

    public static Workbook getWorkbook(InputStream inp) throws IOException {
        Workbook workbook;
        try (InputStream mpbinp = inp.markSupported() ? inp : new PushbackInputStream(inp, 8);
             InputStream newInp = FileMagic.prepareToCheckMagic(mpbinp)) {
            FileMagic fileMagic = FileMagic.valueOf(newInp);
            if (fileMagic == FileMagic.OLE2) {
                try {
                    POIFSFileSystem fs = new POIFSFileSystem(newInp);
                    workbook = new HSSFWorkbook(fs);
                } catch (Exception e) {
                    throw new RuntimeException("xls读取失败！");
                }
            } else if (fileMagic == FileMagic.OOXML) {
                try {
                    workbook = new XSSFWorkbook(newInp);
                } catch (Exception e) {
                    throw new RuntimeException("xlsx读取失败！");
                }
            } else {
                throw new RuntimeException("不支持的文件类型！");
            }
        }
        return workbook;
    }
    //endregion

    //region 获取一Row所有cell的值

    public static <V> Map<Integer, V> getRowV(Sheet sheet, int rowIndex, int... skipColArr) {
        return sheet == null ? null : getRowV(sheet.getRow(rowIndex), skipColArr);
    }

    public static <V> Map<Integer, V> getRowV(Row row, int... skipColArr) {
        return getRowVBase(row, i -> i, skipColArr);
    }

    public static <V> Map<String, V> getRowV(Sheet sheet, int rowIndex, int whichRowToCellKey, int... skipColArr) {
        Map<Integer, Object> keyMap = getRowV(sheet, whichRowToCellKey);
        return getRowV(sheet, rowIndex, keyMap, skipColArr);
    }

    private static <R> R getRowV(Sheet sheet, int rowIndex, int whichRowToCellKey, Class<R> rClass, int... skipColArr) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        R result = rClass.newInstance();
        Field[] fields = LReflectionUtils.getFields(rClass);
        if (fields.length > 0) {
            Map<String, Object> rowV = getRowV(sheet, rowIndex, whichRowToCellKey, skipColArr);
            for (Field field : fields) {
                ReadExcelField readAnno = field.getAnnotation(ReadExcelField.class);
                if (readAnno != null) {
                    String excelFieldName = StringUtils.isNotBlank(readAnno.value()) ? readAnno.value() : field.getName();
                    rowV.get(excelFieldName);// TODO

                    Method method = LReflectionUtils.getMethod(rClass, "set" + StringUtils.capitalize(excelFieldName));
                    method.invoke(result, rowV.get(excelFieldName));
                }
            }
        }
        return result;
    }

    private static <V> Map<String, V> getRowV(Sheet sheet, int rowIndex, Map<Integer, Object> cellKeyFunc, int... skipColArr) {
        return sheet == null ? null : getRowV(sheet.getRow(rowIndex), cellKeyFunc, skipColArr);
    }

    private static <V> Map<String, V> getRowV(Row row, Map<Integer, Object> cellKeyFunc, int... skipColArr) {
        Function<Integer, String> cellKeyFuncVar = i -> ofNullable(cellKeyFunc)
                .map(c -> c.get(i))
                .map(Object::toString)
                .filter(StringUtils::isNotBlank)
                .orElse(String.valueOf(i));
        return getRowVBase(row, cellKeyFuncVar, skipColArr);
    }

    private static <K, V> Map<K, V> getRowVBase(Row row, Function<Integer, K> cellKeyFunc, int... skipColArr) {
        return ofNullable(row).map(r -> {
            Map<K, V> cellMap = null;
            for (int i = r.getFirstCellNum(); i < r.getLastCellNum(); i++) {
                if (ArrayUtils.contains(skipColArr, i)) {
                    continue;
                }
                V cellValue = getCellV(r.getCell(i));
                if (cellValue != null) {
                    if (cellMap == null) {
                        cellMap = new LinkedHashMap<>(r.getPhysicalNumberOfCells());
                    }
                    cellMap.put(cellKeyFunc.apply(i), cellValue);
                }
            }
            return cellMap;
        }).orElse(null);
    }
    //endregion

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

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Workbook wb = getWorkbook("/Users/lgren/Desktop/临时存放/test.xlsx");
        // Map<Integer, String> rowV = getRowV(wb.getSheetAt(0).getRow(1));
        Map<String, Object> rowV1 = getRowV(wb.getSheetAt(0), 1, 0, (int[]) null);
        BaseClass rowV = getRowV(wb.getSheetAt(0), 1, 0, BaseClass.class);


        System.out.println(wb.toString());/**/
        // int[] arr = {1, 2, 3, 4};
        int[] arr = null;
        ArrayUtils.contains(arr, 4);
    }
}
