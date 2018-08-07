package com.dlf.model.dto.enums.user;

public enum RoleResultEnum {

    ROLE_REQ_NAME_NULL("role_001", "角色名称不能为空"),
    ROLE_REQ_CODE_NULL("role_002", "角色编号不能为空"),
    ;

    private String code;
    private String msg;

    RoleResultEnum(String code, String msg) {
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