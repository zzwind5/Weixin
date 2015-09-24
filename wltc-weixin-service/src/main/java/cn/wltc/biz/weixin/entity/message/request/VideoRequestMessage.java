package cn.wltc.biz.weixin.entity.message.request;

import cn.wltc.biz.weixin.entity.BaseMessage;

public class VideoRequestMessage extends BaseMessage {

	private String MediaId;
	
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	
}
