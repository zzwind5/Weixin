package cn.wltc.biz.weixin.entity.message.request;

import cn.wltc.biz.weixin.entity.BaseMessage;

public class LinkRequestMessage extends BaseMessage {

	private String Title;
	
	private String Description;
	
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}
}
