package com.example.demo.base;

/**
 * @version 1.0
 * @author: WangWei
 * @date: 2021/01/13 11:26:32
 */
public class ResponseResult {
    private int code;
    private String message;
    private Object result;

    public ResponseResult() {
    }

    public ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(int code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.result = object;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
