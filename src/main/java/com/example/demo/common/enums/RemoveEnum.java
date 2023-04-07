package com.example.demo.common.enums;


/**
 * @version 1.0
 * @author: WangWei
 * @date: 2021/01/13 11:26:32
 */
public enum RemoveEnum {

    /**
     * 数据状态
     */
    ALIVE(0, "启用"),
    DELETE(1, "删除"),
    ;

    RemoveEnum(Integer code, String message) {
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

    public static RemoveEnum getEnum(Integer code) {
        RemoveEnum[] values = RemoveEnum.values();
        for (RemoveEnum eachEnum : values) {
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
