package com.dlf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dailf on 2017/7/21.
 */
@Controller
public class DashBoardController {

    @RequestMapping(value = "/dataDashboard", method = RequestMethod.GET)
    public String dataDashboard(){
        return "dashboard/dataDashboard";
    }
}
