package cn.wltc.biz.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wltc.biz.system.model.SysRoleAuthInfo;

@Repository("SysRoleAuthInfoDao_")
public class SysRoleAuthInfoDao extends G_SysRoleAuthInfoDao {
    
    public int deleteByYhid(int yhid) {
        return ((Integer)getSqlMapClientTemplate().delete(getEntityClass().getSimpleName() + ".deleteByYhid", yhid)).intValue();
    }
    public SysRoleAuthInfo getByRoleIdAndResourceId(int roleId,int resourceId) {
        Map<String,Integer> querymap = new HashMap<String,Integer>();
        querymap.put("jsid", roleId);
        querymap.put("zyid", resourceId);
        return (SysRoleAuthInfo)getSqlMapClientTemplate().queryForObject(getEntityClass().getSimpleName() + ".getByRoleIdAndResourceId", querymap);
    }
    public int deleteByRoleIdAndResourceId(int roleId,int resourceId) {
        Map<String,Integer> querymap = new HashMap<String,Integer>();
        querymap.put("jsid", roleId);
        querymap.put("zyid", resourceId);        
        return (int)getSqlMapClientTemplate().delete(getEntityClass().getSimpleName() + ".deleteByRoleIdAndResourceId", querymap);
    }  
    public List<SysRoleAuthInfo> getByRoleId(int roleId) {
        Map<String,Integer> querymap = new HashMap<String,Integer>();
        querymap.put("jsid", roleId);
        return (List<SysRoleAuthInfo>)getSqlMapClientTemplate().queryForList(getEntityClass().getSimpleName() + ".getByRoleId", querymap);
    }    
}
