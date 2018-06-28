package com.dlf.model.dto.enums.user;

public enum UserResultEnum {

    TOKEN_ERROR("0001","TOKEN_ERROR"),
    PERMISSION_DENIED("0002","PERMISSION_DENIED"),
    USERNAME_OR_PASSWORD_ERROR("0003","USERNAME_OR_PASSWORD_ERROR"),
    REGISTER_FAIL("0004","注册失败"),
    ;

    private String code;
    private String msg;

    UserResultEnum(String code, String msg) {
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