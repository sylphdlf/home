package com.dlf.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class Question {
    private Long id;

    private String name;

    private Integer type;

    private String content;

    private String description;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;

}