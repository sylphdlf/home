package com.dlf.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.dlf.model.dto.person.IdNumberDTO;
import com.dlf.model.dto.person.IdNumberResultDTO;
import com.dlf.model.dto.person.PayMethodDTO;
import com.dlf.model.dto.person.PayMethodResultDTO;
import com.dlf.model.dto.person.PersonInfoDTO;
import com.dlf.model.dto.person.PersonInfoResultDTO;
import com.dlf.model.dto.person.PicturePathDTO;

@Mapper
public interface PersonalCenterMapper {
	/**
	 * 查询邮箱和手机号信息
	 */
	PersonInfoResultDTO selectPhoneAndMailbox(PersonInfoDTO personInfo);

	/**
	 * 更新邮箱和手机号信息
	 */
	void updatePhoneAndMailbox(PersonInfoDTO personInfo);

	/**
	 * 插入图片地址相关信息
	 */
	void insertPicturePath(PicturePathDTO picturePath);

	/**
	 * 更新图片地址相关信息
	 */
	int updatePicturePath(PicturePathDTO picturePath);

	/**
	 * 更新身份信息
	 */
	int updateIdNumber(IdNumberDTO idNumber);

	/**
	 * 添加身份信息
	 */
	int insertIdNumber(IdNumberDTO idNumber);

	/**
	 * 查询身份信息
	 */
	IdNumberResultDTO selectIdNumber(PersonInfoDTO personInfo);

	/**
	 * 更新支付信息
	 */
	int updatePayMethod(PayMethodDTO idNumber);

	/**
	 * 添加支付信息
	 */
	int insertPayMethod(PayMethodDTO idNumber);

	/**
	 * 查询身份信息
	 */
	PayMethodResultDTO selectPayMethod(PersonInfoDTO personInfo);

}
