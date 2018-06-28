package com.dlf.model.dto.enums;

public enum GlobalResultEnum {

    SUCCESS("0","success"),
    FAIL("-1","fail"),
    ;

    private String code;
    private String msg;

    GlobalResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}