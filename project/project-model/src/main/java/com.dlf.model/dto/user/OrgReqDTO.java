package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrgReqDTO {

    private Long id;

    private String name;

    private String code;

    private String parentCode;

    private Integer level;

    private String remarks;
}
