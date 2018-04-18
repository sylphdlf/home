package com.dlf.web.controller;

import com.dlf.business.manager.python.IPythonDataTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/7.
 */
@Controller
public class LoginAction {

    @Autowired
    IPythonDataTransferService pythonDataTransferService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model){
        return "login";
    }

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main(){
        return "main";
    }
}
