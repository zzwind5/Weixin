package cn.wltc.biz.weixin.entity.message.request;

import cn.wltc.biz.weixin.entity.BaseMessage;

public class TextRequestMessage extends BaseMessage{

	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	
}
