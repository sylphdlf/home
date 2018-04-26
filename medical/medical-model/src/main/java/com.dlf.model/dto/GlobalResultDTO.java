package com.dlf.model.dto;


import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/4.
 */
public class GlobalResultDTO<T> implements Serializable{

    private static final long serialVersionUID = 6580635395865198847L;
    /**
     * 正确代码
     */
    public static final String SUCCESS_CODE = "0";

    /**
     * 错误代码
     */
    public static final String ERROR_CODE = "1";


    /**
     * 返回（正确/错误）代码
     */
    protected String code;

    /**
     * 返回信息描述
     */
    protected String msg;

    /**
     * 返回结果集
     */
    private T data;


    public GlobalResultDTO() {
        this.code = SUCCESS_CODE;
    }

    /**
     * 成功
     *
     * @param data
     */
    public GlobalResultDTO(T data) {
        this.code = SUCCESS_CODE;
        this.data = data;
    }

    /**
     * 成功
     *
     * @param msg
     * @param data
     */
    public GlobalResultDTO(String msg, T data) {
        this.code = SUCCESS_CODE;
        this.msg = msg;
        this.data = data;
    }


    /**
     * 成功or失败
     *
     * @param msg
     * @param isSuccess
     */
    public GlobalResultDTO(String msg, boolean isSuccess) {
        if (!isSuccess) {
            this.code = ERROR_CODE;
        }
        this.msg = msg;
    }

    /**
     * 成功or失败
     *
     * @param msg
     * @param isSuccess
     */
    public GlobalResultDTO(String msg, T data, boolean isSuccess) {
        if (!isSuccess) {
            this.code = ERROR_CODE;
        } else {
            this.code = SUCCESS_CODE;
        }
        this.msg = msg;
        this.data = data;
    }


    public GlobalResultDTO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public GlobalResultDTO(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public boolean isSuccess() {
        return StringUtils.equalsIgnoreCase(SUCCESS_CODE, code);
    }


    public static GlobalResultDTO SUCCESS() {
        GlobalResultDTO resultDto = new GlobalResultDTO();
        resultDto.setCode(SUCCESS_CODE);
        return resultDto;
    }

    public static GlobalResultDTO SUCCESS(String message) {
        GlobalResultDTO resultDto = new GlobalResultDTO();
        resultDto.setCode(SUCCESS_CODE);
        resultDto.setMsg(message);
        return resultDto;
    }

    public static GlobalResultDTO FAIL(String message) {
        return new GlobalResultDTO(message, false);
    }
    public static GlobalResultDTO FAIL(String code, String message) {
        return new GlobalResultDTO(code, message);
    }

}
