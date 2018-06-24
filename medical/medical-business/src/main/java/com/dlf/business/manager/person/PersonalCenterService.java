package com.dlf.business.manager.person;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.person.IdNumberDTO;
import com.dlf.model.dto.person.IdNumberResultDTO;
import com.dlf.model.dto.person.PersonInfoDTO;
import com.dlf.model.dto.person.PersonInfoResultDTO;
import com.dlf.model.dto.person.PayMethodDTO;
import com.dlf.model.dto.person.PayMethodResultDTO;

public interface PersonalCenterService {
	/**
	 *	查询邮箱和手机号信息
	 */
	GlobalResultDTO<PersonInfoResultDTO> queryPhonenumberAndMailbox(PersonInfoDTO reqDTO);
	
	/**
	 *	添加邮箱和手机号信息
	 */
	GlobalResultDTO updatePhonenumberAndMailbox(PersonInfoDTO personInfo, Boolean deleteKey, String redisPrefix);
	  /**
     * 添加更新身份信息接口
     */
	GlobalResultDTO updateIdNumber(IdNumberDTO idNumber);
	  /**
     * 查询更新身份信息接口
     */
	GlobalResultDTO<IdNumberResultDTO> selectIdNumber(PersonInfoDTO personInfo);
	
	  /**
     * 添加更新身份信息接口
     */
	GlobalResultDTO updatePayMethod(PayMethodDTO payMethod);
	  /**
     * 查询更新身份信息接口
     */
	GlobalResultDTO<PayMethodResultDTO> selectPayMethod(PersonInfoDTO personInfo);
	
	
}
