package cn.wltc.biz.weixin.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.customerMgr.service.CustomerService;
import cn.wltc.biz.weixin.biz.service.WeixinNewsGroupService;
import cn.wltc.biz.weixin.entity.message.response.NewsResonseMessage;
import cn.wltc.biz.weixin.entity.message.response.TextResonseMessage;
import cn.wltc.biz.weixin.util.MessageUtil;

@Service("CoreService_")
public class CoreService {
	
	@Autowired
	private ArticalService articalService;
	
	@Autowired
	private CustomerService customerService;
	
    @Autowired
    private WeixinNewsGroupService weixinNewsGroupService;	
	
    public static final String NOTE_MSG = "客服系统正在建设中，暂时无法处理您的请求！";
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public  String processRequest(HttpServletRequest request) {
	    String serverName = request.getServerName();
	    String context = request.getContextPath();
	    String serverHost = "http://" + serverName + context;
//	    String imageHost = "http://w.mgr.56tc.cn";
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "未知的消息类型！";
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");			
			
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextResonseMessage textMessage = new TextResonseMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);	

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = NOTE_MSG;
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = NOTE_MSG;
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = NOTE_MSG;
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = NOTE_MSG;
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = NOTE_MSG;
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = NOTE_MSG;
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				if(eventType.equals( MessageUtil.EVENT_TYPE_SUBSCRIBE)){
				    customerService.subscribe(fromUserName);
					respContent = "谢谢您的关注，56同城将为您提供最优质的服务！";
//				    NewsResonseMessage message =  articalService.createMultiNewsMessage(fromUserName, toUserName);
//				    respXml = MessageUtil.messageToXml(message);
				    //TODO 添加首次关注时的提示菜单文章
//				    return respContent;
				}else if(eventType.equals( MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
				    customerService.unSubscribe(fromUserName);
				}else if(eventType.equals( MessageUtil.EVENT_TYPE_SCAN)){
					
				}else if(eventType.equals( MessageUtil.EVENT_TYPE_LOCATION)){
					
				}else if(eventType.equals( MessageUtil.EVENT_TYPE_CLICK)){
					String eventKey = requestMap.get("EventKey");  
					if(eventKey.equals("promActNews")){//优惠新闻
                        respXml = MessageUtil.messageToXml(weixinNewsGroupService.createPublishedNewsMessage(serverHost, fromUserName, toUserName, "1"));
	                    return respXml;
					}else if(eventKey.equals("logisticnews")){//物流新闻						
	                    respXml = MessageUtil.messageToXml(weixinNewsGroupService.createPublishedNewsMessage(serverHost, fromUserName, toUserName, "0"));
//					   respXml = MessageUtil.messageToXml(articalService.createLogisticMultiNewsMessage(fromUserName, toUserName));
	                    return respXml;
					}
				}
			}
			// 设置文本消息的内容
			textMessage.setContent(respContent);
			// 将文本消息对象转换成xml
			respXml = MessageUtil.messageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
