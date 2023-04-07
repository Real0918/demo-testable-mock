package com.example.demo.exception;

/**
 * @author ww
 * @description 业务异常的封装
 */
public class BaseException extends RuntimeException {

    /**
     * 友好提示的code码
     */
    private int friendlyCode;

    /**
     * 友好提示
     */
    private String friendlyMsg;

    protected BaseException(int friendlyCode, String friendlyMsg) {
        this.setValues(friendlyCode, friendlyMsg);
    }


    private void setValues(int friendlyCode, String friendlyMsg) {
        this.friendlyCode = friendlyCode;
        this.friendlyMsg = friendlyMsg;
    }

    public int getCode() {
        return friendlyCode;
    }

    @Override
    public String getMessage() {
        return friendlyMsg;
    }

}
