package cn.wltc.biz.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wltc.biz.system.model.SysUserInfo;
import cn.wltc.framework.service.BaseQuery;

@Repository("SysUserInfoDao_")
public class SysUserInfoDao extends G_SysUserInfoDao {
    
    public String getCountByYhm() {
        return getEntityClass().getSimpleName() +".getCountByYhm";
    }
    
    public int getCountByYhm(String yhdm) {
        return ((Long)getSqlMapClientTemplate().queryForObject(getCountByYhm(), yhdm)).intValue();
    }
    public SysUserInfo getByYhm(String yhdm) {
        return ((SysUserInfo)getSqlMapClientTemplate().queryForObject( getEntityClass().getSimpleName() + ".getByYhm", yhdm));
    }  
    public Integer findPageByRoleAndTypeCount(BaseQuery query) {
        return (Integer)getSqlMapClientTemplate().queryForObject(getEntityClass().getSimpleName() + ".findPageByRoleAndTypeCount", query);
    }    
    public List<SysUserInfo> findPageByRoleAndType(BaseQuery query) {
        return getSqlMapClientTemplate().queryForList(getEntityClass().getSimpleName() + ".findPageByRoleAndType", query);
    }
}
