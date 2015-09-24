package cn.wltc.biz.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.system.dao.SysRoleAuthInfoDao;
import cn.wltc.biz.system.model.SysRoleAuthInfo;
import cn.wltc.common.service.BaseService;

@Service("SysRoleAuthInfoService_")
public class SysRoleAuthInfoService extends BaseService<SysRoleAuthInfo, java.lang.Integer> {

    @Autowired
    private SysRoleAuthInfoDao roleResourceDao;
    
    public SysRoleAuthInfo getByRoleIdAndResourceId(int roleId,int resourceId) {
        return roleResourceDao.getByRoleIdAndResourceId(roleId,resourceId);
    }
    public List<SysRoleAuthInfo> getByRoleId(int roleId) {
        return roleResourceDao.getByRoleId(roleId);
    }
    public List<Integer> getIdByRoleId(int roleId) {
        List<SysRoleAuthInfo> list = getByRoleId(roleId);
        List<Integer> listid = new ArrayList<Integer>();
        if (list != null){
            for (SysRoleAuthInfo jsqxxxb:list){
                listid.add(jsqxxxb.getZyid());
            }
        }
        return listid;
    }
    
    @Override
    protected SysRoleAuthInfoDao getEntityDao() {
        return this.roleResourceDao;
    }


}
