package cn.wltc.biz.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wltc.biz.system.model.SysUserRoleInfo;

@Repository("SysUserRoleInfoDao_")
public class SysUserRoleInfoDao extends G_SysUserRoleInfoDao {
    
    public int deleteByYhid(int yhid) {
        return ((Integer)getSqlMapClientTemplate().delete(getEntityClass().getSimpleName() + ".deleteByYhid", yhid)).intValue();
    }
    public List<SysUserRoleInfo> getByYhid(int yhid) {
        return (List<SysUserRoleInfo>)getSqlMapClientTemplate().queryForList(getEntityClass().getSimpleName() + ".getByYhid", yhid);
    }
    public List<SysUserRoleInfo> getByYhidJoinJsxxb(int yhid) {
        return (List<SysUserRoleInfo>)getSqlMapClientTemplate().queryForList(getEntityClass().getSimpleName() + ".getByYhidJoinJsxxb", yhid);
    }    
}
