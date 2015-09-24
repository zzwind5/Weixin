package cn.wltc.biz.wxmgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.wxmgr.dao.CollectedLineDao;
import cn.wltc.biz.wxmgr.model.CollectedLine;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.EntityDao;

@Service("CollectedLineService_")
public class CollectedLineService extends BaseService<CollectedLine, CollectedLine> {

	@Autowired
	private CollectedLineDao CollectedLineDao;
	
	@Override
	protected EntityDao<CollectedLine, CollectedLine> getEntityDao() {
		return CollectedLineDao;
	}

}
