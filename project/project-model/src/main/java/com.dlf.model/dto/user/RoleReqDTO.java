package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleReqDTO{
    //角色编号
    private String code;
    //角色名称
    private String name;
    //备注
    private String remarks;
}
