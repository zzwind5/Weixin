package cn.wltc.biz.wxmgr.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cn.wltc.biz.wxmgr.model.Activity;
import cn.wltc.biz.wxmgr.model.ActivityAuth;
import cn.wltc.common.dao.BaseIbatisDao;

@Repository("ActivityAuthDao_")
public class ActivityAuthDao extends BaseIbatisDao<ActivityAuth, Integer> {

	@Override
	public void saveOrUpdate(ActivityAuth entity) throws DataAccessException {
		if (entity.getActivity_auth_id() == null) {
			save(entity);
		} else {
			update(entity);
		}
	}

	@Override
	public Class<ActivityAuth> getEntityClass() {
		return ActivityAuth.class;
	}

	public int auth(Activity activity) {
		return getSqlMapClientTemplate().update("Activity.auth", activity);
	}
}
