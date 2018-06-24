package com.dlf.model.dto.person;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付方式插入更新传参
 * @author gyy
 *
 */
@Data
@NoArgsConstructor
public class PayMethodDTO {
//	id
	private Long id;
//	用户名
	private String username;
//	银行卡号
	private String bankcard;
//	支付宝号
	private String alipay;
//	微信账号
	private String wechat;
//	创建时间
	private Date createTime;
//	更新时间
	private Date updateTime;
//	是否删除
	private Byte isDeleted;

}
