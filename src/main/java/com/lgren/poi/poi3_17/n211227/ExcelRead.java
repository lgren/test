package com.lgren.poi.poi3_17.n211227;

import cn.hutool.core.date.format.FastDateFormat;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.lgren.poi.poi3_17.new_read.ReadExcelField;
import com.lgren.util.反射.LReflectionUtil;
import lombok.Getter;
import lombok.SneakyThrows;
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

import java.io.File;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.Optional.ofNullable;

public class ExcelRead {
    public static final Map<String, FastDateFormat> FORMAT_MAP = MapUtil.<String, FastDateFormat>builder()
            .put("yyyy-MM-dd HH:mm:ss", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss"))
            .put("yyyy-MM-dd", FastDateFormat.getInstance("yyyy-MM-dd"))
            .put("HH:mm:ss", FastDateFormat.getInstance("HH:mm:ss"))
            .build();
    public static final String USE_UUID = "uuid";

    @Getter
    private final Workbook workbook;

    public ExcelRead(Workbook workbook) {
        this.workbook = workbook;
    }

    public ExcelRead(String name) {
        this(FileUtil.getInputStream(name));
    }

    public ExcelRead(File file) {
        this(FileUtil.getInputStream(file));
    }

    @SneakyThrows
    public ExcelRead(InputStream in) {
        try (InputStream mpbIn = in.markSupported() ? in : new PushbackInputStream(in, 8);
             InputStream newIn = FileMagic.prepareToCheckMagic(mpbIn)) {
            FileMagic fileMagic = FileMagic.valueOf(newIn);
            if (fileMagic == FileMagic.OLE2) {
                try {
                    POIFSFileSystem fs = new POIFSFileSystem(newIn);
                    this.workbook = new HSSFWorkbook(fs);
                } catch (Exception e) {
                    throw new RuntimeException("xls读取失败！");
                }
            } else if (fileMagic == FileMagic.OOXML) {
                try {
                    this.workbook = new XSSFWorkbook(newIn);
                } catch (Exception e) {
                    throw new RuntimeException("xlsx读取失败！");
                }
            } else {
                throw new RuntimeException("不支持的文件类型！");
            }
        }
    }

    public LSheet sheet(int sheetIndex) {
        // 如果 sheetIndex大于等于workbook所有sheet数量 则返回null
        return Optional.ofNullable(getWorkbook()).filter(w -> sheetIndex > -1 && sheetIndex < w.getNumberOfSheets())
                .map(w -> new LSheet(w.getSheetAt(sheetIndex))).orElse(LSheet.NULL);
    }

    public LSheet sheet(String sheetName) {
        return Optional.ofNullable(getWorkbook()).map(w -> new LSheet(w.getSheet(sheetName))).orElse(LSheet.NULL);
    }

    @SneakyThrows
    public static Date parseDateTime(String formatStr, String str) {
        return FORMAT_MAP.computeIfAbsent(formatStr, FastDateFormat::getInstance).parse(str);
    }

    public static class LCell {
        public static final LCell NULL = new LCell(null);
        @Getter
        private final Cell cell;

        public LCell(Cell cell) {
            this.cell = cell;
        }

        @SuppressWarnings("unchecked")
        public <V> Optional<V> getValue() {
            return Optional.ofNullable(getCell()).map(c -> {
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
            });
        }
    }

    public static class LRow {
        public static final LRow NULL = new LRow(null);
        @Getter
        private final Row row;

        public LRow(Row row) {
            this.row = row;
        }

        public LCell cell(int cellIndex) {
            // 如果 cellIndex大于row的最后边的cell 则返回null
            return Optional.ofNullable(getRow()).filter(r -> cellIndex >= r.getFirstCellNum() && cellIndex < r.getLastCellNum())
                    .map(r -> new LCell(r.getCell(cellIndex))).orElse(LCell.NULL);
        }

        private <V> Map<String, V> getRowV(Map<Integer, ?> cellIndexMapping, int... skipColArr) {
            Function<Integer, String> cellKeyFuncVar = i -> ofNullable(cellIndexMapping)
                    .map(c -> c.get(i))
                    .map(Object::toString)
                    .filter(StringUtils::isNotBlank)
                    .orElse(String.valueOf(i));
            return getRowV(cellKeyFuncVar, skipColArr);
        }

        public <V> Map<Integer, V> getRowV(int... skipColArr) {
            return getRowV(i -> i, skipColArr);
        }

        private <K, V> Map<K, V> getRowV(Function<Integer, K> cellIndexMapping, int... skipColArr) {
            return Optional.ofNullable(getRow()).map(r -> {
                Map<K, V> cellMap = new LinkedHashMap<>(r.getPhysicalNumberOfCells());
                for (int i = r.getFirstCellNum(); i < r.getLastCellNum() && !ArrayUtils.contains(skipColArr, i); i++) {
                    cellMap.put(cellIndexMapping.apply(i), cell(i).<V>getValue().orElse(null));
                }
                return cellMap;
            }).orElse(null);
        }

        public <V> Map<String, V> getRowV(LRow whichRowToCellKey, int... skipColArr) {
            Map<Integer, Object> keyMap = whichRowToCellKey.getRowV();
            return getRowV(keyMap, skipColArr);
        }

        @SneakyThrows
        public <R> R getRowV(Map<Integer, Object> keyMap, Class<R> rClass, int... skipColArr) {
            return getRowV(getRowV(keyMap, skipColArr), rClass);
        }

        @SneakyThrows
        public <R> R getRowV(LRow whichRowToCellKey, Class<R> rClass, int... skipColArr) {
            return getRowV(getRowV(whichRowToCellKey, skipColArr), rClass);
        }

        @SneakyThrows
        public <R> R getRowV(Map<String, Object> rowV, Class<R> rClass) {
            R result = rClass.newInstance();
            Field[] fields = LReflectionUtil.findFields(rClass);
            if (fields.length == 0) return result;
            if (rowV == null) return result;

            for (Field field : fields) {
                ReadExcelField readAnno = field.getAnnotation(ReadExcelField.class);
                if (readAnno == null) continue;
                String excelFieldName = StringUtils.isNotBlank(readAnno.value()) ? readAnno.value() : field.getName();
                if (StrUtil.isBlank(excelFieldName)) continue;
                // 获取的到set方法
                Method method = ReflectionUtils.findMethod(rClass, "set" + StrUtil.upperFirst(field.getName()), field.getType());
                if (method == null) continue;

                if (Objects.equals(excelFieldName, USE_UUID)) {
                    method.invoke(result, UUID.randomUUID().toString());
                } else {
                    Object data = rowV.get(excelFieldName);
                    if (data == null) continue;
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
                                if (Objects.equals(mArr[1], data)) {
                                    data = mArr[0];
                                }
                            }

                        }

                        // 最后一步判断是否是日期, 是的话日期格式化
                        String dateFormat = readAnno.dateFormat99();
                        if (field.getType() == Date.class) {
                            data = Optional.ofNullable(((String) data))
                                    .filter(StrUtil::isNotBlank)
                                    .map(s -> parseDateTime(dateFormat, s))
                                    .orElse(null);
                        }
                    }
                    method.invoke(result, data);
                }
            }
            return result;
        }
    }

    public static class LSheet {
        public static final LSheet NULL = new LSheet(null);
        @Getter
        private final Sheet sheet;
        @Getter
        private Map<Integer, Object> keyMap;

        public LSheet(Sheet sheet) {
            this.sheet = sheet;
        }

        public LRow row(int rowIndex) {
            // 如果 rowIndex大于sheet的最后边的row 则返回null
            return Optional.ofNullable(getSheet()).filter(s -> rowIndex >= s.getFirstRowNum() && rowIndex <= s.getLastRowNum())
                    .map(s -> new LRow(s.getRow(rowIndex))).orElse(LRow.NULL);
        }

        public IntStream range(int begin) {
            return IntStream.range(begin, getSheet().getLastRowNum() + 1);
        }

        public LSheet setWhichRowIsCellKey(int whichRowIsCellKey) {
            this.keyMap = row(whichRowIsCellKey).getRowV();
            return this;
        }
        public <V> Map<String, V> getRowV(int rowIndex, int... skipColArr) {
            return row(rowIndex).getRowV(keyMap, skipColArr);
        }

        @SneakyThrows
        public <R> R getRowV(int rowIndex, Class<R> rClass, int... skipColArr) {
            return row(rowIndex).getRowV(keyMap, rClass, skipColArr);
        }
    }
}
