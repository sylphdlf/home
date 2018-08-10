package com.dlf.business.manager.message.impl;

import com.dlf.business.exception.MyException;
import com.dlf.business.manager.message.MessagePushService;
import com.dlf.business.manager.redis.RedisService;
import com.dlf.common.utils.CodeGenerateUtils;
import com.dlf.common.utils.message.MailSendUtils;
import com.dlf.common.utils.message.SmsSendUtils;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.enums.RedisPrefixEnums;
import com.dlf.model.dto.message.MsgReqDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/29.
 */
@Service
public class MessagePushServiceImpl implements MessagePushService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RedisService redisService;

    private static final String MAIL_FROM = "dailinfeng@ailchain.io";

    private static final String MAIL_SUBJECT = "注册验证码";

    private static final Long EXPIRE_TIME_FOR_VERIFY = 300L;
    //发送限制，一分钟一次
    private static final Long SEND_LIMIT_BY_TIME = 60L;

    private static final Long SEND_LIMIT_RESET_TIME = 86400L;

    private static final int SEND_LIMIT_MAX_TIME = 5;

    public GlobalResultDTO messagePush(MsgReqDTO msgReqDTO, String redisPrefix) {
        try {
            //重复发送校验
            sendVerify(msgReqDTO.getTarget());
            int checkCode = CodeGenerateUtils.getCheckCode6();
            //判断手机或者邮箱
            if(SmsSendUtils.isPhone(msgReqDTO.getTarget())){
                SmsSendUtils.send(msgReqDTO.getTarget(), checkCode + "");
            }else if(MailSendUtils.isEmail(msgReqDTO.getTarget())){
                MailSendUtils.send(mailSender,msgReqDTO.getTarget(),MAIL_FROM,MAIL_SUBJECT,"验证码" + checkCode + "即将在" + 300L/60 + "分钟后失效");
            }else{
                throw new MyException("请传入正确的手机或邮箱");
            }
            redisService.put(redisPrefix + msgReqDTO.getTarget(), checkCode + "", EXPIRE_TIME_FOR_VERIFY);
            return GlobalResultDTO.SUCCESS("验证码将在 " +300L/60+ " 分钟后失效");
        }catch (Throwable e){
            e.printStackTrace();
            return GlobalResultDTO.FAIL("验证码发送失败");
        }
    }

    public GlobalResultDTO messagePushToMQ(MsgReqDTO msgReqDTO) {
        return null;
    }

    public GlobalResultDTO messagePullFromMQ(MsgReqDTO msgReqDTO) {
        return null;
    }

    /**
     * 重复发送校验
     * @param target
     */
    private void sendVerify(String target) throws MyException{
        String checkCodeSendLimit = redisService.get(RedisPrefixEnums.SEND_LIMIT_PER_TIME.getCode() + target);
//        String checkCodeMaxLimit = redisService.get(RedisPrefixEnums.SEND_LIMIT_MAX_TIME.getCode() + target);
        if(StringUtils.isNotBlank(checkCodeSendLimit)){
            throw new MyException("验证码发送间隔为" + SEND_LIMIT_BY_TIME + "秒");
        }else{
            //发送做1分钟的限制
            redisService.put(RedisPrefixEnums.SEND_LIMIT_PER_TIME.getCode() + target, "true", SEND_LIMIT_BY_TIME);

        }
//        if(StringUtils.isNotBlank(checkCodeMaxLimit) && Integer.parseInt(checkCodeMaxLimit) >5 ) {
//            throw new MyException("每天最大发送次数为" + SEND_LIMIT_MAX_TIME + "次");
//        }else{
//            //同一个号码或邮箱一天只能发送5次
//            redisService.put(RedisPrefixEnums.SEND_LIMIT_MAX_TIME.getCode() + target, "true", SEND_LIMIT_RESET_TIME);
//        }
    }
}
