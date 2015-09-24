package cn.wltc.biz.wxmgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.wxmgr.dao.CouponOrderDao;
import cn.wltc.biz.wxmgr.model.CouponOrder;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.EntityDao;

@Service("CouponOrderService_")
public class CouponOrderService extends BaseService<CouponOrder, Integer> {

    @Autowired
    private CouponOrderDao couponOrderDao;
    
    @Override
    protected EntityDao<CouponOrder, Integer> getEntityDao() {
        return couponOrderDao;
    }
}
