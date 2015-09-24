package cn.wltc.biz.newsmgr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wltc.biz.newsmgr.model.NewsInfo;
import cn.wltc.common.dao.BaseIbatisDao;
import cn.wltc.framework.service.BaseQuery;
import cn.wltc.framework.service.page.Page;

@Repository("NewsInfoDao_")
public class NewsInfoDao extends BaseIbatisDao<NewsInfo, java.lang.Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getIbatisSqlMapNamespace() {
		return "NewsInfo";
	}

	@Override
	public Class<NewsInfo> getEntityClass() {
		return NewsInfo.class;
	}

	public void saveOrUpdate(NewsInfo entity) {
		if (entity == null )
			save(entity);
		else
			update(entity);
	}

	public Page findPage(NewsInfo query) {
		return pageQuery("NewsInfo.findPage", query);
	}
	public int deleteNews(NewsInfo model) {
        return ((Integer)getSqlMapClientTemplate().delete(getEntityClass().getSimpleName() + ".deleteNews", model)).intValue();
    }
	
	public NewsInfo getGroupById(NewsInfo model) {
    	return (NewsInfo) getSqlMapClientTemplate().queryForObject(getEntityClass().getSimpleName() + ".getGroupById", model);
    }
	
	public NewsInfo getNewsById(NewsInfo model) {
    	return (NewsInfo) getSqlMapClientTemplate().queryForObject(getEntityClass().getSimpleName() + ".getNewsById", model);
    }
	
	public Object insertNews(NewsInfo model) {
		Object primaryKey = getSqlMapClientTemplate().insert(getEntityClass().getSimpleName() + ".insertNews", model);
		return primaryKey;
    }
	
	public int updateNewsgrp(NewsInfo model) {
        return ((Integer)getSqlMapClientTemplate().update(getEntityClass().getSimpleName() + ".updateNewsgrp", model));
    }
	
	public int updateNews(NewsInfo model) {
        return ((Integer)getSqlMapClientTemplate().update(getEntityClass().getSimpleName() + ".updateNews", model));
    }
	
	public List<NewsInfo> getNews(String news_id) {
  		return getSqlMapClientTemplate().queryForList(getEntityClass().getSimpleName() + ".getNews", news_id);
  	}
	
	public int updateNewsgrpAll(NewsInfo model) {
        return ((Integer)getSqlMapClientTemplate().update(getEntityClass().getSimpleName() + ".updateNewsgrpAll", model));
    }
}
