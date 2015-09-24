package cn.wltc.biz.wxmgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.wxmgr.dao.UserInfoDao;
import cn.wltc.biz.wxmgr.model.UserInfo;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.EntityDao;

@Service("UserInfoService_")
public class UserInfoService extends BaseService<UserInfo, Integer> {

	@Autowired
	private UserInfoDao userInfoDao;

	@Override
	protected EntityDao<UserInfo, Integer> getEntityDao() {
		return userInfoDao;
	}

}
