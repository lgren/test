package com.lgren.poi.poi3_17.new_read;

import cn.hutool.core.date.format.FastDateFormat;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Objects;
import com.lgren.util.反射.LReflectionUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Optional.ofNullable;

/**
 * TODO
 * @author lgren
 * @create 2019-05-23 15:41
 **/
public class LRowCommon {
    public static final Map<String, FastDateFormat> FORMAT_MAP = MapUtil.<String, FastDateFormat>builder()
            .put("yyyy-MM-dd HH:mm:ss", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss"))
            .put("yyyy-MM-dd", FastDateFormat.getInstance("yyyy-MM-dd"))
            .put("HH:mm:ss", FastDateFormat.getInstance("HH:mm:ss"))
            .build();
    @SneakyThrows
    public static Date yMdHmsParse(String formatStr, String str) {
        return FORMAT_MAP.computeIfAbsent(formatStr, FastDateFormat::getInstance).parse(str);
    }

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

    @SneakyThrows
    public static <R> R getRowV(Sheet sheet, int rowIndex, int whichRowToCellKey, Class<R> rClass, int... skipColArr) {
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
                            if (data instanceof String) {

                                // 第一步裁剪
                                int[] substring = readAnno.substring1();
                                if (substring.length > 1) {
                                    data = ((String) data).substring(substring[0], Math.min(((String) data).length(), substring[1]));
                                }

                                // 第二步分割
                                String split = readAnno.split2();
                                if (StrUtil.isNotBlank(split)) {
                                    data = ((String) data).split(split);
                                }

                                // 第三步格式化
                                String strFormat = readAnno.strFormat3();
                                if (StrUtil.isNotBlank(strFormat)) {
                                    data = MessageFormat.format(strFormat, data);
                                }

                                // 第四步映射
                                String mapping = readAnno.mapping4();
                                if (StrUtil.isNotBlank(mapping)) {
                                    String[] mappingArr = mapping.split(",");
                                    for (String m : mappingArr) {
                                        String[] mArr = m.trim().split("-");
                                        if (Objects.equal(mArr[1], data)) {
                                            data = mArr[0];
                                        }
                                    }

                                }

                                // 最后一步判断是否是日期, 是的话日期格式化
                                String dateFormat = readAnno.dateFormat99();
                                if (field.getType() == Date.class) {// TODO
                                    data = Optional.ofNullable(((String) data))
                                            .filter(StrUtil::isNotBlank)
                                            .map(s -> yMdHmsParse(dateFormat, s))
                                            .orElse(null);
                                }
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
