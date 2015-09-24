package cn.wltc.biz.wxmgr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.wxmgr.dao.ActivityAuthDao;
import cn.wltc.biz.wxmgr.dao.ActivityDao;
import cn.wltc.biz.wxmgr.model.Activity;
import cn.wltc.biz.wxmgr.model.RoadLine;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.BaseQuery;
import cn.wltc.framework.service.EntityDao;
import cn.wltc.framework.service.page.Page;

@Service("ActivityService_")
public class ActivityService extends BaseService<Activity, Integer> {

	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private ActivityAuthDao authDao;
	
	private static final String SHZT_TG = "1";
	
	@Override
	protected EntityDao<Activity, Integer> getEntityDao() {
		return activityDao;
	}
	
    public Page<Activity> findPage(BaseQuery query) {
        return (Page<Activity>)getEntityDao().findPageFix(query);
    }
    
    public Page<Activity> findPageWeb(BaseQuery query) {
        return (Page<Activity>)activityDao.findPageWeb(query);
    } 
    public Activity getWebById(Integer id) {
        return (Activity)activityDao.getWebById(id);
    }      
    public Page<Activity> findPageWebzx(BaseQuery query) {
        return (Page<Activity>)activityDao.findPageWebzx(query);
    } 
    public Activity getWebzxById(Integer id) {
        return (Activity)activityDao.getWebzxById(id);
    }
	
    public List<RoadLine> findActivityList(BaseQuery query) {
		return activityDao.findActivityList(query);
    }
    
    public boolean auth(Activity activity){
    	
    	if (SHZT_TG.equals(activity.getShzt())){
    		activity.setStatus(SHZT_TG);
    		activityDao.update(activity);
    	}
    	
    	return authDao.auth(activity) > 0;
    }
    
    public Page<Activity> getAuthList(BaseQuery query) {
    	
    	return activityDao.getAuthList(query);
    }
    
}
