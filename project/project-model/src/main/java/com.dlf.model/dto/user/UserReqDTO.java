package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserReqDTO {
    //用户名
    private String username;
    //密码值
    private String password;
    //再次输入密码
    private String passwordRepeat;
    //真实姓名
    private String realName;
    //手机
    private String mobile;
    //座机
    private String telephone;
    //性别
    private Integer gender;
    //组织机构代码
    private String orgCode;
    //验证码
    private String checkCode;
    //备注
    private String remarks;
}
