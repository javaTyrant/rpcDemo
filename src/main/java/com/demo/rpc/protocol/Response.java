package com.demo.rpc.protocol;


import java.io.Serializable;

@SuppressWarnings("unused")
public class Response implements Serializable {

    // 响应的错误码，正常响应为0，非0表示异常响应
    private int code = 0;

    // 异常信息
    private String errMsg;

    // 响应结果
    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
