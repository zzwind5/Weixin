package cn.wltc.biz.weixin;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wltc.biz.weixin.service.MessageHandleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/config/spring/**/*-beans.xml")
public class MessageHandleServiceTest {

	@Autowired
	public MessageHandleService messageHandleService;
	
	@Test
	@Ignore
	public void TestSendSingleUser(){
		messageHandleService.sendMsgToSingleFan("788071984","单元测试");
	}
}
