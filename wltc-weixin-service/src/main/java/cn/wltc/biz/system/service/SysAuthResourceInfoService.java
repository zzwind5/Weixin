package cn.wltc.biz.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.system.dao.SysAuthResourceInfoDao;
import cn.wltc.biz.system.model.SysAuthResourceInfo;
import cn.wltc.common.service.BaseService;

@Service("SysAuthResourceInfoService_")
public class SysAuthResourceInfoService extends BaseService<SysAuthResourceInfo, java.lang.Integer> {

    @Autowired
    private SysAuthResourceInfoDao resourceDao;

    
    public List<SysAuthResourceInfo> findAllByTypes(List<String> zytypes) {
        return resourceDao.findAllByTypes(zytypes);
    }
    public List<SysAuthResourceInfo> findUserResulesByTypes(int yhid,List<String> zytypes) {
        return resourceDao.findUserResulesByTypes(yhid,zytypes);
    }
    protected SysAuthResourceInfoDao getEntityDao() {
        return this.resourceDao;
    }


}
