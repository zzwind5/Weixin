package cn.wltc.biz.weixin.entity.message.response;

import java.util.List;

import cn.wltc.biz.weixin.entity.BaseMessage;

public class NewsResonseMessage extends BaseMessage {

	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<Article> Articles;

	private String MsgType = "news";
	
	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
	
	
}
