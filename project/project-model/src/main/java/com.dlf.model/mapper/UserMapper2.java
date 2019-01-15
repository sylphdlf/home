package com.dlf.model.mapper;

import com.dlf.model.dto.user.FunDTO;
import com.dlf.model.dto.user.RoleDTO;
import com.dlf.model.dto.user.UserReqDTO;
import com.dlf.model.dto.user.UserSearchDTO;
import com.dlf.model.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper2 extends UserMapper{
    /**
     * 通过用户名获取用户
     * @param user
     * @return
     */
    User selectByUsername(User user);

    /**
     * 获取所有用户
     * @return
     */
    Set<String> getAllUsername();

    /**
     * 更新密码
     * @param user
     */
    int updatePwdByUsername(User user);

    List<RoleDTO> getRoleIdsByUserId(Long id);

    int insertUserRoles(UserReqDTO reqDTO);

    int delUserRoles(UserReqDTO reqDTO);

    void insertWithIdReturn(User user);
}