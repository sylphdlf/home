package com.dlf.model.enums.file;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum FileResultEnums {

    FILE_ALREADY_EXIST("file-001", "文件已经存在"),
    FILE_ALREADY_EXIST_BY_USER("file-002", "文件已经上传过了"),
    MD5_ENCODER_FAIL("file-003", "md5加密失败"),
    ;
    private String code;
    private String msg;

    FileResultEnums(String code, String msg) {
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
