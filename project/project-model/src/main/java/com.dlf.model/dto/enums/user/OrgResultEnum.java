package com.dlf.model.dto.enums.user;

public enum OrgResultEnum {

    ORG_TREE_EMPTY("org_001", "没有节点数据"),
    ;

    private String code;
    private String msg;

    OrgResultEnum(String code, String msg) {
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