package cn.wltc.biz.weixin.entity.message.response;

import cn.wltc.biz.weixin.entity.BaseMessage;

public class MusicResponseMessage extends BaseMessage {

	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
