package com.dlf.business.manager.message;

import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.message.MsgReqDTO;
import com.dlf.model.dto.user.UserReqDTO;

/**
 * Created by Administrator on 2017/5/29.
 */
public interface MessagePushService {
    /**
     * 消息发送
     * @param msgReqDTO
     * @return
     */
    GlobalResultDTO messagePush(MsgReqDTO msgReqDTO, String redisPrefix);

    /**
     * 消息发送到MQ
     * @param msgReqDTO
     * @return
     */
    GlobalResultDTO messagePushToMQ(MsgReqDTO msgReqDTO);

    /**
     * 从MQ中获取消息
     * @param msgReqDTO
     * @return
     */
    GlobalResultDTO messagePullFromMQ(MsgReqDTO msgReqDTO);

}
