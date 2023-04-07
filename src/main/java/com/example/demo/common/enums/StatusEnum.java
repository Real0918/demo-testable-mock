package com.example.demo.common.enums;


/**
 * @version 1.0
 * @author: WangWei
 * @date: 2021/01/13 11:26:32
 */
public enum StatusEnum {

    /**
     *
     */
    ENABLED(1, "启用"),
    DISABLED(2, "停用"),
    ;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static StatusEnum getEnum(Integer code) {
        StatusEnum[] values = StatusEnum.values();
        for (StatusEnum eachEnum : values) {
            if (null == code) {
                break;
            }
            if (eachEnum.code.equals(code)) {
                return eachEnum;
            }
        }
        return null;
    }

}
