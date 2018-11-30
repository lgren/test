package com.lgren.poi.poi3_17;

import com.lgren.common.CommResult;
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


public class LReadExcelUtils {
    private static DecimalFormat df = new DecimalFormat("0");// 格式化 number String 字符
    private static DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字


//    /**
//     * 获取Excel文件数据 第一个sheet
//     * @param inp excel流
//     */
//    @NonNull
//    public static CommResult<Map<String, Map<Integer, Map<Integer, Object>>>> readWorkbook(InputStream inp) throws IOException {
//        CommResult<Workbook> wbResult = getWorkbook(inp);
//        if (wbResult.isSuccess()) {
//            Map<String, Map<Integer, Map<Integer, Object>>> map = getWorkbookValue(wbResult.getData());
//            return CommResult.newSuccess(map).setErrorMsg(wbResult.getErrorMsg());
//        }
//        return CommResult.newFailure(wbResult.getErrorCode(), wbResult.getErrorMsg());
//    }

    /** 通过输入流inp获取Workbook */
    public static CommResult<Workbook> getWorkbook(InputStream inp) throws IOException {
        Workbook workbook;
        CommResult<Workbook> result = new CommResult<>();
        try (InputStream mpbinp = inp.markSupported() ? inp : new PushbackInputStream(inp, 8);
                InputStream newInp = FileMagic.prepareToCheckMagic(mpbinp)){
            FileMagic fileMagic = FileMagic.valueOf(newInp);
            if (fileMagic == FileMagic.OLE2) {
                try {
                    POIFSFileSystem fs = new POIFSFileSystem(newInp);
                    workbook = new HSSFWorkbook(fs);
                    result.setErrorMsg("xls");
                } catch (Exception e) {
                    return result.setErrorCode("-1").setErrorMsg("读取失败！");
                }
            } else if (fileMagic == FileMagic.OOXML) {
                try {
                    workbook = new XSSFWorkbook(newInp);
                    result.setErrorMsg("xlsx");
                } catch (Exception e) {
                    return result.setErrorCode("-1").setErrorMsg("读取失败！");
                }
            } else {
                return result.setErrorCode("-1").setErrorMsg("不支持的文件类型！");
            }
        }
        return result.setSuccess(true).setData(workbook);
    }

