package com.dlf.model.dto.person;

import java.util.Date;

import com.dlf.model.po.User;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 存储上传图片相关信息入参参数集
 * @author gyy
 *
 */
@Data
@NoArgsConstructor
public class PicturePathDTO {
//	id
	private Long id;
//	用户名
	private String username;
//	照片id
	private String photoid;
//	照片存放相对地址
	private String facepath;
//	创建时间
	private Date createTime;
//	更新时间
	private Date updateTime;
//	是否删除
	private Byte isDeleted;
}
