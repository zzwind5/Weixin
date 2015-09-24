package cn.wltc.biz.newsmgr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.newsmgr.dao.NewsInfoDao;
import cn.wltc.biz.newsmgr.model.NewsInfo;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.EntityDao;
import cn.wltc.framework.service.page.Page;

@Service("NewsInfoService_")
public class NewsInfoService extends BaseService<NewsInfo, java.lang.Integer>{

	 @Autowired
	 private NewsInfoDao newsInfoDao;

	    @Override
	    protected EntityDao getEntityDao() {
	        return newsInfoDao;
	    }

	    public int deleteNews(NewsInfo model) {
	    	return newsInfoDao.deleteNews(model);
	    }
	    public NewsInfo getGroupById(NewsInfo model) {
	    	return newsInfoDao.getGroupById(model);
	    }
	    
	    public NewsInfo getNewsById(NewsInfo model) {
	    	return newsInfoDao.getNewsById(model);
	    	
	    }
	    
	    
	    public void insertNews(NewsInfo model) {
	    	if (model.getNews_id() != null) {
	    		updateNews(model);
	    	}
	    	else {
	    		Object primaryKey= newsInfoDao.insertNews(model);
	    		model.setNews_id((Integer)primaryKey);
	    		
	    		NewsInfo obj = getGroupById(model);
		    	NewsInfo record = new NewsInfo();
		    	if (obj.getNews_ids() != null && obj.getNews_ids() != "") {
		    		record.setNews_ids(obj.getNews_ids()+"|"+ model.getNews_id());
		    	} else {
		    		record.setNews_ids(String.valueOf(model.getNews_id()));
		    	}
		    	record.setStatus("0");
		    	//record.setUpdate_time(new Date());
		    	record.setGroup_id(model.getGroup_id());
		    	updateNewsgrp(record);
	    	}
	    }
	    
	    public int updateNews(NewsInfo model) {
	    	return newsInfoDao.updateNews(model);
	    }
	    
	    public int updateNewsgrp(NewsInfo model) {
	    	return newsInfoDao.updateNewsgrp(model);
	    }
	    public List<NewsInfo> getNews(String news_id) {
	    	String[] array = news_id.split("\\|");
	    	String sql = "";
	    	for (String s : array) {
	    		if (s != null && s !="") {
	    			sql +="'";
	    			sql += s;
	    			sql += "',";
	    		}
	    	}
	    	sql = sql != "" ? sql.substring(0, sql.length()-1) : sql;
	    	return newsInfoDao.getNews(sql);
	    }
	    
	    public List<NewsInfo> getNews(List<NewsInfo> list, String news_ids) {
	    	List<NewsInfo> result = new ArrayList<NewsInfo>();
	    	
	    	if (list != null && list.size() > 0 && news_ids != null && news_ids != "")  {
	    			String[] array = news_ids.split("\\|");
	    			for (String s : array) {
	    				if (s != null && s != "" && s != "0") {
	    					for (NewsInfo obj : list) {
	    						if (s.equalsIgnoreCase(String.valueOf(obj.getNews_id()))) {
	    							result.add(obj);
	    						}
	    					}
	    				}
	    			}
	    	}
	    	
	    	if (result != null && result.size() > 0) {
	    		int size = result.size();
	    		for (int i=0;i<8-size;i++) {
	    			NewsInfo info = new NewsInfo();
	    			result.add(info);
	    		}
	    	} else {
	    		for (int i=0;i<8;i++) {
	    			NewsInfo info = new NewsInfo();
	    			result.add(info);
	    		}
	    	}
	    	
	    	return result;
	    }
	     
	    public int updateNewsgrpAll(NewsInfo model) {
	    	return newsInfoDao.updateNewsgrpAll(model);
	    }
	    
	    public Map getSecEnum(String section) {
	    	Map<String, String> secEnum = new LinkedMap();
	    	if ("0".equalsIgnoreCase(section)) {
	    		secEnum.put("0", "物流新闻");
	    	} else {
	    		secEnum.put("1", "优惠新闻");
	    	}
	    	return secEnum;
	    }
	    
	    public List<NewsInfo> getNewsOrder(List<NewsInfo> list, String news_ids) {
	    	List<NewsInfo> result = new ArrayList<NewsInfo>();
	    	
	    	if (list != null && list.size() > 0 && news_ids != null && news_ids != "")  {
	    			String[] array = news_ids.split("\\|");
	    			for (String s : array) {
	    				if (s != null && s != "" && s != "0") {
	    					for (NewsInfo obj : list) {
	    						if (s.equalsIgnoreCase(String.valueOf(obj.getNews_id()))) {
	    							result.add(obj);
	    						}
	    					}
	    				}
	    			}
	    	}
	    	return result;
	    }
}
