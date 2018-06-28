package com.dlf.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class PayNotifyLog {
    private Long id;

    private Byte channel;

    private String content;

    private Date createTime;

    private Date updateTime;

    private Byte isDeleted;
}