    //region 获取全部数据
    /** 获取workbook下的所有cell的数据 通过sheet分组 */
    public static Map<String, Map<Integer, Map<Integer, Object>>> getWorkbookValue(Workbook workbook) {
        return ofNullable(workbook).map(wb -> {
            Map<String, Map<Integer, Map<Integer, Object>>> allFile = null;
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                Sheet sheet = wb.getSheetAt(i);
                Map<Integer, Map<Integer, Object>> sheetMap = getSheetValue(sheet);
                if (sheetMap != null) {
                    if (allFile == null) {
                        allFile = new LinkedHashMap<>(wb.getNumberOfSheets());
                    }
                    allFile.put(sheet.getSheetName(), sheetMap);
                }
            }
            return allFile;
        }).orElse(null);
    }

    /** 获取sheet下的所有cell的数据 通过row分组 */
    public static Map<Integer, Map<Integer, Object>> getSheetValue(Sheet sheet) {
        return ofNullable(sheet).map(s -> {
            Map<Integer, Map<Integer, Object>> result = null;
            for (int j = s.getFirstRowNum(); j <= s.getLastRowNum(); j++) {
                Row row = s.getRow(j);
                Map<Integer, Object> cellMap = getRowValue(row);
                if (cellMap != null) {
                    if (result == null) {
                        result = new LinkedHashMap<>(s.getPhysicalNumberOfRows());
                    }
                    result.put(j, cellMap);
                }
            }
            return result;
        }).orElse(null);
    }

    /** 获取sheet下的所有cell的数据 通过row分组 */
    public static Map<Integer, Map<Integer, Object>> getSheetValueByCol(Sheet sheet) {
        return ofNullable(sheet).map(s -> {
            Map<Integer, Map<Integer, Object>> result = new LinkedHashMap<>(s.getRow(s.getFirstRowNum()).getPhysicalNumberOfCells());
            for (int j = s.getFirstRowNum(); j <= s.getLastRowNum(); j++) {
                Row row = s.getRow(j);
                if (row == null) {
                    continue;
                }
                for (int k = row.getFirstCellNum(); k < row.getLastCellNum(); k++) {
                    Cell cell = row.getCell(k);
                    Object cellValue = getCellValue(cell);

                    Map<Integer, Object> cellMap;
                    if (cellValue != null) {
                        cellMap = result.get(k);
                        if (cellMap == null) {
                            cellMap = new LinkedHashMap<>(sheet.getPhysicalNumberOfRows());
                            result.put(k, cellMap);
                        }
                        cellMap.put(j, getCellValue(cell));
                    }
                }
            }
            return result;
        }).orElse(null);
    }

    /** 获取row下的所有cell的数据 */
    public static Map<Integer, Object> getRowValue(Row row) {
        return ofNullable(row).map(r -> {
            Map<Integer, Object> cellMap = null;
            for (int k = r.getFirstCellNum(); k < r.getLastCellNum(); k++) {
                Cell cell = r.getCell(k);
                Object cellValue = getCellValue(cell);
                if (cellValue != null) {
                    if (cellMap == null) {
                        cellMap = new LinkedHashMap<>(r.getPhysicalNumberOfCells());
                    }
                    cellMap.put(k, getCellValue(cell));
                }
            }
            return cellMap;
        }).orElse(null);
    }

    /** 获取cell下的数据 */
    public static Object getCellValue(Cell cell) {
        return ofNullable(cell).map(c -> {
            Object result;
            switch (c.getCellTypeEnum()) {
                case STRING:
                    result = c.getStringCellValue();
                    break;
                case NUMERIC:
                    if ("@".equals(c.getCellStyle().getDataFormatString())) {
                        result = df.format(c.getNumericCellValue());
                    } else if ("General".equals(c.getCellStyle().getDataFormatString())) {
                        result = nf.format(c.getNumericCellValue());
                    } else {
                        result = c.getNumericCellValue();
                    }
                    break;
                case BOOLEAN:
                    result = c.getBooleanCellValue();
                    break;
                case BLANK:
                    result = "";
                    break;
                default:
                    result = c.toString();
            }
            return result;
        }).orElse(null);
    }
    //endregion

    //region 特殊需求的获取值
    // 1.获取一个sheet(通过sheet编号/名称) getSheetValue
    // 2.获取一行的值 getRowValue
    // 3.获取一列的值 getColValue
    // 4.获取一个值 getCellValue

    /** 获取workbook下的第sheetIndex个sheet的所有cell的数据 通过row分组 */
    public static Map<Integer, Map<Integer, Object>> getSheetValue(Workbook workbook, int sheetIndex) {
        // 如果 sheetIndex大于workbook中sheet的总数 则返回null
        return ofNullable(workbook).filter(wb -> sheetIndex < wb.getNumberOfSheets())
                // 否则输出对应的Sheet下所有Cell的值
                .map(wb -> getSheetValue(wb.getSheetAt(sheetIndex))).orElse(null);
    }

    /** 获取workbook下的名称叫做sheetName的sheet的所有cell的数据 通过row分组 */
    public static Map<Integer, Map<Integer, Object>> getSheetValue(Workbook workbook, String sheetName) {
        return ofNullable(workbook).map(wb -> getSheetValue(wb.getSheet(sheetName))).orElse(null);
    }

    /** 获取sheet下的第rowIndex行的所有cell的数据 */
    public static Map<Integer, Object> getRowValue(Sheet sheet, int rowIndex) {
        // 如果 rowIndex大于sheet的最后边的row 则返回null
        return ofNullable(sheet).filter(s -> rowIndex <= s.getLastRowNum())
                // 否则输出对应的Row下所有Cell的值
                .map(s -> getRowValue(s.getRow(rowIndex))).orElse(null);
    }

    /** 获取sheet下的第colIndex列的所有cell的数据 */
    public static Map<Integer, Object> getColValue(Sheet sheet, int colIndex) {
        return ofNullable(sheet).map(s -> {
            // 否则输出所有cell的值
            Map<Integer, Object> result = null;
            for (int j = s.getFirstRowNum(); j <= s.getLastRowNum(); j++) {
                Row row = s.getRow(j);
                if (row == null) {
                    continue;
                }
                // 如果 colIndex 不在对应 row的cell范围内 则输出null
                if (colIndex < row.getFirstCellNum() ||  colIndex > row.getLastCellNum() - 1) {
                    break;
                }
                Object cellValue = getCellValue(s.getRow(j), colIndex);
                if (cellValue != null) {
                    if (result == null) {
                        result = new LinkedHashMap<>(s.getPhysicalNumberOfRows());
                    }
                    result.put(j, cellValue);
                }
            }
            return result;
        }).orElse(null);
    }

    /** 获取sheet下的第cellIndex行第colIndex列的cell的数据 */
    public static Object getCellValue(Sheet sheet, int rowIndex, int colIndex) {
        // 如果 rowIndex大于sheet的最后边的row 则返回null
        return ofNullable(sheet).filter(s -> rowIndex <= s.getLastRowNum())
                // 否则输出对应的Cell的值
                .map(s -> getCellValue(s.getRow(rowIndex), colIndex)).orElse(null);
    }

    /** 获取row下的第cellIndex行的cell的数据 */
    public static Object getCellValue(Row row, int cellIndex) {
        // 如果 cellIndex大于row的最后边的cell 则返回null
        return ofNullable(row).filter(r -> cellIndex < r.getLastCellNum())
                // 否则输出对应的Cell的值
                .map(r -> getCellValue(r.getCell(cellIndex))).orElse(null);
    }
    //endregion

}
