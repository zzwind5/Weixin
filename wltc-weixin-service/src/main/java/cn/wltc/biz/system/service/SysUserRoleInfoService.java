package cn.wltc.biz.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.system.dao.SysUserRoleInfoDao;
import cn.wltc.biz.system.model.SysUserRoleInfo;
import cn.wltc.common.service.BaseService;

@Service("SysUserRoleInfoService_")
public class SysUserRoleInfoService extends BaseService<SysUserRoleInfo, java.lang.Integer> {

    @Autowired
    private SysUserRoleInfoDao userRoleDao;

    
    protected SysUserRoleInfoDao getEntityDao() {
        return this.userRoleDao;
    }
    
    public List<SysUserRoleInfo> getByYhidJoinJsxxb(int yhid) {
        return userRoleDao.getByYhidJoinJsxxb(yhid);
    }
}
