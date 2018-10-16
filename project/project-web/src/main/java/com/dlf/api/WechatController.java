package com.dlf.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/wechat")
public class WechatController {
    /**
     * 自动回复
     * @return
     */
    @RequestMapping(value = "/autoReply",method = RequestMethod.GET)
    public String autoReply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String echostr = request.getParameter("echostr");
        System.out.println(echostr);
        return echostr;
    }
}
