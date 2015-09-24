package cn.wltc.biz.weixin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wltc.biz.customerMgr.service.CustomerService;
import cn.wltc.biz.weixin.model.message.resp.CustomArticle;
import cn.wltc.biz.weixin.model.message.resp.CustomNewsMessage;
import cn.wltc.biz.weixin.service.CustomMessageService;
import cn.wltc.biz.weixin.util.MessageUtil;

import com.alibaba.fastjson.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/config/spring/**/*-beans.xml")
public class CustomMessageTest {

	@Autowired
	CustomMessageService customMessageService;
	
    
    @Autowired
    private CustomerService customerService;
    
	/***
	 * 发送客服文本消息
	 */
	@Test
	public void sendTextTest() {
		customMessageService.sendText("o3r6QjvD9eGB073nSajH51s0j6tU", "56tc测试客服消息接口");
	}

	/**
	 * 发送图文客服消息
	 */
	@Ignore
	@Test
	public void sendImgMessageTest() {
		Map<String,List<CustomArticle>> news = new HashMap<String, List<CustomArticle>>();
		List<CustomArticle> articleList = new ArrayList<CustomArticle>();
		// 创建图文消息
		String toUserName = "o3r6QjvD9eGB073nSajH51s0j6tU";
		CustomNewsMessage newsMessage = new CustomNewsMessage();
		newsMessage.setMsgtype(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setTouser(toUserName);
		
		CustomArticle article = new CustomArticle();
		article.setTitle("test");
		article.setDescription("test");
		article.setPicUrl("http://mp.weixin.qq.com/wiki/skins/common/images/weixin_wiki_logo.png");
		article.setUrl("http://blog.csdn.net/");
		articleList.add(article);
		news.put("articles", articleList);
		// 设置图文消息包含的图文集合
		newsMessage.setNews(news);
		// 将图文消息对象转换成xml字符串
		String respMessage = JSONObject.toJSONString(newsMessage);
		System.out.println("respMessage:"+respMessage);
//		String msg = "{\"touser\":\"oo-gut9g1q5NWFD_AzJQ4Dd2svt0\",\"msgtype\":\"news\",\"news\":{\"articles\":[{\"description\":\"test\",\"picurl\":\"http://mp.weixin.qq.com/wiki/skins/common/images/weixin_wiki_logo.png\",\"title\":\"test\",\"url\":\"http://blog.csdn.net\"}]}}";
//		System.out.println("msg:"+msg);
		String result = customMessageService.sendImgMessage(respMessage);
		System.out.println("result:"+result);
	}
	
	
	   /***
     * 发送客服文本消息
     */
    @Test
    public void subscribe() {
        customerService.subscribe("o3r6QjvD9eGB073nSajH51s0j6tU");
    }
}
