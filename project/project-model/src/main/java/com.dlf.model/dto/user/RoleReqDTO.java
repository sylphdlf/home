package com.dlf.model.dto.user;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RoleReqDTO extends PageDTO {
    //角色编号
    private String code;
    //角色名称
    private String name;
}
