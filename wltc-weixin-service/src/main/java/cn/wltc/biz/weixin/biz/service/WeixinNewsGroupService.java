package cn.wltc.biz.weixin.biz.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.weixin.biz.bo.WeixinNews;
import cn.wltc.biz.weixin.biz.bo.WeixinNewsGroup;
import cn.wltc.biz.weixin.biz.dao.WeixinNewsGroupDao;
import cn.wltc.biz.weixin.entity.message.response.Article;
import cn.wltc.biz.weixin.entity.message.response.NewsResonseMessage;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.EntityDao;

@Service("WeixinNewsGroupService_")
public class WeixinNewsGroupService extends BaseService<WeixinNewsGroup, java.lang.Integer> {

    @Autowired
    private WeixinNewsGroupDao weixinNewsGroupDao;

    @Autowired
    private WeixinNewsService      weixinNewsService;

    @Override
    protected EntityDao getEntityDao() {
        return weixinNewsGroupDao;
    }

    public List<WeixinNews> getNewsInPublishedGroup(String section) {

        WeixinNewsGroup group = new WeixinNewsGroup();
        group.setStatus("1");
        group.setSection(section);
        List<WeixinNewsGroup> newsgroups = weixinNewsGroupDao.find(group);
        WeixinNewsGroup newsgroup = null;
        if (newsgroups == null || newsgroups.size() <= 0) {
            return null;
        }

        newsgroup = newsgroups.get(0);

        String[] ids = newsgroup.getNews_ids().split("\\|");
        Integer[] news_ids = new Integer[ids.length];
        for (int i = 0; i < ids.length; i++) {
            news_ids[i] = Integer.valueOf(ids[i]);
        }

        WeixinNews weixinNewsQuery = new WeixinNews();
        weixinNewsQuery.setNews_ids(news_ids);
        List<WeixinNews> weixinNewslist = weixinNewsService.find(weixinNewsQuery);

        List<WeixinNews> sortedWeixinNewslist = new ArrayList<WeixinNews>();
        for(Integer temp_id:news_ids){ //排序
            for(WeixinNews news : weixinNewslist){
                if (news.getNews_id().intValue() == temp_id.intValue()){
                    sortedWeixinNewslist.add(news);
                }
            }
        }
        
        return sortedWeixinNewslist;

    }
    
    
    public NewsResonseMessage createPublishedNewsMessage(String serverHost, String fromUserName, String toUserName,String section){
      
        List<WeixinNews> weixinNewslist =  getNewsInPublishedGroup(section);
        if (weixinNewslist == null){
            return null;
        }
        
        NewsResonseMessage newsMessage = new NewsResonseMessage();  
        newsMessage.setToUserName(fromUserName);  
        newsMessage.setFromUserName(toUserName);  
        newsMessage.setCreateTime(new Date().getTime());  
        List<Article> articleList = new ArrayList<Article>();
          
        for (WeixinNews weixinNews : weixinNewslist) {
            if ("1".equals(weixinNews.getIs_main_news())) {
                Article mainArticle = new Article();
                mainArticle.setTitle(weixinNews.getTitle());  
                mainArticle.setDescription(weixinNews.getDescription());  
                if (weixinNews.getImage_url() != null && weixinNews.getImage_url().startsWith("http://")){
                    mainArticle.setPicUrl(weixinNews.getImage_url()); 
                } else {
                    mainArticle.setPicUrl(serverHost + weixinNews.getImage_url()); 
                }
                if (weixinNews.getNews_url() != null && weixinNews.getNews_url().startsWith("http://")){
                    mainArticle.setUrl(weixinNews.getNews_url());  
                } else {
                    mainArticle.setUrl(serverHost + "/news/detail/" + weixinNews.getNews_id() + ".htm");  
                }                 
                articleList.add(mainArticle); 
                System.out.print(mainArticle.getPicUrl());
                break;
            }
        }
        
        for (WeixinNews weixinNews : weixinNewslist) {
            if (!"1".equals(weixinNews.getIs_main_news())) {
                Article article = new Article(); 
                article.setTitle(weixinNews.getTitle());  
                article.setDescription(weixinNews.getDescription());  
                if (weixinNews.getImage_url() != null && weixinNews.getImage_url().startsWith("http://")){
                    article.setPicUrl(weixinNews.getImage_url()); 
                } else {
                    article.setPicUrl(serverHost + weixinNews.getImage_url()); 
                }
                if (weixinNews.getNews_url() != null && weixinNews.getNews_url().startsWith("http://")){
                    article.setUrl(weixinNews.getNews_url());  
                } else {
                    //article.setUrl(serverHost + weixinNews.getNews_url()); 
                    article.setUrl(serverHost + "/news/detail/" + weixinNews.getNews_id() + ".htm");  
                } 
                articleList.add(article); 
            }
        }
        
        newsMessage.setArticleCount(articleList.size());  
        newsMessage.setArticles(articleList);  
        return newsMessage;
    }

}
