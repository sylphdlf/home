package com.dlf.web.shiro;

import com.dlf.business.manager.user.UserBebuyService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserBebuyReqDTO;
import com.dlf.model.dto.user.UserBebuyResDTO;
import com.dlf.model.po.UserBebuy;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/12/11.
 * 自定义权限匹配和账号密码匹配
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserBebuyService userBebuyService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
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
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserBebuyReqDTO reqDTO = new UserBebuyReqDTO();
        reqDTO.setUsername(username);
        GlobalResultDTO<UserBebuyResDTO> resultDTO = userBebuyService.queryUserBebuyByUsername(reqDTO);
        if(!resultDTO.isSuccess()){
            return null;
        }
        UserBebuyResDTO resDTO = resultDTO.getData();
//        if (userInfo.getState() == 1) { //账户冻结
//            throw new LockedAccountException();
//        }
        //用户名//密码
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(resDTO.getUsername(),resDTO.getPassword(), getName());
        return authenticationInfo;
    }

}