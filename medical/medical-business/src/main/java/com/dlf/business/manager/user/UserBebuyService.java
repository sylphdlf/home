package com.dlf.business.manager.user;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserBebuyReqDTO;
import com.dlf.model.dto.user.UserBebuyResDTO;

public interface UserBebuyService {
    /**
     * 通过用户名获取用户
     * @param reqDTO
     * @return
     */
    GlobalResultDTO queryUserBebuyByUsername(UserBebuyReqDTO reqDTO);
}
