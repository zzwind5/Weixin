package cn.wltc.biz.weixin.entity.message.request;

import cn.wltc.biz.weixin.entity.BaseMessage;

public class ImageRequestMessage extends BaseMessage {

	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	
	
}
