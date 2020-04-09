package com.lgren.poi.poi3_17.new_read;

import com.lgren.util.反射.LReflectionUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import static java.util.Optional.ofNullable;

/**
 * TODO
 * @author lgren
 * @create 2019-05-23 15:41
 **/
public class LRowCommon {
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

    public static <R> R getRowV(Sheet sheet, int rowIndex, int whichRowToCellKey, Class<R> rClass, int... skipColArr) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        R result = rClass.newInstance();
        Field[] fields = LReflectionUtil.findFields(rClass);
        if (fields.length > 0) {
            Map<String, Object> rowV = getRowV(sheet, rowIndex, whichRowToCellKey, skipColArr);
            for (Field field : fields) {
                ReadExcelField readAnno = field.getAnnotation(ReadExcelField.class);
                if (readAnno != null) {
                    String excelFieldName = StringUtils.isNotBlank(readAnno.value()) ? readAnno.value() : field.getName();
                    Object data = rowV.get(excelFieldName);
                    if (data != null) {
                        Method method = ReflectionUtils.findMethod(rClass, "set" + StringUtils.capitalize(field.getName()), field.getType());
                        if (method != null) {
                            if (field.getType() == Integer.class) {// TODO
                                data = data;
                            }
                            method.invoke(result, data);
                        }
                    }
                }
            }
        }
        return result;
    }

    private static <V> Map<String, V> getRowV(Sheet sheet, int rowIndex, Map<Integer, Object> cellKeyMap, int... skipColArr) {
        return sheet == null ? null : getRowV(sheet.getRow(rowIndex), cellKeyMap, skipColArr);
    }

    private static <V> Map<String, V> getRowV(Row row, Map<Integer, Object> cellKeyMap, int... skipColArr) {
        Function<Integer, String> cellKeyFuncVar = i -> ofNullable(cellKeyMap)
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
                V cellValue = LCellCommon.getCellV(r.getCell(i));
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
}
