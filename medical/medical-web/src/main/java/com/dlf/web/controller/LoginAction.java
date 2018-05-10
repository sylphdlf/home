package com.dlf.web.controller;

import com.dlf.business.manager.python.IPythonDataTransferService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.user.UserBebuyReqDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/5/7.
 */
@Controller
public class LoginAction {

    @RequestMapping(value = "/loginAjax",method = RequestMethod.POST)
    @ResponseBody
    public GlobalResultDTO loginAjax(@RequestBody UserBebuyReqDTO reqDTO){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(reqDTO.getUsername(), reqDTO.getPassword());
        try {
            subject.login(token);
        }catch (Exception e){
            return GlobalResultDTO.FAIL(e.getMessage());
        }

        System.out.println(111);
        return GlobalResultDTO.SUCCESS();
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model){
        System.out.println(111);
        return "login";
    }

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main(){
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.getAndIncrement();
        return "main";
    }
}
