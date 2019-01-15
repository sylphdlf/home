package com.dlf.business.manager.user.impl;

import com.dlf.business.anno.ExecuteTimeAnno;
import com.dlf.business.exception.MyException;
import com.dlf.business.factory.OrgRolesFactory;
import com.dlf.business.manager.redis.RedisService;
import com.dlf.business.manager.user.UserService;
import com.dlf.common.utils.CompareUtils;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.*;
import com.dlf.model.enums.GlobalResultEnum;
import com.dlf.model.enums.RedisPrefixEnums;
import com.dlf.model.enums.user.UserResultEnums;
import com.dlf.model.mapper.FunctionMapper2;
import com.dlf.model.mapper.RoleMapper2;
import com.dlf.model.mapper.UserMapper2;
import com.dlf.model.po.User;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper2 userMapper;
    @Resource
    private FunctionMapper2 functionMapper;
    @Resource
    private RoleMapper2 roleMapper;
    @Resource
    private RedisService redisService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public GlobalResultDTO queryUserByUsername(UserReqDTO reqDTO) {
        try {
            if(StringUtils.isBlank(reqDTO.getUsername())){
                return GlobalResultDTO.FAIL(UserResultEnums.USERNAME_NULL.getMsg());
            }
            User user = new User();
            BeanUtils.copyProperties(reqDTO, user);
            user = userMapper.selectByUsername(user);
            if(null == user){
                return GlobalResultDTO.FAIL(UserResultEnums.USER_NULL.getMsg());
            }else{
                UserResDTO resDTO = new UserResDTO();
                BeanUtils.copyProperties(user, resDTO);
                return new GlobalResultDTO<UserResDTO>(resDTO);
            }
        }catch (Exception e){
            e.printStackTrace();
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
            return GlobalResultDTO.FAIL(UserResultEnums.USERNAME_EXIST.getMsg());
        }catch (MyException e){
            return GlobalResultDTO.FAIL(e.getMessage());
        }
    }

    public GlobalResultDTO checkUsername(String username) {
        SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
        //查缓存
        Set<String> usernameSet = setOperations.members(RedisPrefixEnums.USER_NAME_COMPARE_LIST_PRE.getCode());
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
                    setOperations.unionAndStore(RedisPrefixEnums.USER_NAME_COMPARE_LIST_PRE.getCode(), thisSet, RedisPrefixEnums.USER_NAME_COMPARE_LIST_PRE.getCode());
                }
            }
        }else{
            //查库
            Set<String> usernames = userMapper.getAllUsername();
            if(!CollectionUtils.isEmpty(usernames)){
                //数据放入redis中
                for(String thisSet:usernames){
                    setOperations.add(RedisPrefixEnums.USER_NAME_COMPARE_LIST_PRE.getCode(), thisSet);
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
            throw new MyException(UserResultEnums.USERNAME_NULL.getMsg());
        }else if(StringUtils.isBlank(reqDTO.getPassword())) {
            throw new MyException(UserResultEnums.PASSWORD_NULL.getMsg());
        }else if(StringUtils.isBlank(reqDTO.getPasswordRepeat())){
            throw new MyException(UserResultEnums.PASSWORD_REPEAT_NULL.getMsg());
        }else if(!reqDTO.getPassword().equals(reqDTO.getPasswordRepeat())){
            throw new MyException(UserResultEnums.PASSWORD_REPEAT_NOT_MATCH.getMsg());
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
        GlobalResultDTO resultDTO = this.checkCodeVerify(reqDTO, true, RedisPrefixEnums.PASSWORD_MESSAGE_PRE.getCode());
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

    @Override
    public List<FunDTO> getFunListByUser(Long id) {
        return functionMapper.getSourceListByUser(id);
    }

    @Override
    public List<RoleDTO> getRoleListByUser(Long id) {
        return roleMapper.getRoleListByUser(id);
    }

    @Override
    public GlobalResultDTO getRolePageByUser(UserSearchDTO searchDTO) {
        RoleSearchDTO roleSearchDTO = new RoleSearchDTO();
        BeanUtils.copyProperties(searchDTO, roleSearchDTO);
        PageHelper.startPage(searchDTO.getPageNum(), searchDTO.getPageSize());
        List<RoleDTO> list = roleMapper.queryListByParams(roleSearchDTO);
        PageInfo<RoleDTO> pageInfo = new PageInfo<RoleDTO>(list);
        if(!CollectionUtils.isEmpty(list)){
            //获取组织下的所有角色id
            Map<Long, RoleDTO> roleMap = OrgRolesFactory.getRoleMapByOrgId(searchDTO.getId());
            if(CollectionUtils.isEmpty(OrgRolesFactory.getRoleMapByOrgId(searchDTO.getId()))){
                //初始化组织对应角色列表
                List<RoleDTO> roleList = userMapper.getRoleIdsByUserId(searchDTO.getId());
                if(!CollectionUtils.isEmpty(roleList)){
                    roleMap = OrgRolesFactory.initUserRoleMapAndGet(searchDTO.getId(), roleList);
                }
            }
            //遍历返回值，查看参数是否有对应角色, 有则做标记
            if(!CollectionUtils.isEmpty(roleMap)){
                for(RoleDTO thisDTO : list){
                    if(null != roleMap.get(thisDTO.getId())){
                        thisDTO.setChecked(true);
                    }
                }
            }
        }
        return new GlobalResultDTO(pageInfo);
    }

    @Override
    @Transactional
    @ExecuteTimeAnno
    public GlobalResultDTO bindingRole(UserReqDTO reqDTO) {
        try {
            List<Long> originalIds = reqDTO.getOriginalIds();
            List<Long> changedIds = reqDTO.getChangedIds();
            //待新增的id
            List<Long> toAddIds = new ArrayList<Long>();
            //待删除的id
            List<Long> toDelIds = new ArrayList<Long>();
            CompareUtils.getAddAndDel(originalIds, changedIds, toAddIds, toDelIds);
            //操作数据库
            if(!CollectionUtils.isEmpty(toAddIds)){
                reqDTO.setToAddIds(toAddIds);
                userMapper.insertUserRoles(reqDTO);
            }
            if(!CollectionUtils.isEmpty(toDelIds)){
                reqDTO.setToDelIds(toDelIds);
                userMapper.delUserRoles(reqDTO);
            }
            //移除factory中缓存的数据
            OrgRolesFactory.removeOrgId(reqDTO.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return GlobalResultDTO.SUCCESS();
    }

    @Override
    @Transactional
    public GlobalResultDTO del(UserReqDTO reqDTO) {
        //删除用户
        int count = userMapper.deleteByPrimaryKey(reqDTO.getId());
        if(count == 1){
            //删除用户对应的角色
            roleMapper.delRoleByUserId(reqDTO.getId());
            return GlobalResultDTO.SUCCESS();
        }else{
            return GlobalResultDTO.FAIL();
        }
    }

    @Override
    public GlobalResultDTO bindingOrg(UserReqDTO reqDTO) {
        try {
            User user = userMapper.selectByPrimaryKey(reqDTO.getId());
            user.setOrgCode(reqDTO.getOrgCode());
            userMapper.updateByPrimaryKey(user);
            return GlobalResultDTO.SUCCESS();
        }catch (Exception e){
            return GlobalResultDTO.FAIL();
        }
    }

    @Override
    public GlobalResultDTO searchOnlineUsers(UserSearchDTO searchDTO) {
        List<UserResDTO> result = new ArrayList<UserResDTO>();
        Set<String> keys = redisService.getKeysByPrefix(new RedisSessionDAO().getKeyPrefix() + "*");
        if(!CollectionUtils.isEmpty(keys)){
            for(String key : keys){
                Session session = redisService.getSession(key);
                SimplePrincipalCollection spc = (SimplePrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                UserResDTO resDTO  = (UserResDTO)spc.getPrimaryPrincipal();
                resDTO.setSessionId(key);
                resDTO.setLastAccessTime(session.getLastAccessTime());
                result.add(resDTO);
            }
        }
        return new GlobalResultDTO(result);
    }

    @Override
    public GlobalResultDTO kickOffUser(UserReqDTO userReqDTO) {
        redisService.delKey(userReqDTO.getSessionId());
        return GlobalResultDTO.SUCCESS();
    }
}
