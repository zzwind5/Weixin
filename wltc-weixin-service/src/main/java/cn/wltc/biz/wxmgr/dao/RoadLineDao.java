package cn.wltc.biz.wxmgr.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cn.wltc.biz.wxmgr.model.RoadLine;
import cn.wltc.common.dao.BaseIbatisDao;
import cn.wltc.framework.service.BaseQuery;
import cn.wltc.framework.service.page.Page;

@Repository("RoadLineDao_")
@SuppressWarnings("unchecked")
public class RoadLineDao extends BaseIbatisDao<RoadLine, Integer> {

	@Override
	public void saveOrUpdate(RoadLine entity) throws DataAccessException {
		if (entity.getId() == null)
			save(entity);
		else
			update(entity);		
	}

	@Override
	public Class<RoadLine> getEntityClass() {
		return RoadLine.class;
	}
	
    public Page<RoadLine> findPageLine(BaseQuery query) {
        return pageQueryFix("RoadLine.findPageLine",query);
    }
    
	
    public List<RoadLine> findRoadlineList(BaseQuery query) {
		return ((List<RoadLine>) getSqlMapClientTemplate().queryForList(
				"RoadLineWebzx.find", query));
    }
	
    public List<RoadLine> findCurZx(BaseQuery query) {
		return ((List<RoadLine>) getSqlMapClientTemplate().queryForList(
				"RoadLineWebzx.findCurZx", query));
    }
}
