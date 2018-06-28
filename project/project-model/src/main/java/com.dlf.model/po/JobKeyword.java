package com.dlf.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class JobKeyword {
    private Long id;

    private Integer batchNum;

    private String website;

    private String keyword;

    private Integer keywordNum;

    private Long createUserid;

    private String createUsername;

    private Date createTime;

    private Long updateUserid;

    private String updateUsername;

    private Date updateTime;

    private Boolean isDeleted;

}