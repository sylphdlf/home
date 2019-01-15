package com.dlf.model.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserReqDTO {

    private Long id;
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

    private String sessionId;

    //绑定角色
    //原始角色
    private List<Long> originalIds;
    //修改后的角色
    private List<Long> changedIds;
    //待新增的角色ID
    private List<Long> toAddIds;
    //待删除的角色ID
    private List<Long> toDelIds;

    private Date createTime;
    private Long createUserId;
    private Integer isDeleted;
}
