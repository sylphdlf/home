package com.dlf.business.manager.user.imp;

import com.dlf.business.manager.user.UserBebuyService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserBebuyReqDTO;
import com.dlf.model.dto.user.UserBebuyResDTO;
import com.dlf.model.mapper.UserBebuyMapper2;
import com.dlf.model.po.UserBebuy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserBebuyServiceImpl implements UserBebuyService {

    @Resource
    private UserBebuyMapper2 userBebuyMapper;

    public GlobalResultDTO queryUserBebuyByUsername(UserBebuyReqDTO reqDTO) {
        try {
            if(StringUtils.isBlank(reqDTO.getUsername())){
                //TODO throws
            }
            UserBebuy userBebuy = new UserBebuy();
            BeanUtils.copyProperties(reqDTO, userBebuy);
            userBebuy = userBebuyMapper.selectByUsername(userBebuy);
            UserBebuyResDTO resDTO = new UserBebuyResDTO();
            BeanUtils.copyProperties(userBebuy, resDTO);
            return new GlobalResultDTO<UserBebuyResDTO>(resDTO);
        }catch (Exception e){
            e.printStackTrace();
            return GlobalResultDTO.FAIL(e.getMessage());
        }
    }
}
