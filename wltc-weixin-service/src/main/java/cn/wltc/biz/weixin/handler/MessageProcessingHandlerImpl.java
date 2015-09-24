/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package cn.wltc.biz.weixin.handler;

import cn.wltc.biz.weixin.biz.ScratchCard;
import cn.wltc.biz.weixin.model.bean.InMessage;
import cn.wltc.biz.weixin.model.bean.OutMessage;
import cn.wltc.biz.weixin.model.bean.TextOutMessage;
import cn.wltc.biz.weixin.util.MessageUtil;

/**
 * 自定义实现消息处理
 * @author GodSon
 *
 */
public class MessageProcessingHandlerImpl implements MessageProcessingHandler {

	@Override
	public OutMessage textTypeMsg(InMessage msg) {
		TextOutMessage oms = new TextOutMessage();
		String content = msg.getContent();	//文本消息内容
		StringBuffer buffer = new StringBuffer(); 
		if(content.equals(MessageUtil.MESSAGE_CORD_SCRATCH_CARD)){
			ScratchCard scratchCard = new ScratchCard();
			oms.setContent(scratchCard.showMsgContent());
			return oms;
		}else{
			buffer.append("您好，我是智能机器人，请回复数字选择服务：").append("\n\n");
			buffer.append("101 参加 刮刮乐活动").append("\n");
		}
		oms.setContent(buffer.toString());
		return oms;
	}

	@Override
	public OutMessage locationTypeMsg(InMessage msg) {
		TextOutMessage oms = new TextOutMessage();
		oms.setContent("getLocationX:"+msg.getLocationX());
		return oms;
	}

	@Override
	public OutMessage imageTypeMsg(InMessage msg) {
		TextOutMessage oms = new TextOutMessage();
		oms.setContent("getPicUrl:"+msg.getPicUrl());
		return oms;
	}

	@Override
	public OutMessage linkTypeMsg(InMessage msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutMessage eventTypeMsg(InMessage msg) {
		TextOutMessage oms = new TextOutMessage();
		String event = msg.getEvent();
		StringBuffer buffer = new StringBuffer(); 
		if(event.equals("subscribe")){	//推荐
			buffer.append("您好，我是智能机器人，请回复数字选择服务：").append("\n\n");
			buffer.append("101 参加 刮刮乐活动").append("\n");
		}else{
			buffer.append("智能机器人回复：事件("+event+")已经收到！");
		}
		oms.setContent(buffer.toString());
		return oms;
	}

	@Override
	public OutMessage voiceTypeMsg(InMessage msg) {
		TextOutMessage oms = new TextOutMessage();
		StringBuffer buffer = new StringBuffer(); 
		buffer.append("智能机器人回复：音频("+msg.getMediaId()+")已经收到！");
		return oms;
	}

}
