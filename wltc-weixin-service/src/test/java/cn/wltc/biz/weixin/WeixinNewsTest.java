package cn.wltc.biz.weixin;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wltc.biz.weixin.biz.service.WeixinNewsGroupService;
import cn.wltc.biz.weixin.util.MessageUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/config/spring/**/*-beans.xml")
public class WeixinNewsTest {

	@Autowired
	WeixinNewsGroupService weixinNewsGroupService;
	
	/***
     * 发送客服文本消息
     */
    @Test
    @Ignore
    public void createPublishedNewsMessage() {
        
//       String respXml = MessageUtil.messageToXml(weixinNewsGroupService.createPublishedNewsMessage("http://localhost", "dev", "o8jY7uC6X8PTK3p7ONztfm9F9h2Y", "0"));
//       System.out.println(respXml);
    }
}
