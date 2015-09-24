package cn.wltc.biz.weixin.biz.dao;

import org.springframework.stereotype.Repository;

import cn.wltc.biz.weixin.biz.bo.WeixinNews;
import cn.wltc.common.dao.BaseIbatisDao;
import cn.wltc.framework.service.BaseQuery;
import cn.wltc.framework.service.page.Page;

@Repository("WeixinNewsDao_")
public class WeixinNewsDao extends BaseIbatisDao<WeixinNews, java.lang.Integer> {

	public String getIbatisSqlMapNamespace() {
		return "WeixinNewsDao";
	}

	@Override
	public Class<WeixinNews> getEntityClass() {
		return WeixinNews.class;
	}

	public void saveOrUpdate(WeixinNews entity) {
		if (entity.getNews_id() >0 )
			save(entity);
		else
			update(entity);
	}

	public Page findPage(WeixinNews query) {
	    return pageQueryFix("WeixinNews.findPage",query);
	}
	
    @Override
    public Page<WeixinNews> findPageFix(BaseQuery query) {
        return pageQueryFix("WeixinNews.findPage",query);
    }
}
