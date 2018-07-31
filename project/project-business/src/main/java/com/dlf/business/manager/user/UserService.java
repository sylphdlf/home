package com.dlf.business.manager.user;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.PageDTO;
import com.dlf.model.dto.user.UserDTO;
import com.dlf.model.dto.user.UserReqDTO;
import com.dlf.model.dto.user.UserSearchDTO;

public interface UserService {
    /**
     * 通过用户名获取用户
     * @param reqDTO
     * @return
     */
    GlobalResultDTO queryUserByUsername(UserReqDTO reqDTO);

    /**
     * 用户注册
     * @param reqDTO
     * @return
     */
    GlobalResultDTO register(UserReqDTO reqDTO);

    /**
     * 检查用户名是否重复
     * @param username
     * @return
     */
    GlobalResultDTO checkUsername(String username);

    /**
     * 验证码验证
     * @param reqDTO
     * @return
     */
    GlobalResultDTO checkCodeVerify(UserReqDTO reqDTO, Boolean deleteKey, String redisPrefix);

    /**
     * 密码重置
     * @param reqDTO
     * @return
     */
    GlobalResultDTO resetPassword(UserReqDTO reqDTO);

    /**
     * 查询用户列表
     * @param searchDTO
     * @return
     */
    GlobalResultDTO queryListByParams(UserSearchDTO searchDTO);
}
