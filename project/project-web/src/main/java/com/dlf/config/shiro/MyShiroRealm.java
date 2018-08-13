package com.dlf.config.shiro;

import com.dlf.business.manager.redis.RedisService;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.FunDTO;
import com.dlf.model.dto.user.UserReqDTO;
import com.dlf.model.dto.user.UserResDTO;
import com.dlf.model.enums.RedisPrefixEnums;
import com.dlf.model.vo.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Created by Administrator on 2017/12/11.
 * 自定义权限匹配和账号密码匹配
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Value("${spring.redis.timeout}")
    private String redisTimeout;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(userInfo.getId()),SecurityUtils.getSubject().getPrincipals());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //赋予角色
        List<FunDTO> funList = userService.getFunListByUser(userInfo.getId());

//        info.addRole(roleInfo.getName());

//        for (SysRole role : userInfo.getRoleList()) {
//            authorizationInfo.addRole(role.getRole());
//            for (SysPermission p : role.getPermissions()) {
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号
        String username = (String) token.getPrincipal();
        //通过username从数据库中查找 User对象
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserReqDTO reqDTO = new UserReqDTO();
        reqDTO.setUsername(username);
        GlobalResultDTO resultDTO = userService.queryUserByUsername(reqDTO);
        if(!resultDTO.isSuccess()){
            throw new AuthenticationException(resultDTO.getMsg());
        }
        UserResDTO resDTO = (UserResDTO)resultDTO.getData();
        return new SimpleAuthenticationInfo(resDTO, resDTO.getPassword(), getName());
    }
}