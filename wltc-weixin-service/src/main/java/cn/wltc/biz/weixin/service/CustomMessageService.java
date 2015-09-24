package cn.wltc.biz.weixin.service;

import org.springframework.stereotype.Repository;

import cn.wltc.biz.weixin.manager.WeChatManager;
import cn.wltc.biz.weixin.util.HttpKit;

/**
 * 客服消息接口
 */
@Repository("CustomMessageService_")
public class CustomMessageService {

    private static final String MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

    /**
     * 发送文本客服消息
     * @param openId
     * @param text
     */
    public  String sendText(String openId, String text) {
        String accessToken = WeChatManager.getAccessToken();
        String reslut = HttpKit.post(MESSAGE_URL.concat(accessToken), "{\"touser\":\"" + openId + "\",\"msgtype\":\"text\",\"text\":{\"content\":\"" + text + "\"}}");
        return reslut;
    }
    
    /**
     * 发送图文客服消息
     * @param openId
     * @param text
     */
    public String sendImgMessage(String imgMsg) {
        String accessToken = WeChatManager.getAccessToken();
        String reslut = HttpKit.post(MESSAGE_URL.concat(accessToken), imgMsg);
        return reslut;
    }
}
