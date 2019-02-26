package com.dlf.business.manager.wechat;

public interface WechatService {
    /**
     * 自动回复
     * @param xmlStr
     * @param timestamp
     * @param signature
     * @param nonce
     * @return
     */
    String autoReply(String xmlStr, String signature, String timestamp, String nonce);
}
