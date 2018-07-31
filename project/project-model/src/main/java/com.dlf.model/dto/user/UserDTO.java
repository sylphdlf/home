package com.dlf.model.dto.user;

import com.dlf.model.dto.PageDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDTO{

    private String username;

    private String realName;

    private Integer gender;

    private String mobile;

    private String telephone;

    private Date createTime;
}
