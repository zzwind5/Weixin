package cn.wltc.weixin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wltc.biz.customerMgr.model.CustomerInfo;
import cn.wltc.biz.customerMgr.service.CustomerService;
import cn.wltc.biz.wxmgr.model.Activity;
import cn.wltc.biz.wxmgr.model.ActivityAuth;
import cn.wltc.biz.wxmgr.model.Coupon;
import cn.wltc.biz.wxmgr.model.CouponOrder;
import cn.wltc.biz.wxmgr.model.Orgzanation;
import cn.wltc.biz.wxmgr.model.RoadLine;
import cn.wltc.biz.wxmgr.service.ActivityAuthService;
import cn.wltc.biz.wxmgr.service.ActivityService;
import cn.wltc.biz.wxmgr.service.CouponOrderService;
import cn.wltc.biz.wxmgr.service.CouponService;
import cn.wltc.biz.wxmgr.service.OrgzanationService;
import cn.wltc.biz.wxmgr.service.RoadLineService;
import cn.wltc.framework.util.DateUtil;
import cn.wltc.weixin.JsonResp;

@Controller
@RequestMapping("/linecoupon")
public class LinecouponController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private RoadLineService roadLineService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private CouponOrderService couponOrderService;
	@Autowired
	private ActivityAuthService activityAuthService;
    @Autowired
    private OrgzanationService orgzanationService;	
	private static String USER_FLAG = "user";

	private final String VIEW_BASE = "/linecoupon/";

	/** 列表 */
	@RequestMapping(value = "/list")
	public String list(ModelMap model,
			@ModelAttribute("query") CustomerInfo query,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);

		String status = request.getParameter("status");
		if (status == null) {
			status = "2";
		}

		Activity activity = new Activity();
		if (status.equals("2")) {
			activity.setProgressStatus("1");
		} else if (status.equals("3")) {
			activity.setEndStatus("1");
		} else {
			activity.setInitStatus("1");
		}
		activity.setStatus(null);

		Orgzanation orgzanation = new Orgzanation();
		orgzanation.setId(Integer.parseInt(user.getOrg_id()));
		activity.setOrgzanation(orgzanation);

		activity.setSortColumns("begin_time desc");
		List activityList = activityService
				.findActivityList(activity);
		model.addAttribute("status", status);
		model.addAttribute("list", activityList);
		model.addAttribute("title", "专线优惠管理");
		return VIEW_BASE + "list";
	}

	@RequestMapping(value = "/detail")
	public String detail(ModelMap model, Activity query,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);

		Integer id = query.getId();
		Activity item = activityService.getWebzxById(id);

		Coupon coupon = new Coupon();
		coupon.setActivityId(id);
		int zsCount = couponService.getCount(coupon);

		coupon.setLineDealStatus("1");
		int yclCount = couponService.getCount(coupon);

		coupon.setStatus("1");
		int yxCount = couponService.getCount(coupon);

		model.addAttribute("zsCount", zsCount);
		model.addAttribute("yclCount", yclCount);
		model.addAttribute("yxCount", yxCount);
		model.addAttribute("item", item);
		model.addAttribute("title", "优惠活动详情");

		return VIEW_BASE + "detail";
	}

	@RequestMapping(value = "/couponlist")
	public String couponlist(ModelMap model, Coupon query,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);

		String lineDealStatus = request.getParameter("lineDealStatus");
		if (lineDealStatus == null) {
			lineDealStatus = "0";
		}

		if (!lineDealStatus.equals("1")) {
			query.setLineDealStatus(null);
		}
		String mobile = request.getParameter("mobile");
		model.addAttribute("mobile", mobile);
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setMobile(mobile);
		query.setCustomer(customerInfo);
		if (lineDealStatus.equals("1")) {
			if (query.getStatus() != null && query.getStatus().equals("-1")) {
				query.setStatus(null);
			}
			model.addAttribute("qStatus", lineDealStatus);
		}

		query.setSortColumns("get_time desc");
		List<Coupon> list = couponService.findWebzxCouponlist(query);

		query.setLineDealStatus(null);
		int dclCount = couponService.findWebzxCount(query);
		query.setLineDealStatus("1");
		int yclCount = couponService.findWebzxCount(query);
		model.addAttribute("list", list);
		model.addAttribute("dclCount", dclCount);
		model.addAttribute("yclCount", yclCount);
		model.addAttribute("lineDealStatus", lineDealStatus);
		model.addAttribute("activityId", query.getActivityId());
		Activity  activity = activityService.getById(query.getActivityId());
		model.addAttribute("activity", activity);
		model.addAttribute("title", "专线活动列表");
		String status = request.getParameter("status");
		model.addAttribute("status", status);
		return VIEW_BASE + "couponlist";
	}

	@RequestMapping(value = "/coupondeal")
	public String coupondeal(ModelMap model, Coupon query,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);

		Coupon item = couponService.getById(query.getId());

		CouponOrder couponOrder = new CouponOrder();
		couponOrder.setCouponId(query.getId());
		List<CouponOrder> orderList = couponOrderService.find(couponOrder);
		if (orderList != null && orderList.size() > 0) {
			couponOrder = orderList.get(0);
		}
		model.addAttribute("couponOrder", couponOrder);
		
		Activity activity = activityService.getById(item.getActivityId());
		
		String endTime = activity.getEndTime();
		String endTime1 = endTime.substring(0, 10);		
		String curDate = DateUtil.formatDate(new Date());
		int dateInteval = endTime1.compareTo(curDate);
		CustomerInfo  customerInfo  = customerService.getById(item.getCustomer().getCustomer_id());
		model.addAttribute("customerInfo", customerInfo);
		model.addAttribute("activity", activity);
        model.addAttribute("dateInteval",dateInteval);
		model.addAttribute("item", item);
		model.addAttribute("title", "处理详情页面");

		return VIEW_BASE + "coupondeal";
	}

	@RequestMapping(value = "/saveCoupondeal")
	public String saveCoupondeal(ModelMap model, Coupon query,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);

		query.setLineDealTime(new Date());
		query.setLineDealStatus("1");
		if(query.getStatus() == null){
			query.setStatus("0");
		}
		couponService.update(query);

		String returnUrl = "redirect:" + VIEW_BASE
				+ "couponlist.htm?activityId=" + query.getActivityId();

		String nextFlag = request.getParameter("next");
		if (nextFlag != null && nextFlag.equals("1")) {
			Coupon coupon = new Coupon();
			query.setSortColumns("get_time desc");
			coupon.setLineDealStatus("0");
			coupon.setActivityId(query.getActivityId());
			List<Coupon> otherList = couponService.find(coupon);

			if (otherList != null && otherList.size() > 0) {
				returnUrl = "redirect:" + VIEW_BASE + "coupondeal.htm?id="
						+ otherList.get(0).getId();
			}
		}
		returnUrl += "&timestamp=" + new Date().getTime();
		return returnUrl;
	}

	@RequestMapping(value = "/couponnew")
	public String couponnew(ModelMap model, Coupon query,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);

		RoadLine roadLine = new RoadLine();
		roadLine.setOrgId(Integer.parseInt(user.getOrg_id()));
		List<RoadLine> roadLineList = roadLineService.findCurZx(roadLine);
		String orgName = "";
		if (roadLineList != null && roadLineList.size() > 0) {
			orgName = roadLineList.get(0).getOrgName();
		}
		Orgzanation  orgzanation  = orgzanationService.getById(Integer.parseInt(user.getOrg_id()));
		model.addAttribute("orgName", orgzanation.getName());
		model.addAttribute("roadLineList", roadLineList);
		model.addAttribute("user", user);
		model.addAttribute("title", "发起优惠活动");

		return VIEW_BASE + "couponnew";
	}

	@ResponseBody
	@RequestMapping(value = "/saveCoupon", method = RequestMethod.POST)
	public Object saveCoupon(ModelMap model, Activity item,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);
		boolean result = false;
		try {
			Orgzanation orgzanation = new Orgzanation();
			orgzanation.setId(Integer.parseInt(user.getOrg_id()));
			item.setOrgzanation(orgzanation);
			item.setStatus("0");
			Integer activityId = (Integer) activityService.save(item);

			ActivityAuth activityAuth = new ActivityAuth();
			activityAuth.setActivity_id(activityId);
			activityAuth.setLine_id(item.getLineId());
			activityAuth.setOrg_id(orgzanation.getId());
			activityAuth.setApply_man(user.getCustomer_id() + "");
			activityAuth.setApply_time(new Date());
			activityAuth.setSource("1");
			activityAuth.setStatus("0");
			activityAuthService.save(activityAuth);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResp(true, null, null, result);
	}

}
