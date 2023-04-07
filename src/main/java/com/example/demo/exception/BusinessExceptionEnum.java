package com.example.demo.exception;


/**
 * 所有业务异常的枚举
 *
 * @version 1.0
 * @author: WangWei
 * @date: 2021/01/13 11:26:32
 */
public enum BusinessExceptionEnum implements IResultCode {


    /**
     * 系统通用错误码
     */
    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),
    SYS_ERROR(500, "系统内部错误"),


    /**
     * 系统通用错误码
     */
    PARAM_IS_NULL(10001, "参数为空"),
    PARAM_ERROR(10002, "参数错误"),
    LENGTH_LIMIT_ERROR(10003, "长度超过限制"),
    ILLEGAL_CHARACTER(10004, "包含非法字符"),
    OBJECT_EXIST(10006, "对象已存在"),
    OBJECT_NOT_EXIST(10007, "对象不存在"),
    ADD_SUCCESS(10011, "新增成功"),
    SELECT_SUCCESS(10012, "查询成功"),
    UPDATE_SUCCESS(10013, "修改成功"),
    DELETE_SUCCESS(10014, "删除成功"),
    ADD_FAIL(10015, "新增失败"),
    SELECT_FAIL(10016, "查询失败"),
    UPDATE_FAIL(10017, "修改失败"),
    DELETE_FAIL(10018, "删除失败"),
    INTERFACE_CALL_FAIL(10101, "接口调用失败"),
    INTERFACE_CALL_WITHOUT_DATA(10102, "接口调用未获取到数据"),

    /**
     * 用户相关错误码
     */
    PARAM_USER_NOT_EXIST(10201, "传入用户ID不存在"),

    /**
     * 业务相关错误码
     */
    EXAMPLE(1100, "example"),


    ;

    BusinessExceptionEnum(int code, String message) {
        this.friendlyCode = code;
        this.friendlyMsg = message;
    }


    private int friendlyCode;

    private String friendlyMsg;

    @Override
    public int getCode() {
        return friendlyCode;
    }

    @Override
    public String getMessage() {
        return friendlyMsg;
    }

}
