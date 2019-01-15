package com.dlf.api;

import com.dlf.business.anno.UrlPermissionIgnoreAnno;
import com.dlf.business.manager.wechat.WechatService;
import com.dlf.common.utils.wxUtils.AesException;
import com.dlf.common.utils.wxUtils.WXBizMsgCrypt;
import com.dlf.common.utils.wxUtils.XMLParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    WechatService wechatService;
    /**
     * 自动回复
     * @return
     * 参数signature，timestamp，nonce，echostr
     */
    @UrlPermissionIgnoreAnno
    @RequestMapping(value = "/autoReply",method = {RequestMethod.GET, RequestMethod.POST})
    public String autoReply(HttpServletRequest req, HttpServletResponse res) throws IOException, AesException {

        String signature = req.getParameter("msg_signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");

        BufferedReader in = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuilder stb = new StringBuilder();
        String line = null;
        while ((line = in.readLine()) != null) {
            stb.append(line);
        }
//        String returnStr2 = "<xml><ToUserName><![CDATA[o7vpH6J67LgGwnBp_v_HNgFXKZZg]]></ToUserName><FromUserName><![CDATA[gh_fc5930619f5b]]></FromUserName><CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你好]]></Content></xml>";
        return wechatService.autoReply(stb.toString(), signature, timestamp, nonce);
    }
}
