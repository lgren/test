package com.lgren.poi.poi3_17.readExcel;

import com.lgren.common.CommResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Map;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * 读取workbook的类
 * @author Lgren
 * @create 2018-11-30 9:21
 **/
public class LReadWorkbook {
    /** 通过输入流inp获取Workbook */
    public static CommResult<Workbook> getWorkbook(InputStream inp) throws IOException {
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

    private Workbook workbook;

    //region 构造区
    public LReadWorkbook(Workbook workbook) {
        Objects.requireNonNull(workbook);
        this.workbook = workbook;
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

    public void setWorkbook(Workbook workbook) {
        Objects.requireNonNull(workbook);
        this.workbook = workbook;
    }
}
