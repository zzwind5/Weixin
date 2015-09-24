package cn.wltc.biz.weixin.entity.message.response;

import cn.wltc.biz.weixin.entity.BaseMessage;

public class TextResonseMessage extends BaseMessage {
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
