package com.dlf.web.controller;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.enums.GlobalResultEnum;
import com.dlf.model.dto.user.UserReqDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/5/7.
 */
@Controller
@RequestMapping("/login")
public class LoginAction {
    /**
     * 登录
     * @param reqDTO
     * @return
     */
    @RequestMapping(value = "/loginAjax",method = RequestMethod.POST)
    @ResponseBody
    public GlobalResultDTO loginAjax(@RequestBody UserReqDTO reqDTO) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(reqDTO.getUsername(), reqDTO.getPassword());
        try {
            subject.login(token);
            return GlobalResultDTO.SUCCESS(subject.getSession().getId().toString());
        } catch (Exception e) {
            return GlobalResultDTO.FAIL(e.getMessage());
        }
    }

    /**
     * 未登录
     * @param reqDTO
     * @return
     */
    @RequestMapping(value = "/unauth",method = RequestMethod.POST)
    @ResponseBody
    public GlobalResultDTO unauth(@RequestBody UserReqDTO reqDTO) {
        return GlobalResultDTO.FAIL(GlobalResultEnum.FAIL.getMsg());
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping(value = "/logoutAjax",method = RequestMethod.GET)
    @ResponseBody
    public GlobalResultDTO logoutAjax(){
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return GlobalResultDTO.SUCCESS();
        }catch (Exception e){
            e.printStackTrace();
            return GlobalResultDTO.FAIL(e.getMessage());
        }
    }
}
