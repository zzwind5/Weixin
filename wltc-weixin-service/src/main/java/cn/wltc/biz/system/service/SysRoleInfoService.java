package cn.wltc.biz.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.system.dao.SysRoleInfoDao;
import cn.wltc.biz.system.model.SysRoleInfo;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.BaseQuery;
import cn.wltc.framework.service.page.Page;

@Service("SysRoleInfoService_")
public class SysRoleInfoService extends BaseService<SysRoleInfo, java.lang.Integer> {

    @Autowired
    private SysRoleInfoDao roleDao;

    public int getCountByJsdm(String jsdm) {
        return roleDao.getCountByJsdm(jsdm);
    }
    public Integer getMaxJsdm() {
        return roleDao.getMaxJsdm();
    }
    public Map<String,String> getJsdmJsmcMap() {
        return roleDao.getJsdmJsmcMap();
    }
    public Page<SysRoleInfo> findPageJoinMe(BaseQuery query) {
        return (Page<SysRoleInfo>)getEntityDao().findPageJoinMe(query);
    }
    public int updateEdit(SysRoleInfo jsxxb) {
        return roleDao.updateEdit(jsxxb);
    }
    
    public List<SysRoleInfo> findAll(SysRoleInfo jsxxb) {
        return roleDao.findAll(jsxxb);
    }
    public List<SysRoleInfo> findAllByYhid(Integer yhid) {
        return roleDao.findAllByYhid(yhid);
    }    
    protected SysRoleInfoDao getEntityDao() {
        return this.roleDao;
    }
}
