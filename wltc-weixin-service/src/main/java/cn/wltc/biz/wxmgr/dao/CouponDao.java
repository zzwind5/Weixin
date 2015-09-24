package cn.wltc.biz.wxmgr.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cn.wltc.biz.wxmgr.model.Coupon;
import cn.wltc.common.dao.BaseIbatisDao;
import cn.wltc.framework.service.BaseQuery;

@Repository("CouponDao_")
@SuppressWarnings("unchecked")
public class CouponDao extends BaseIbatisDao<Coupon, Integer> {

	@Override
	public void saveOrUpdate(Coupon entity) throws DataAccessException {
		if (entity.getId() == null)
			save(entity);
		else
			update(entity);
	}

	@Override
	public Class<Coupon> getEntityClass() {
		return Coupon.class;
	}
	
	public String generateCouponCode(String code){
		
		return (String) getSqlMapClientTemplate().queryForObject("Coupon.generateCouponCode", code);
	}
	
	public Map countByClzt(Coupon entity){
		
		return (Map) getSqlMapClientTemplate().queryForObject("Coupon.countByClzt", entity);
	}
	
	public Integer getCountQrrs(Coupon query){
		
		return (Integer) getSqlMapClientTemplate().queryForObject("Coupon.getCountQrrs",query);
	}
	
    public List<Coupon> findWebzxCouponlist(BaseQuery query) {
		return ((List<Coupon>) getSqlMapClientTemplate().queryForList(
				"Coupon.findWebzxCouponlist", query));
    }
    
	public Integer findWebzxCount(BaseQuery query){		
		return (Integer) getSqlMapClientTemplate().queryForObject("Coupon.findWebzxCount",query);
	}
	
    
}
