package cn.wltc.biz.adcd.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cn.wltc.biz.adcd.model.BaseAdcd;
import cn.wltc.common.dao.BaseIbatisDao;
@Repository("BaseAdcdDao_")
public class BaseAdcdDao extends BaseIbatisDao<BaseAdcd, java.lang.Integer>{

	public String getIbatisSqlMapNamespace() {
		return "BaseAdcd";
	}
	@Override
	public Class<BaseAdcd> getEntityClass() {
		return BaseAdcd.class;
	}
    @Override
    public void saveOrUpdate(BaseAdcd entity) throws DataAccessException {
        // TODO Auto-generated method stub
        
    }
	

	
}
