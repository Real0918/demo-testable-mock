package com.example.demo.utils;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.IResultCode;

import java.util.Objects;

/**
 * @version 1.0
 * @author: WangWei
 * @date: 2021/4/12 11:31:21
 */
public class Asserts {

    private static void throwException(IResultCode resultCode) {
        throw new BusinessException(resultCode.getCode(), resultCode.getMessage());
    }

    public static void notNull(Object obj, IResultCode resultCode) {
        if (Objects.isNull(obj)) {
            throwException(resultCode);
        }
    }

    public static void notEquals(Object obj1, Object obj2, IResultCode resultCode) {
        if (!Objects.equals(obj1, obj2)) {
            throwException(resultCode);
        }
    }

}
