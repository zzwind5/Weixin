package cn.wltc.biz.adcd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.adcd.dao.BaseAdcdDao;
import cn.wltc.biz.adcd.model.BaseAdcd;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.EntityDao;

@Service("BaseAdcdService_")
public class BaseAdcdService extends BaseService<BaseAdcd, java.lang.Integer> {

    @Autowired
    private BaseAdcdDao baseAdcdDao;

    @Override
    protected EntityDao getEntityDao() {
        return baseAdcdDao;
    }

}
