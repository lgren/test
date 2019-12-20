package com.lgren.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommResultNew<T> implements Serializable{
    private static final long serialVersionUID = -1L;
    // 结果
    private boolean success;
    //错误编号
    private String code;
    //详情
    private String message;
    // 方法返回结果
    private T data;

    /**
     * 构建错误对象
     * @param errorCode 错误编号
     * @param errorMsg 错误详情
     */
    public static <T> CommResultNew<T> newFailure(String errorCode, String errorMsg) {
        CommResultNew<T> result=new CommResultNew<>();
        result.setSuccess(false);
        result.setCode(errorCode);
        result.setMessage(errorMsg);
        return result;
    }

    /**
     * 构建成功对象
     * @param data 返回数据
     */
    public static <T> CommResultNew<T> newSuccess(T data) {
        CommResultNew<T> result=new CommResultNew<>();
        result.setData(data);
        result.setSuccess(true);
        result.setCode("200");
        result.setMessage("操作成功");
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "\"success\":" + success +
                ",\"errorCode\":\"" + code + '\"' +
                ",\"errorMsg\":\"" + message + '\"' +
                ",\"data\":" + data +
                "}";
    }
}
