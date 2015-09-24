package cn.wltc.biz.wxmgr.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cn.wltc.biz.wxmgr.model.UserInfo;
import cn.wltc.common.dao.BaseIbatisDao;

@Repository("UserInfoDao_")
public class UserInfoDao extends BaseIbatisDao<UserInfo, Integer> {

	@Override
	public void saveOrUpdate(UserInfo entity) throws DataAccessException {
		if (entity.getUid() == null) {
			save(entity);
		} else {
			update(entity);
		}
	}

	@Override
	public Class<UserInfo> getEntityClass() {
		return UserInfo.class;
	}

}
