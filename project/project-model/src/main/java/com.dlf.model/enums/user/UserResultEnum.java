package com.dlf.model.enums.user;

public enum UserResultEnum {

    TOKEN_ERROR("user_001","TOKEN_ERROR"),
    PERMISSION_DENIED("user_002","PERMISSION_DENIED"),
    USERNAME_OR_PASSWORD_ERROR("user_003","USERNAME_OR_PASSWORD_ERROR"),
    REGISTER_FAIL("user_004","注册失败"),
    USERNAME_NULL("user_005","用户名不能为空"),
    PASSWORD_NULL("user_006","密码不能为空"),
    PASSWORD_REPEAT_NULL("user_007","请再次输入密码"),
    PASSWORD_REPEAT_NOT_MATCH("user_008","2次输入的密码不一致"),
    USER_NULL("user_009","没有此用户，请确认用户名是否输入正确"),
    USERNAME_EXIST("user_010","该用户已存在"),
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