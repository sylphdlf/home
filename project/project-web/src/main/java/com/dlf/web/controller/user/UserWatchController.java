package com.dlf.web.controller.user;

import com.dlf.business.anno.FunDescriptionAnno;
import com.dlf.business.anno.MenuDescriptionAnno;
import com.dlf.business.manager.redis.RedisService;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserDTO;
import com.dlf.model.dto.user.UserReqDTO;
import com.dlf.model.dto.user.UserSearchDTO;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * 用户
 */
@RestController
@RequestMapping("/userWatch")
@MenuDescriptionAnno(name = "在线用户管理", parent = "用户管理")
public class UserWatchController {

    @Autowired
    UserService userService;

    @FunDescriptionAnno(name = "查询")
    @RequestMapping(value = "/searchOnlineUsers",method = RequestMethod.POST)
    public GlobalResultDTO searchOnlineUsers(@RequestBody UserSearchDTO searchDTO) throws IOException, ClassNotFoundException {
        return userService.searchOnlineUsers(searchDTO);
    }

    @FunDescriptionAnno(name = "强制下线")
    @RequestMapping(value = "/kickOffUser",method = RequestMethod.POST)
    public GlobalResultDTO kickOffUser(@RequestBody UserReqDTO userReqDTO) {
        return userService.kickOffUser(userReqDTO);
    }

    public static void main(String[] args) {
        Socket socket = new Socket();
    }

}
