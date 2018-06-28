package com.dlf.business.manager.person.impl;

import java.util.Date;

import javax.annotation.Resource;


import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


import com.dlf.business.manager.person.PersonalCenterService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.person.IdNumberDTO;
import com.dlf.model.dto.person.IdNumberResultDTO;
import com.dlf.model.dto.person.PayMethodDTO;
import com.dlf.model.dto.person.PayMethodResultDTO;
import com.dlf.model.dto.person.PersonInfoDTO;
import com.dlf.model.dto.person.PersonInfoResultDTO;
import com.dlf.model.mapper.PersonalCenterMapper;
import com.dlf.model.mapper.UserMapper2;
import com.dlf.model.po.User;

/**
 * @author gyy
 *
 */
@Service
public class PersonalCenterServiceImpl implements PersonalCenterService {

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Resource
	PersonalCenterMapper PersonalCenterMapper;
	@Resource
	private UserMapper2 userMapper;

	/**
	 * 查询邮箱和手机号信息
	 */

	@Override
	public GlobalResultDTO<PersonInfoResultDTO> queryPhonenumberAndMailbox(PersonInfoDTO reqDTO) {
		GlobalResultDTO<PersonInfoResultDTO> response = new GlobalResultDTO<PersonInfoResultDTO>();
		// 去数据库查询邮箱和手机号信息
		PersonInfoResultDTO personInfoResult = PersonalCenterMapper.selectPhoneAndMailbox(reqDTO);
		response.setMsg("查询邮箱或者手机号信息成功");
		response.setData(personInfoResult);
		return response;
	}

	/**
	 * 添加邮箱或者手机号信息
	 */

	@Override
	public GlobalResultDTO updatePhonenumberAndMailbox(PersonInfoDTO personInfo, Boolean deleteKey,
			String redisPrefix) {
		// 验证码验证
		ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
		String checkCode = valueOperations.get(redisPrefix + personInfo.getUsername());
		if (StringUtils.isBlank(checkCode)) {

			return GlobalResultDTO.FAIL("验证码已失效");
		} else if (!checkCode.equals(personInfo.getCheckCode())) {
			return GlobalResultDTO.FAIL("验证码不匹配");
		} else {
			// 删除key
			if (deleteKey) {
				stringRedisTemplate.delete(redisPrefix + personInfo.getUsername());
			}
			User user = new User();
			user.setUsername(personInfo.getUsername());
			user = userMapper.selectByUsername(user);
			if (null != user) {
				// 更新手机号或者邮箱信息
				PersonalCenterMapper.updatePhoneAndMailbox(personInfo);
			}
		}
		return GlobalResultDTO.SUCCESS();
	}

	/**
	 * 添加更新身份证信息
	 */
	@Override
	public GlobalResultDTO updateIdNumber(IdNumberDTO idNumber) {
		if (idNumber.getUsername() == null || "".equals(idNumber.getUsername()) || idNumber.getUsername().isEmpty()) {
			return GlobalResultDTO.FAIL("用户名为空");
		} else if (idNumber.getFullname() == null || "".equals(idNumber.getFullname())
				|| idNumber.getFullname().isEmpty()) {
			return GlobalResultDTO.FAIL("姓名为空，用户忘记填写姓名了");
		} else if (idNumber.getIdnumber() == null || "".equals(idNumber.getIdnumber())
				|| idNumber.getIdnumber().isEmpty()) {
			return GlobalResultDTO.FAIL("身份证号码为空，用户忘记填写身份证号码了");
		}

		idNumber.setUpdateTime(new Date());
		// 先更新再插入
		int a = PersonalCenterMapper.updateIdNumber(idNumber);
		if (a == 0) {
			idNumber.setCreateTime(new Date());
			PersonalCenterMapper.insertIdNumber(idNumber);
		}
		return GlobalResultDTO.SUCCESS("身份信息插入或更新成功");
	}

	/**
	 * 查询身份证信息
	 */
	@Override
	public GlobalResultDTO<IdNumberResultDTO> selectIdNumber(PersonInfoDTO personInfo) {
		GlobalResultDTO<IdNumberResultDTO> response = new GlobalResultDTO<IdNumberResultDTO>();
		IdNumberResultDTO idNumberResult = PersonalCenterMapper.selectIdNumber(personInfo);
		response.setMsg("查询身份证信息成功");
		response.setData(idNumberResult);
		return response;
	}

	/**
	 * 插入更新支付方式信息
	 */
	@Override
	public GlobalResultDTO updatePayMethod(PayMethodDTO payMethod) {
		if (payMethod.getUsername() == null || "".equals(payMethod.getUsername())
				|| payMethod.getUsername().isEmpty()) {
			return GlobalResultDTO.FAIL("用户名为空");
		}
		payMethod.setUpdateTime(new Date());
		// 先更新再插入
		int a = PersonalCenterMapper.updatePayMethod(payMethod);
		if (a == 0) {
			payMethod.setCreateTime(new Date());
			PersonalCenterMapper.insertPayMethod(payMethod);
		}
		return GlobalResultDTO.SUCCESS("支付信息插入或更新成功");
	}

	/**
	 * 查询支付方式信息
	 */
	@Override
	public GlobalResultDTO<PayMethodResultDTO> selectPayMethod(PersonInfoDTO personInfo) {
		GlobalResultDTO<PayMethodResultDTO> response = new GlobalResultDTO<PayMethodResultDTO>();
		PayMethodResultDTO payMethodResult = PersonalCenterMapper.selectPayMethod(personInfo);
		response.setMsg("查询支付方式信息成功");
		response.setData(payMethodResult);
		return response;
	}

}
