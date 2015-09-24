package cn.wltc.biz.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.base.dao.UserFileDao;
import cn.wltc.biz.base.model.UserFile;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.EntityDao;

@Service("UserFileService_")
public class UserFileService extends BaseService<UserFile, java.lang.Integer>{

	@Autowired
	private UserFileDao userFileDao;
	
	
	@Override
	protected EntityDao getEntityDao() {
		return userFileDao;
	}



}
