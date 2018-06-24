package com.dlf.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class User {
    private Long id;

    private String username;

    private String password;

    private String passwordStr;

    private Byte status;

    private String remarks;

    private Date createTime;

    private Date updateTime;

    private Byte isDeleted;

}