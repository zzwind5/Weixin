package cn.wltc.biz.wxmgr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.wxmgr.dao.RoadLineDao;
import cn.wltc.biz.wxmgr.model.RoadLine;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.BaseQuery;
import cn.wltc.framework.service.EntityDao;
import cn.wltc.framework.service.page.Page;

@Service("RoadLineService_")
public class RoadLineService extends BaseService<RoadLine, Integer> {

	@Autowired
	private RoadLineDao roadLineDao;
	
	@Override
	protected EntityDao<?, ?> getEntityDao() {
		return roadLineDao;
	}
	
    public Page<RoadLine> findPageLine(BaseQuery query) {
        return roadLineDao.findPageLine(query);
    }
	
    public List<RoadLine> findRoadlineList(BaseQuery query) {
		return roadLineDao.findRoadlineList(query);
    }
	
    public List<RoadLine> findCurZx(BaseQuery query) {
		return roadLineDao.findCurZx(query);
    }
}
