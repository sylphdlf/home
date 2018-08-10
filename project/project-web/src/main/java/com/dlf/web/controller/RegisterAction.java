package com.dlf.web.controller;

import com.dlf.business.manager.message.MessagePushService;
import com.dlf.business.manager.user.UserService;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.enums.RedisPrefixEnums;
import com.dlf.model.dto.message.MsgReqDTO;
import com.dlf.model.dto.user.UserReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/register")
public class RegisterAction {

    @Autowired
    private UserService userService;
    @Autowired
    private MessagePushService messagePushService;

    @RequestMapping(value = "/registerAjax",method = RequestMethod.POST)
    @ResponseBody
    public GlobalResultDTO registerAjax(@RequestBody UserReqDTO reqDTO){
        return userService.register(reqDTO);
    }

    /**
     *
     * @param reqDTO
     * @return
     */
    @RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResultDTO checkUsername(@RequestBody UserReqDTO reqDTO){
        return userService.checkUsername(reqDTO.getUsername());
    }

    /**
     * 消息发送
     * @param msgReqDTO
     * @return
     */
    @RequestMapping(value = "/messagePush", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResultDTO messagePush(@RequestBody MsgReqDTO msgReqDTO){
        return messagePushService.messagePush(msgReqDTO, RedisPrefixEnums.REGISTER_MESSAGE.getCode());
    }

    /**
     * 消息发送(忘记密码)
     * @param msgReqDTO
     * @return
     */
    @RequestMapping(value = "/messagePushForPwd", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResultDTO messagePushForPwd(@RequestBody MsgReqDTO msgReqDTO){
        return messagePushService.messagePush(msgReqDTO, RedisPrefixEnums.PASSWORD_MESSAGE.getCode());
    }

    /**
     * 验证验证码
     * @param reqDTO
     * @return
     */
    @RequestMapping(value = "/checkCodeVerify", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResultDTO checkCodeVerify(@RequestBody UserReqDTO reqDTO){
        return userService.checkCodeVerify(reqDTO, false, RedisPrefixEnums.REGISTER_MESSAGE.getCode());
    }

    /**
     * 验证验证码(密码重置)
     * @param reqDTO
     * @return
     */
    @RequestMapping(value = "/checkCodeVerifyForPwd", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResultDTO checkCodeVerifyForPwd(@RequestBody UserReqDTO reqDTO){
        return userService.checkCodeVerify(reqDTO, false, RedisPrefixEnums.PASSWORD_MESSAGE.getCode());
    }
    /**
     * 重置密码
     * @param reqDTO
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResultDTO resetPassword(@RequestBody UserReqDTO reqDTO){
        return userService.resetPassword(reqDTO);
    }
}
