package cn.wltc.biz.wxmgr.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cn.wltc.biz.wxmgr.model.CollectedLine;
import cn.wltc.common.dao.BaseIbatisDao;

@SuppressWarnings("unchecked")
@Repository("CollectedLineDao_")
public class CollectedLineDao extends BaseIbatisDao<CollectedLine, CollectedLine> {

	@Override
	public void saveOrUpdate(CollectedLine entity) throws DataAccessException {
		if (entity.getCustomerId() == null)
			save(entity);
		else
			update(entity);
		
	}

	@Override
	public Class<CollectedLine> getEntityClass() {
		return CollectedLine.class;
	}


}
