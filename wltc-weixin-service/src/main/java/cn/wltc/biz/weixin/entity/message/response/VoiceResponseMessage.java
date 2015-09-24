package cn.wltc.biz.weixin.entity.message.response;

import cn.wltc.biz.weixin.entity.BaseMessage;

public class VoiceResponseMessage extends BaseMessage {
	
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
