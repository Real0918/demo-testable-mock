package com.example.demo.exception;


/**
 * @author ww
 * @description 业务异常的封装
 */
public class BusinessException extends BaseException {

    public BusinessException(BusinessExceptionEnum businessExceptionEnum) {
        this(businessExceptionEnum.getCode(), businessExceptionEnum.getMessage());
    }

    public BusinessException(int code, String message) {
        super(code, message);
    }
}
