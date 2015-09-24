package cn.wltc.biz.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.wltc.biz.weixin.entity.message.response.Article;
import cn.wltc.biz.weixin.entity.message.response.NewsResonseMessage;
import cn.wltc.biz.weixin.util.MessageUtil;

@Service("ArticalService_")
public class ArticalService {

	public NewsResonseMessage createSingleNewsMessage(String fromUserName, String toUserName){
        NewsResonseMessage newsMessage = new NewsResonseMessage();  
        newsMessage.setToUserName(fromUserName);  
        newsMessage.setFromUserName(toUserName);  
        newsMessage.setCreateTime(new Date().getTime());  
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();  
        article.setTitle("郑州发快递到纽约未来可在一周内寄达");  
        article.setDescription("《计划》明确，力争到2016年，全省物流业增加值达到2400亿元。未来我省将力争新开通至迪拜、东京、悉尼、卢森堡等国际地区客货运航线20条以上，力争到2016年，郑州新郑国际机场进入全国机场前10位。");  
        article.setPicUrl("http://www.vooec.com/member/Pic/20113/201131740989020.jpg");  
        article.setUrl("http://news.163.com/14/0726/01/A21SBETL00014Q4P.html");  
        articleList.add(article);  
        newsMessage.setArticleCount(articleList.size());  
        newsMessage.setArticles(articleList);  
        return newsMessage;
	}
	
	public NewsResonseMessage createMultiNewsMessage(String fromUserName, String toUserName){
        NewsResonseMessage newsMessage = new NewsResonseMessage();  
        newsMessage.setToUserName(fromUserName);  
        newsMessage.setFromUserName(toUserName);  
        newsMessage.setCreateTime(new Date().getTime());  
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
        List<Article> articleList = new ArrayList<Article>();
        Article mainArticle = new Article();  
        mainArticle.setTitle("郑州发快递到纽约未来可在一周内寄达");  
        mainArticle.setDescription("《计划》明确，力争到2016年，全省物流业增加值达到2400亿元。未来我省将力争新开通至迪拜、东京、悉尼、卢森堡等国际地区客货运航线20条以上，力争到2016年，郑州新郑国际机场进入全国机场前10位。");  
        mainArticle.setPicUrl("http://www.vooec.com/member/Pic/20113/201131740989020.jpg");  
        mainArticle.setUrl("http://news.163.com/14/0726/01/A21SBETL00014Q4P.html");  
        articleList.add(mainArticle);  
        
        Article article_1 = new Article();  
        article_1.setTitle("郑州发快递到纽约未来可在一周内寄达-SUB-1");     
        article_1.setUrl("http://news.163.com/14/0726/01/A21SBETL00014Q4P.html");  
        articleList.add(article_1);  
        
        Article article_2 = new Article();  
        article_2.setTitle("郑州发快递到纽约未来可在一周内寄达-SUB-2");     
        article_2.setUrl("http://news.163.com/14/0726/01/A21SBETL00014Q4P.html");  
        articleList.add(article_2);  
        
        Article article_3 = new Article();  
        article_3.setTitle("郑州发快递到纽约未来可在一周内寄达-SUB-3");     
        article_3.setUrl("http://news.163.com/14/0726/01/A21SBETL00014Q4P.html");  
        articleList.add(article_3);  
        
        newsMessage.setArticleCount(articleList.size());  
        newsMessage.setArticles(articleList);  
        return newsMessage;
	}
    public NewsResonseMessage createLogisticsNewsMessage(String fromUserName, String toUserName){
        NewsResonseMessage newsMessage = new NewsResonseMessage();  
        newsMessage.setToUserName(fromUserName);  
        newsMessage.setFromUserName(toUserName);  
        newsMessage.setCreateTime(new Date().getTime());  
        List<Article> articleList = new ArrayList<Article>();
        Article mainArticle = new Article();  
        
        
        mainArticle.setTitle("郑州发快递到纽约未来可在一周内寄达");  
        mainArticle.setDescription("《计划》明确，力争到2016年，全省物流业增加值达到2400亿元。未来我省将力争新开通至迪拜、东京、悉尼、卢森堡等国际地区客货运航线20条以上，力争到2016年，郑州新郑国际机场进入全国机场前10位。");  
        mainArticle.setPicUrl("http://www.vooec.com/member/Pic/20113/201131740989020.jpg");  
        mainArticle.setUrl("http://news.163.com/14/0726/01/A21SBETL00014Q4P.html");  
        articleList.add(mainArticle);  

        newsMessage.setArticleCount(articleList.size());  
        newsMessage.setArticles(articleList);  
        return newsMessage;
    }	
	
    
	public NewsResonseMessage createLogisticMultiNewsMessage(String fromUserName, String toUserName){
        NewsResonseMessage newsMessage = new NewsResonseMessage();  
        newsMessage.setToUserName(fromUserName);  
        newsMessage.setFromUserName(toUserName);  
        newsMessage.setCreateTime(new Date().getTime());  
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
        List<Article> articleList = new ArrayList<Article>();
        Article mainArticle = new Article();  
        mainArticle.setTitle("电商物流：自建还是第三方?");  
        mainArticle.setDescription("物流是电商企业绕不过去的“坎”，目前电商在物流配送环节中，存在第三方和自建两种模式。");  
        mainArticle.setPicUrl("http://www.vooec.com/member/Pic/20113/201131740989020.jpg");  
        mainArticle.setUrl("http://w.56tc.cn/news/detail/1.htm");  
        articleList.add(mainArticle);  
        
        Article article_1 = new Article();  
        article_1.setTitle("打造面向产业链的物流服务平台");     
        article_1.setUrl("http://w.56tc.cn/news/detail/2.htm");  
        articleList.add(article_1);  
        
        newsMessage.setArticleCount(articleList.size());  
        newsMessage.setArticles(articleList);  
        return newsMessage;
	}
}
