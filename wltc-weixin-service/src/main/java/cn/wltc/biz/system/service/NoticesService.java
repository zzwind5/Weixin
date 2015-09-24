package cn.wltc.biz.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.system.dao.NoticesDao;
import cn.wltc.biz.system.model.Notices;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.EntityDao;

@Service("NoticesService_")
public class NoticesService extends BaseService<Notices, java.lang.Integer> {

	@Autowired
	private NoticesDao noticesDao;
	
	@Override
	protected EntityDao getEntityDao() {
		return noticesDao;
	}

}
