package cn.wltc.biz.base.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cn.wltc.biz.base.model.UserFile;
import cn.wltc.common.dao.BaseIbatisDao;

@SuppressWarnings("unchecked")
@Repository("UserFileDao_")
public class UserFileDao extends BaseIbatisDao<UserFile, java.lang.Integer>{

	@Override
	public void saveOrUpdate(UserFile entity) throws DataAccessException {
	}

	@Override
	public Class<UserFile> getEntityClass() {
		return UserFile.class;
	}

}
