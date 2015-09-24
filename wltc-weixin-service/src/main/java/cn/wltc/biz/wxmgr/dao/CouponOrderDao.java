package cn.wltc.biz.wxmgr.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cn.wltc.biz.wxmgr.model.Coupon;
import cn.wltc.biz.wxmgr.model.CouponOrder;
import cn.wltc.common.dao.BaseIbatisDao;

@Repository("CouponOrderDao_")
@SuppressWarnings("unchecked")
public class CouponOrderDao extends BaseIbatisDao<CouponOrder, Integer> {

	@Override
	public void saveOrUpdate(CouponOrder entity) throws DataAccessException {
		if (entity.getOrderId() == null)
			save(entity);
		else
			update(entity);
	}

	@Override
	public Class<CouponOrder> getEntityClass() {
		return CouponOrder.class;
	}
	


}
