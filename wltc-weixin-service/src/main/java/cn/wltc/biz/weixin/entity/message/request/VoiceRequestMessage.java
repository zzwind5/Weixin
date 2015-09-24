package cn.wltc.biz.weixin.entity.message.request;

import cn.wltc.biz.weixin.entity.BaseMessage;

public class VoiceRequestMessage extends BaseMessage {

	private String  MediaId;
	
	private String Format;
	
	private String Recognition;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
}
