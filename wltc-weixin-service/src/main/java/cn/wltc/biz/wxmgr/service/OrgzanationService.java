package cn.wltc.biz.wxmgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.wxmgr.dao.OrgzanationDao;
import cn.wltc.biz.wxmgr.model.Orgzanation;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.EntityDao;

@Service("OrgzanationService_")
public class OrgzanationService extends BaseService<Orgzanation, Integer> {

	@Autowired
	private OrgzanationDao orgzanationDao;
	
	@Override
	protected EntityDao<Orgzanation, Integer> getEntityDao() {
		return orgzanationDao;
	}
    
    public Orgzanation findByName(String param){    	
    	return orgzanationDao.findByName(param);
    }

}
