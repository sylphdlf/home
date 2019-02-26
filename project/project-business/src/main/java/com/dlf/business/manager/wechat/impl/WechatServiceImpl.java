package com.dlf.business.manager.wechat.impl;

import com.dlf.business.manager.dashboard.DashboardService;
import com.dlf.business.manager.dashboard.WeatherService;
import com.dlf.business.manager.wechat.WechatService;
import com.dlf.common.utils.wxUtils.AesException;
import com.dlf.common.utils.wxUtils.WXBizMsgCrypt;
import com.dlf.model.dto.GlobalResultDTO;
import com.dlf.model.dto.spider.WeatherDTO;
import com.dlf.model.dto.spider.WeatherSearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

@Service
public class WechatServiceImpl implements WechatService {

    private Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);
    @Autowired
    private WeatherService weatherService;

    private static final String token = "dailf";
    private static final String encodingAesKey = "qCJ0WFsv7AutDAugbkgpLMN8yRsofuhcv3PaDNlezc3";
    private static final String appId = "wx05854eeddb43cdc4";

    @Override
    public String autoReply(String inputStr, String signature, String timestamp, String nonce) {
        String resultXml = "";
        try {
            WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(token, encodingAesKey, appId);
            String decryptMsg = wxBizMsgCrypt.decryptMsg(signature, timestamp, nonce, inputStr);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

            dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
            dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            dbf.setXIncludeAware(false);
            dbf.setExpandEntityReferences(false);
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(decryptMsg);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);

            Element root = document.getDocumentElement();
            String toUserName = root.getElementsByTagName("ToUserName").item(0).getTextContent();
            String fromUserName = root.getElementsByTagName("FromUserName").item(0).getTextContent();
            String createTime = root.getElementsByTagName("CreateTime").item(0).getTextContent();
            String content = root.getElementsByTagName("Content").item(0).getTextContent();
            String msgId = root.getElementsByTagName("MsgId").item(0).getTextContent();
            logger.info("输入：" + content);
            String replyStr = "";
            if(content.equals("天气")){
                WeatherSearchDTO searchDTO = new WeatherSearchDTO();
                searchDTO.setCity("浦东新区");
                GlobalResultDTO<WeatherDTO> lastWeather = weatherService.getLastWeather(searchDTO);
                if(lastWeather.isSuccess()){
                    WeatherDTO thisDTO = lastWeather.getData();
                    String replyTemplate = "上海目前气温：%1$s度，pm25：%2$s, 天气：%3$s，湿度：%4$s，更新时间：%5$s";
                    replyStr = String.format(replyTemplate, thisDTO.getTemperature(), thisDTO.getPm25(), thisDTO.getWeather(), thisDTO.getHumidity(), thisDTO.getDataUpdateTime());
                }else{
                    replyStr = "天气获取失败，请稍后再试";
                }
            }
            String replyMsg =
                    "<xml>" +
                    "<ToUserName><![CDATA[%1$s]]></ToUserName>" +
                    "<FromUserName><![CDATA[%2$s]]></FromUserName>" +
                    "<CreateTime>" + createTime + "</CreateTime>" +
                    "<MsgType><![CDATA[text]]></MsgType>" +
                    "<Content><![CDATA[%3$s]]></Content>" +
                    "</xml>";
            //填充数据
            resultXml = String.format(replyMsg, fromUserName, toUserName, replyStr);
            String encryptMsg = wxBizMsgCrypt.encryptMsg(resultXml, timestamp, nonce);
            return encryptMsg;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AesException e) {
            e.printStackTrace();
        }
        return "";
    }
}
