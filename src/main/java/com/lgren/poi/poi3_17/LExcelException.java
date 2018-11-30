package com.lgren.poi.poi3_17;

/**
 * Excel 异常
 * @author Lgren
 * @create 2018-11-30 8:26
 **/
public class LExcelException extends RuntimeException {
    private String code;

    LExcelException(String code, String message) {
        super(message);
        this.code = code;
    }

    LExcelException(Throwable cause) {
        super(cause);
    }

    LExcelException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
