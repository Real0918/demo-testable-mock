package com.example.demo.utils;


import com.example.demo.base.ResponseResult;
import com.example.demo.exception.BusinessExceptionEnum;

/**
 * @version 1.0
 * @author: WangWei
 * @date: 2021/01/13 11:26:32
 */
public class ResultUtil {

    public static ResponseResult result(int code, String msg) {
        return result(code, msg, "");
    }

    public static ResponseResult result(int code, String msg, Object obj) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(code);
        responseResult.setMessage(msg);
        responseResult.setResult(obj);
        return responseResult;
    }

    public static ResponseResult result(BusinessExceptionEnum businessExceptionEnum) {
        return result(businessExceptionEnum.getCode(), businessExceptionEnum.getMessage());
    }

    public static ResponseResult result(BusinessExceptionEnum businessExceptionEnum, Object obj) {
        return result(businessExceptionEnum.getCode(), businessExceptionEnum.getMessage(), obj);
    }

}
