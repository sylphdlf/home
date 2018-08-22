package com.dlf.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class BaseReqDTO {

    private Date createTime;

    private Integer isDeleted;
}
