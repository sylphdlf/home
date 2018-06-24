package com.dlf.model.dto.person;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 查询支付方式结果集
 * @author gyy
 *
 */
@Data
@NoArgsConstructor
public class PayMethodResultDTO {
//	用户名
	private String username;
//	银行卡号
	private String bankcard;
//	支付宝号
	private String alipay;
//	微信账号
	private String wechat;
}
