package com.dlf.model.enums.stock;

public enum MarketEnums {

    WATCHING_STATUS_0(0, "不监控"),
    WATCHING_STATUS_1(1, "开启监控"),
    ;

    private Integer code;
    private String msg;

    MarketEnums(Integer code, String msg) {
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
