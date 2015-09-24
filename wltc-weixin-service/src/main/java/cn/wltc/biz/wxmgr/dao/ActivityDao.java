package cn.wltc.biz.wxmgr.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cn.wltc.biz.wxmgr.model.Activity;
import cn.wltc.biz.wxmgr.model.RoadLine;
import cn.wltc.common.dao.BaseIbatisDao;
import cn.wltc.framework.service.BaseQuery;
import cn.wltc.framework.service.page.Page;

@SuppressWarnings("unchecked")
@Repository("ActivityDao_")
public class ActivityDao extends BaseIbatisDao<Activity, Integer> {

	@Override
	public void saveOrUpdate(Activity entity) throws DataAccessException {
		if (entity.getId() == null)
			save(entity);
		else
			update(entity);
		
	}

	@Override
	public Class<Activity> getEntityClass() {
		return Activity.class;
	}
	
    @Override
    public Page<Activity> findPageFix(BaseQuery query) {
        return pageQueryFix("Activity.findPage",query);
    }
    
    public Page<Activity> findPageWeb(BaseQuery query) {
        return pageQueryFix("ActivityWeb.findPage",query);
    }
    public Activity getWebById(Integer id) {
        return (Activity)getSqlMapClientTemplate().queryForObject("ActivityWeb.getById",id);
    }   
    
    public Page<Activity> findPageWebzx(BaseQuery query) {
        return pageQueryFix("ActivityWebzx.findPage",query);
    }
    
    public Page<Activity> getAuthList(BaseQuery query) {
    	return pageQueryFix("Activity.getAuthList",query);
    }
    
    public Activity getWebzxById(Integer id) {
        return (Activity)getSqlMapClientTemplate().queryForObject("ActivityWebzx.getById",id);
    }   
	
    public List<RoadLine> findActivityList(BaseQuery query) {
		return ((List<RoadLine>) getSqlMapClientTemplate().queryForList(
				"ActivityWebzx.find", query));
    }
    
    

}
