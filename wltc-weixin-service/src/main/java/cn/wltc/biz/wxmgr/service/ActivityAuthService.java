package cn.wltc.biz.wxmgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.wxmgr.dao.ActivityAuthDao;
import cn.wltc.biz.wxmgr.model.ActivityAuth;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.EntityDao;

@Service("ActivityAuthService_")
public class ActivityAuthService extends BaseService<ActivityAuth, Integer> {

	@Autowired
	private ActivityAuthDao activityAuthDao;

	@Override
	protected EntityDao<ActivityAuth, Integer> getEntityDao() {
		return activityAuthDao;
	}

}
