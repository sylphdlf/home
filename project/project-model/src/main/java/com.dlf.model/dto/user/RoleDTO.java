package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RoleDTO {

    private Long id;

    private String code;

    private String name;

    private String remarks;

    private Date createTime;
}
