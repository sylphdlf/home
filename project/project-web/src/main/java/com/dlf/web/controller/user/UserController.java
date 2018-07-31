package com.dlf.web.controller.user;

import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 查询用户列表
     * @param searchDTO
     * @return
     */
    @RequestMapping(value = "/queryListByParams",method = RequestMethod.POST)
    public GlobalResultDTO queryListByParams(@RequestBody UserSearchDTO searchDTO) {
        return userService.queryListByParams(searchDTO);
    }
}
