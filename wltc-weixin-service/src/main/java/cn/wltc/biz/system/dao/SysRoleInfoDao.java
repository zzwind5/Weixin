package cn.wltc.biz.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wltc.biz.system.model.SysRoleInfo;
import cn.wltc.framework.service.BaseQuery;
import cn.wltc.framework.service.page.Page;

@Repository("SysRoleInfoDao_")
public class SysRoleInfoDao extends G_SysRoleInfoDao {
    
    public String getCountByJsdm() {
        return getEntityClass().getSimpleName() +".getCountByJsdm";
    }
    public Integer getMaxJsdm() {
        return ((Integer)getSqlMapClientTemplate().queryForObject(getEntityClass().getSimpleName() +".getMaxJsdm", null));
    }    
    public int getCountByJsdm(String jsdm) {
        return ((Long)getSqlMapClientTemplate().queryForObject(getCountByJsdm(), jsdm)).intValue();
    }
    public Map<String,String> getJsdmJsmcMap() {
        return (Map<String,String> )getSqlMapClientTemplate().queryForMap(getEntityClass().getSimpleName() + ".getJsdmJsmc", null, "id", "jsmc");
    }   
    
    public Page<SysRoleInfo> findPageJoinMe(BaseQuery query) {
        return pageQuery(getEntityClass().getSimpleName() + ".findPageJoinMe",query);
    }
    public int updateEdit(SysRoleInfo jsxxb) {
        return (Integer)getSqlMapClientTemplate().update(getEntityClass().getSimpleName() + ".updateEdit", jsxxb);
    }
    
    public List<SysRoleInfo> findAll(SysRoleInfo jsxxb) {
        return ((List<SysRoleInfo>)getSqlMapClientTemplate().queryForList(getEntityClass().getSimpleName() +".findAll", jsxxb));
    }
    public List<SysRoleInfo> findAllByYhid(Integer yhid) {
        return ((List<SysRoleInfo>)getSqlMapClientTemplate().queryForList(getEntityClass().getSimpleName() +".findAllByYhid", yhid));
    }    
    
}
