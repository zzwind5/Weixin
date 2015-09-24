package cn.wltc.biz.weixin.biz.dao;

import org.springframework.stereotype.Repository;

import cn.wltc.biz.weixin.biz.bo.WeixinNewsGroup;
import cn.wltc.common.dao.BaseIbatisDao;
import cn.wltc.framework.service.BaseQuery;
import cn.wltc.framework.service.page.Page;

@Repository("WeixinNewsGroupDao_")
public class WeixinNewsGroupDao extends BaseIbatisDao<WeixinNewsGroup, java.lang.Integer> {

	public String getIbatisSqlMapNamespace() {
		return "WeixinNewsGroupDao";
	}

	@Override
	public Class<WeixinNewsGroup> getEntityClass() {
		return WeixinNewsGroup.class;
	}

	public void saveOrUpdate(WeixinNewsGroup entity) {
		if (entity.getGroup_id() >0 )
			save(entity);
		else
			update(entity);
	}

	public Page findPage(WeixinNewsGroup query) {
	    return pageQueryFix("WeixinNewsGroup.findPage",query);
	}
	
    @Override
    public Page<WeixinNewsGroup> findPageFix(BaseQuery query) {
        return pageQueryFix("WeixinNewsGroup.findPage",query);
    }
}
