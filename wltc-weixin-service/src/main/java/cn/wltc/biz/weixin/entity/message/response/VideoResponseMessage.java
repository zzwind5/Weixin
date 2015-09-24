package cn.wltc.biz.weixin.entity.message.response;

import cn.wltc.biz.weixin.entity.BaseMessage;

public class VideoResponseMessage extends BaseMessage {

	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
