package com.dlf.model.enums;

public enum GlobalEnum {

    IS_DELETED_0(0,"正常"),
    IS_DELETED_1(1,"删除"),
    ;

    private Integer code;
    private String msg;

    GlobalEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}