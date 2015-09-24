package cn.wltc.weixin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wltc.biz.customerMgr.model.CustomerInfo;
import cn.wltc.biz.wxmgr.model.Activity;
import cn.wltc.biz.wxmgr.model.Coupon;
import cn.wltc.biz.wxmgr.model.CouponOrder;
import cn.wltc.biz.wxmgr.service.ActivityService;
import cn.wltc.biz.wxmgr.service.CouponOrderService;
import cn.wltc.biz.wxmgr.service.CouponService;
import cn.wltc.framework.service.page.Page;
import cn.wltc.framework.util.DateUtil;
import cn.wltc.framework.web.editor.AllDateFormat;
import cn.wltc.types.ActivityType;

@RequestMapping(value = "/activity")
@Controller
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private CouponOrderService couponOrderService;

    
	private final String VIEW_BASE = "/activity/";

	/** binder用于bean属性的设置 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				AllDateFormat.instance, true));
	}

	@RequestMapping(value = "/all")
	public String all(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		return VIEW_BASE + "all";
	}
    @RequestMapping(value = "/my")
    public String my(ModelMap model, HttpServletRequest request,
            HttpServletResponse response) {
        return VIEW_BASE + "my";
    }
    
    @RequestMapping(value = "/page")
    public String allpage(ModelMap model, Activity Activity_query,HttpServletRequest request,
            HttpServletResponse response) {
        //Activity_query.setBaseDate(new Date());
        Activity_query.setStatus("2");
        CustomerInfo user = (CustomerInfo) request.getSession().getAttribute("user");
        Activity_query.setCustomerId(user.getCustomer_id());        
        //Activity_query.setPageSize(3);
        if ("my".equalsIgnoreCase(Activity_query.getPageType())){
        	Activity_query.setSortColumns("g.get_time desc");
        } else {
        	Activity_query.setSortColumns("a.update_time desc");
        }
        Page<Activity> page = activityService.findPageWeb(Activity_query);
        model.addAttribute("page", page);
        model.addAttribute("now", new Date().getTime());
        model.addAttribute("pageType", Activity_query.getPageType());
        return VIEW_BASE + "page";
    }
    
    @RequestMapping(value = "/detail")
    public String detail(ModelMap model, Integer id ,HttpServletRequest request,
            HttpServletResponse response) {
        Activity activity = activityService.getWebById(id);
        String content = activity.getContent();
        if (StringUtils.hasText(content)){
            String[] contents = content.split("(\n\r|\n)");
            activity.setContents(contents);
        }
        model.addAttribute("activity", activity);
        model.addAttribute("ActivityType", ActivityType.class);
        
        Coupon coupon_query = new Coupon();
        coupon_query.setActivityId(id);
        CustomerInfo user = (CustomerInfo) request.getSession().getAttribute("user");
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomer_id(user.getCustomer_id());
        coupon_query.setCustomer(customerInfo);
        List<Coupon> hadCoupons =  couponService.find(coupon_query);
        if (hadCoupons != null && hadCoupons.size() > 0){
            model.addAttribute("hadCoupons", true);
        } 
        model.addAttribute("now", new Date().getTime());
        return VIEW_BASE + "detail";
    }
    
    @RequestMapping(value = "/order")
    public String order(ModelMap model,  Integer id ,HttpServletRequest request,
            HttpServletResponse response) {
        model.addAttribute("activityId", id);
        return VIEW_BASE + "order";
    } 
    
    @RequestMapping(value = "/getCoupon")
    public String getCoupon(ModelMap model, Integer activityId, CouponOrder couponOrder ,HttpServletRequest request,
                         HttpServletResponse response) {
        
        Coupon coupon_query = new Coupon();
        coupon_query.setActivityId(activityId);
        CustomerInfo user = (CustomerInfo) request.getSession().getAttribute("user");
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomer_id(user.getCustomer_id());
        coupon_query.setCustomer(customerInfo);
        List<Coupon> hadCoupons =  couponService.find(coupon_query);
        if (hadCoupons != null && hadCoupons.size() > 0){
        	model.addAttribute("hadCoupons", true);
        	return VIEW_BASE + "result";
        }
        
        if (!StringUtils.hasText(couponOrder.getCargoName())
           || !StringUtils.hasText(couponOrder.getCargoNumber())
           || !StringUtils.hasText(couponOrder.getDestination())){
               model.addAttribute("param", true);
               return VIEW_BASE + "result";
        }
        
        Activity activity = activityService.getWebById(activityId);
        
        
        
        
        Coupon coupon = new Coupon();
        coupon.setActivityId(activityId);
        coupon.setCode(couponService.generateCouponCode(activityId));
        coupon.setCustomerId(user.getCustomer_id());
        coupon.setStatus("1");
        coupon.setGetTime(DateUtil.formatDateTime(new Date()));
        //coupon.setOverdueTime(activity.getEndTime());
        Integer couponId = (Integer) couponService.save(coupon);
        
        
        couponOrder.setCouponId(couponId);
        couponOrder.setLineId(activity.getLineId());
        couponOrder.setOrgId(activity.getOrgzanationId());
        couponOrderService.save(couponOrder);
        
        return VIEW_BASE + "result";
    }
}
