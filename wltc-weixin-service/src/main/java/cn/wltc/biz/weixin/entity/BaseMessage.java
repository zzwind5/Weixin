package cn.wltc.biz.weixin.entity;

public class BaseMessage {

	//开发者微信号
	private String ToUserName;
	//发送方帐号
	private String FromUserName;
	//消息创建时间
	private long CreateTime;
	//消息ID，64位整型
	private long MsgId;
	//消息类型(text/image/location/link/voice)
	private String MsgType;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public long getMsgId() {
		return MsgId;
	}
	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
}
