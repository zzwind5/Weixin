package cn.wltc.biz.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.system.dao.SysUserInfoDao;
import cn.wltc.biz.system.dao.SysUserRoleInfoDao;
import cn.wltc.biz.system.model.SysUserInfo;
import cn.wltc.biz.system.model.SysUserRoleInfo;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.BaseQuery;

@Service("SysUserInfoService_")
public class SysUserInfoService extends BaseService<SysUserInfo, java.lang.Integer> {

    @Autowired
    private SysUserInfoDao userDao;
    @Autowired
    private SysUserRoleInfoDao userRole;
    
    public int getCountByYhm(String yhdm) {
        return userDao.getCountByYhm(yhdm);
    }
    public SysUserInfo getByYhm(String yhdm) {
        return userDao.getByYhm(yhdm);
    }
    
    public int deleteByYhid(int yhid) {
        return userRole.deleteByYhid(yhid);
    }
    public int saveUserRole(SysUserRoleInfo yhjsxxb) {
        return (Integer)userRole.save(yhjsxxb);
    } 
    public List<Integer> getByYhid(int yhid) {
        List<SysUserRoleInfo> list =  userRole.getByYhid(yhid);
        List<Integer> yhjsids = new ArrayList<Integer>();
        
        if (list != null){
            for (SysUserRoleInfo yhjsxxb :list ){
                yhjsids.add(yhjsxxb.getJsid());
            }
        }
        return yhjsids;
        
    } 
    public Integer findPageByRoleAndTypeCount(BaseQuery query) {
        return userDao.findPageByRoleAndTypeCount(query);
    }     
    public List<SysUserInfo> findPageByRoleAndType(BaseQuery query) {
        return userDao.findPageByRoleAndType(query);
    } 
    
    protected SysUserInfoDao getEntityDao() {
        return this.userDao;
    }

}
