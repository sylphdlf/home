package com.dlf.business.manager.user.impl;

import com.alibaba.fastjson.JSONArray;
import com.dlf.business.exception.MyException;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.PageDTO;
import com.dlf.model.dto.enums.GlobalResultEnum;
import com.dlf.model.dto.enums.RedisPrefixEnums;
import com.dlf.model.dto.enums.user.UserResultEnum;
import com.dlf.model.dto.user.UserDTO;
import com.dlf.model.dto.user.UserReqDTO;
import com.dlf.model.dto.user.UserResDTO;
import com.dlf.model.dto.user.UserSearchDTO;
import com.dlf.model.mapper.UserMapper2;
import com.dlf.model.po.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper2 userMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public GlobalResultDTO queryUserByUsername(UserReqDTO reqDTO) {
        try {
            if(StringUtils.isBlank(reqDTO.getUsername())){
                return GlobalResultDTO.FAIL(UserResultEnum.USERNAME_NULL.getMsg());
            }
            User user = new User();
            BeanUtils.copyProperties(reqDTO, user);
            user = userMapper.selectByUsername(user);
            if(null == user){
                return GlobalResultDTO.FAIL(UserResultEnum.USER_NULL.getMsg());
            }else{
                UserResDTO resDTO = new UserResDTO();
                BeanUtils.copyProperties(user, resDTO);
                return new GlobalResultDTO<UserResDTO>(resDTO);
            }
        }catch (Exception e){
            return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getMsg());
        }
    }

    public GlobalResultDTO register(UserReqDTO reqDTO) {
        try {
            //注册验证
            registerVerify(reqDTO);
            User user = new User();
            BeanUtils.copyProperties(reqDTO, user);
            //FIXME 状态
            user.setStatus(1);
            //对密码做加密处理
            String afterMd5 = DigestUtils.md5Hex(user.getPassword());
            user.setPassword(afterMd5.toUpperCase());
            userMapper.insert(user);
            try {
                //更新redis用户
                SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
                setOperations.add("userNameListStr", user.getUsername());
            }catch (Exception e){
                e.printStackTrace();
            }
            return GlobalResultDTO.SUCCESS();
        }catch(DuplicateKeyException e){
            return GlobalResultDTO.FAIL(UserResultEnum.USERNAME_EXIST.getMsg());
        }catch (MyException e){
            return GlobalResultDTO.FAIL(e.getMessage());
        }
    }

    public GlobalResultDTO checkUsername(String username) {
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
        //查缓存
        Set<String> usernameSet = setOperations.members(RedisPrefixEnums.USER_NAME_COMPARE_LIST.getCode());
        if(!CollectionUtils.isEmpty(usernameSet)){
            if(usernameSet.contains(username)){
                return GlobalResultDTO.FAIL("用户名重复");
            }else{
                User user = new User();
                user.setUsername(username);
                //查询用户并更新缓存
                User resultUser = userMapper.selectByUsername(user);
                if(null != resultUser){
                    Set<String> thisSet = new HashSet<String>();
                    thisSet.add(resultUser.getUsername());
                    setOperations.unionAndStore(RedisPrefixEnums.USER_NAME_COMPARE_LIST.getCode(), thisSet, RedisPrefixEnums.USER_NAME_COMPARE_LIST.getCode());
                }
            }
        }else{
            //查库
            Set<String> usernames = userMapper.getAllUsername();
            if(!CollectionUtils.isEmpty(usernames)){
                //数据放入redis中
                for(String thisSet:usernames){
                    setOperations.add(RedisPrefixEnums.USER_NAME_COMPARE_LIST.getCode(), thisSet);
                }
                if(usernames.contains(username)){
                    return GlobalResultDTO.FAIL("用户名重复");
                }
            }else{
                return GlobalResultDTO.FAIL("没有用户数据");
            }
        }
        return GlobalResultDTO.SUCCESS();
    }

    /**
     * 注册验证
     * @param reqDTO
     * @throws MyException
     */
    private void registerVerify(UserReqDTO reqDTO) throws MyException  {
        if(StringUtils.isBlank(reqDTO.getUsername())){
            throw new MyException(UserResultEnum.USERNAME_NULL.getMsg());
        }else if(StringUtils.isBlank(reqDTO.getPassword())) {
            throw new MyException(UserResultEnum.PASSWORD_NULL.getMsg());
        }else if(StringUtils.isBlank(reqDTO.getPasswordRepeat())){
            throw new MyException(UserResultEnum.PASSWORD_REPEAT_NULL.getMsg());
        }else if(!reqDTO.getPassword().equals(reqDTO.getPasswordRepeat())){
            throw new MyException(UserResultEnum.PASSWORD_REPEAT_NOT_MATCH.getMsg());
        }
        //注册验证码验证
//            GlobalResultDTO resultDTO = checkCodeVerify(reqDTO, true, RedisPrefixEnums.REGISTER_MESSAGE.getCode());
//            if(!resultDTO.isSuccess()){
//                return resultDTO;
//            }
    }

    /**
     * 验证码验证
     * @param reqDTO
     * @throws MyException
     */
    public GlobalResultDTO checkCodeVerify(UserReqDTO reqDTO, Boolean deleteKey, String redisPrefix){
        //验证码验证
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        String checkCode = valueOperations.get(redisPrefix + reqDTO.getUsername());
        if(StringUtils.isBlank(checkCode)){

            return GlobalResultDTO.FAIL("验证码已失效");
        }else if(!checkCode.equals(reqDTO.getCheckCode())) {
            return GlobalResultDTO.FAIL("验证码不匹配");
        }else{
            //删除key
            if(deleteKey){
                stringRedisTemplate.delete(redisPrefix + reqDTO.getUsername());
            }
        }
        return GlobalResultDTO.SUCCESS();
    }

    public GlobalResultDTO resetPassword(UserReqDTO reqDTO){
        if(StringUtils.isBlank(reqDTO.getUsername())){
            return GlobalResultDTO.FAIL("用户名不能为空");
        }else if(StringUtils.isBlank(reqDTO.getPassword())){
            return GlobalResultDTO.FAIL("密码不能为空");
        }
        //验证checkCode,并删除
        GlobalResultDTO resultDTO = this.checkCodeVerify(reqDTO, true, RedisPrefixEnums.PASSWORD_MESSAGE.getCode());
        if(!resultDTO.isSuccess()){
            return resultDTO;
        }
        User user = new User();
        user.setUsername(reqDTO.getUsername());
        user = userMapper.selectByUsername(user);
        if(null != user){
            //对密码做加密处理
            String afterMd5 = DigestUtils.md5Hex(reqDTO.getPassword());
            user.setPassword(afterMd5.toUpperCase());
            int count = userMapper.updateByPrimaryKey(user);
            if(count == 1){
                return GlobalResultDTO.SUCCESS();
            }
        }else{
            return GlobalResultDTO.FAIL("没有找到相关用户");
        }
        return GlobalResultDTO.FAIL("密码更新失败");
    }

    @Override
    public GlobalResultDTO queryListByParams(UserSearchDTO searchDTO) {
        try {
            PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
            List<UserDTO> list = userMapper.queryListByParams(searchDTO);
            PageInfo<UserDTO> pageInfo = new PageInfo<UserDTO>(list);
            return new GlobalResultDTO(pageInfo);
        }catch (Exception e){
            return GlobalResultDTO.FAIL(e.getMessage());
        }
    }
}
