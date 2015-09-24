package cn.wltc.biz.weixin.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.weixin.biz.bo.WeixinNews;
import cn.wltc.biz.weixin.biz.dao.WeixinNewsDao;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.EntityDao;

@Service("WeixinNewsService_")
public class WeixinNewsService extends BaseService<WeixinNews, java.lang.Integer> {

    @Autowired
    private WeixinNewsDao weixinNewsDao;

    @Override
    protected EntityDao getEntityDao() {
        return weixinNewsDao;
    }

}
