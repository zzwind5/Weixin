/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package cn.wltc.biz.weixin.handler;

import cn.wltc.biz.weixin.model.bean.InMessage;
import cn.wltc.biz.weixin.model.bean.OutMessage;
import cn.wltc.biz.weixin.model.bean.TextOutMessage;

public abstract class DefaultMessageProcessingHandlerImpl implements MessageProcessingHandler{

	private OutMessage allType(InMessage msg){
		TextOutMessage out = new TextOutMessage();
		out.setContent("你的消息已经收到！");
		return out;
	}
	@Override
	public OutMessage textTypeMsg(InMessage msg) {
		return allType(msg);
	}

	@Override
	public OutMessage locationTypeMsg(InMessage msg) {
		return allType(msg);
	}

	@Override
	public OutMessage imageTypeMsg(InMessage msg) {
		return allType(msg);
	}

	@Override
	public OutMessage linkTypeMsg(InMessage msg) {
		return allType(msg);
	}

	@Override
	public OutMessage eventTypeMsg(InMessage msg) {
		return allType(msg);
	}

}
