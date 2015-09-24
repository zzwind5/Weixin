package cn.wltc.biz.weixin.model.message.resp;

import java.util.List;
import java.util.Map;

public class CustomNewsMessage {
	private String touser;// 普通用户openid
	// 消息类型（text/music/news）
	private String msgtype;
	// 多条图文消息信息，默认第一个item为大图
	private Map<String,List<CustomArticle>> news;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public Map<String, List<CustomArticle>> getNews() {
		return news;
	}

	public void setNews(Map<String, List<CustomArticle>> news) {
		this.news = news;
	}

	
}
