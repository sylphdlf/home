package com.dlf.model.dto.person;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 查询身份信息结果集
 *
 */
@Data
@NoArgsConstructor
public class IdNumberResultDTO {
//	用户名
	private String username;
//	用户姓名
	private String fullname;
//	用户身份证号码
	private String idnumber;

}
