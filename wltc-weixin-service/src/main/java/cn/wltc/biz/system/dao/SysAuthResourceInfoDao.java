package cn.wltc.biz.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.wltc.biz.system.model.SysAuthResourceInfo;

@Repository("SysAuthResourceInfoDao_")
public class SysAuthResourceInfoDao extends G_SysAuthResourceInfoDao {
 
    public List<SysAuthResourceInfo> findAllByTypes(List<String> zytypes) {
        return ((List<SysAuthResourceInfo>)getSqlMapClientTemplate().queryForList(getEntityClass().getSimpleName() +".findAllByTypes", zytypes));
    }    
    public List<SysAuthResourceInfo> findAll(SysAuthResourceInfo zyxxb) {
        return ((List<SysAuthResourceInfo>)getSqlMapClientTemplate().queryForList(getEntityClass().getSimpleName() +".findAll", zyxxb));
    }
    
    public List<SysAuthResourceInfo>  findUserResulesByTypes(Integer yhid,List<String> zytypes){
        Map<String,Object> par = new HashMap<String,Object>();
        par.put("yhid", yhid);
        par.put("zytypes", zytypes);
        return ((List<SysAuthResourceInfo>)getSqlMapClientTemplate().queryForList(getEntityClass().getSimpleName() +".getByYhid", par));
    }
}
