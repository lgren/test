package com.lgren.一些写法等;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * 公共返回类
 * @create 2018/9/30 8:49
 * @author Lgren
 * @param <T>
 */
public class CommResult<T> implements Serializable{
	private static final long serialVersionUID = -1L;
	// 结果
	private boolean success;
	//错误编号
	private String errorCode;
	//错误详情
	private String errorMsg;
	// 方法返回结果
	private T data;
	
	
	public CommResult() {
		super();
	}
	
	/**
	 * 错误构建对象
	 * @param errorCode 错误编码
	 * @param errorMsg 错误内容
     */
	public static <T> CommResult<T> newFailure(String errorCode, String errorMsg) {
		CommResult<T> result=new CommResult<>();
		result.setSuccess(false);
		result.setErrorCode(errorCode);
		result.setErrorMsg(errorMsg);
		return result;
	}
	/**
	 * 成功构建对象
	 * @param data 返回内容
	 * @return
	 */
	public static <T> CommResult<T> newSuccess(T data) {
		CommResult<T> result=new CommResult<>();
		result.setData(data);
		result.setSuccess(true);
		result.setErrorCode("200");
		result.setErrorMsg("操作成功");
		return result;
	}
    public boolean getSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	
}
