package com.dlf.model.dto.person;

import com.dlf.model.dto.user.UserReqDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonInfoDTO {
	//用户名
	 private String username;
	 //手机号
	 private String phonenumber;
	 //邮箱
	 private String mailbox;
	 //验证码
	 private String checkCode;
	 
}
