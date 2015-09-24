package cn.wltc.biz.wxmgr.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cn.wltc.biz.wxmgr.model.Orgzanation;
import cn.wltc.common.dao.BaseIbatisDao;

@Repository("OrgzanationDao_")
@SuppressWarnings("unchecked")
public class OrgzanationDao extends BaseIbatisDao<Orgzanation, Integer> {

	@Override
	public void saveOrUpdate(Orgzanation entity) throws DataAccessException {
		if (entity.getId() == null)
			save(entity);
		else
			update(entity);
	}

	@Override
	public Class<Orgzanation> getEntityClass() {
		return Orgzanation.class;
	}
    
    public Orgzanation findByName(String param){    	
    	return (Orgzanation) getSqlMapClientTemplate().queryForObject("Orgzanation.findByName", param);
    }

}
