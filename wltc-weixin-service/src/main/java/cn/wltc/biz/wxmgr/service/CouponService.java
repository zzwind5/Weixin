package cn.wltc.biz.wxmgr.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wltc.biz.wxmgr.dao.ActivityDao;
import cn.wltc.biz.wxmgr.dao.CouponDao;
import cn.wltc.biz.wxmgr.dao.RoadLineDao;
import cn.wltc.biz.wxmgr.model.Activity;
import cn.wltc.biz.wxmgr.model.Coupon;
import cn.wltc.biz.wxmgr.model.RoadLine;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.BaseQuery;
import cn.wltc.framework.service.EntityDao;
import cn.wltc.framework.util.DateUtil;

@Service("CouponService_")
public class CouponService extends BaseService<Coupon, Integer> {

	@Autowired
	private CouponDao couponDao;
	
	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private RoadLineDao roadLineDao;
	
	@Override
	protected EntityDao<?, ?> getEntityDao() {
		return couponDao;
	}

	/**
	 * 生成优惠券号
	 * @param activityId
	 * @return
	 */
	public String generateCouponCode(Integer activityId){
		
		Activity activity = (Activity) activityDao.getById(activityId);
		String couponCode = null;
		
		if (activity != null){
			RoadLine roadLine = (RoadLine) roadLineDao.getById(activity.getLineId());
			
			StringBuilder code = new StringBuilder();
			
			code.append(roadLine.getStartCd()).append("-").append(roadLine.getEndCd()).append("-").append(DateUtil.formatDate(new Date()));
			
			couponCode = couponDao.generateCouponCode(code.toString());
		}
		
		return couponCode;
	}
	
	
	public Map countByClzt(Coupon entity){
		
		return couponDao.countByClzt(entity);
	}
	
	public Integer getCountQrrs(Coupon query){
		
		return couponDao.getCountQrrs(query);
	}
	
    public List<Coupon> findWebzxCouponlist(BaseQuery query) {
		return couponDao.findWebzxCouponlist(query);
    }
    
	public Integer findWebzxCount(BaseQuery query){		
		return couponDao.findWebzxCount(query);
	}
	
    
}
