package com.dlf.model.dto.person;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 身份信息插入更新传入参数
 *
 */
@Data
@NoArgsConstructor
public class IdNumberDTO {
//	id
	private Long id;
//	用户名
	private String username;
//	用户姓名
	private String fullname;
//	用户身份证号码
	private String idnumber;
//	创建时间
	private Date createTime;
//	更新时间
	private Date updateTime;
//	是否删除
	private Byte isDeleted;
}
