package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserReqDTO {
    //用户名
    private String username;
    //密码md5值
    private String password;
    //密码原值
    private String passwordStr;
    //验证码
    private String checkCode;

    private String remarks;
}
