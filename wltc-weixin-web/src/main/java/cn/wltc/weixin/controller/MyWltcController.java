package cn.wltc.weixin.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wltc.biz.customerMgr.model.CustomerInfo;
import cn.wltc.biz.customerMgr.service.CustomerService;
import cn.wltc.biz.weixin.util.SMSUtil;
import cn.wltc.biz.wxmgr.model.Activity;
import cn.wltc.biz.wxmgr.model.Coupon;
import cn.wltc.biz.wxmgr.model.Orgzanation;
import cn.wltc.biz.wxmgr.model.RoadLine;
import cn.wltc.biz.wxmgr.model.UserInfo;
import cn.wltc.biz.wxmgr.service.ActivityService;
import cn.wltc.biz.wxmgr.service.CouponService;
import cn.wltc.biz.wxmgr.service.OrgzanationService;
import cn.wltc.biz.wxmgr.service.RoadLineService;
import cn.wltc.biz.wxmgr.service.UserInfoService;
import cn.wltc.framework.service.page.Page;
import cn.wltc.framework.web.editor.AllDateFormat;
import cn.wltc.weixin.JsonResp;

@Controller
@RequestMapping("/mywltc")
public class MyWltcController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private RoadLineService roadLineService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private OrgzanationService orgzanationService;

	private static String USER_FLAG = "user";
	private static String CHECK_CODE_FLAG = "checkCode";

	private final String VIEW_BASE = "/mywltc/";

	/** binder用于bean属性的设置 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				AllDateFormat.instance, true));
	}

	/** 列表 */
	@RequestMapping(value = "/main")
	public String main(ModelMap model,
			@ModelAttribute("query") CustomerInfo query,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);

		Coupon coupon = new Coupon();
		coupon.setCustomer(user);

		Activity activity = new Activity();
		activity.setCustomerId(user.getCustomer_id());
		activity.setPageSize(100);
		Page<Activity> page = activityService.findPageWebzx(activity);
		int couponCount = page.getTotalCount();

		RoadLine roadLine = new RoadLine();
		roadLine.setCustomerId(user.getCustomer_id());
		List<RoadLine> roadlineList = roadLineService
				.findRoadlineList(roadLine);
		
		String orgId = user.getOrg_id();
		Orgzanation orgzanation = null;
		if(orgId != null && !orgId.trim().equals("")){
			orgzanation = orgzanationService.getById(Integer.parseInt(orgId));
		}
		model.addAttribute("user", user);
		model.addAttribute("orgzanation", orgzanation);
		model.addAttribute("couponCount", couponCount);
		model.addAttribute("roadlineCount", roadlineList.size());
		return VIEW_BASE + "main";
	}

	@RequestMapping(value = "/mycoupon")
	public String mycoupon(ModelMap model, CustomerInfo query,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);

		String status = request.getParameter("status");
		if (status == null) {
			status = "0";
		}

		Activity activity = new Activity();
		activity.setCustomerId(user.getCustomer_id());
		activity.setPageSize(100);
		activity.setSortColumns("get_time desc");
		Page<Activity> page = activityService.findPageWebzx(activity);

		model.addAttribute("page", page);
		model.addAttribute("couponCount", page.getTotalCount());
		model.addAttribute("title", "我参加的优惠活动");

		return VIEW_BASE + "mycoupon";
	}

	@RequestMapping(value = "/myroadline")
	public String myroadline(ModelMap model, CustomerInfo query,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);
		model.addAttribute("user", user);

		RoadLine roadLine = new RoadLine();
		roadLine.setCustomerId(user.getCustomer_id());
		List<RoadLine> list = roadLineService.findRoadlineList(roadLine);

		model.addAttribute("list", list);
		model.addAttribute("roadlineCount", list.size());
		model.addAttribute("title", "我收藏的专线");

		return VIEW_BASE + "myroadline";
	}
	

	@RequestMapping(value = "/lineauthBefore")
	public String lineauthBefore(ModelMap model, CustomerInfo query,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);
		if (user.getCustomer_type() != null
				&& user.getCustomer_type().equals("1")) {
			return mainUrl();
		}
		model.addAttribute("title", "专线认证");

		return VIEW_BASE + "lineauthBefore";
	}

	@RequestMapping(value = "/lineauth")
	public String lineauth(ModelMap model, CustomerInfo query,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);

		if (user.getCustomer_type() != null
				&& user.getCustomer_type().equals("1")) {
			return mainUrl();
		}
		model.addAttribute("title", "专线认证");

		return VIEW_BASE + "lineauth";
	}

	@RequestMapping(value = "/saveLineauth")
	public String saveLineauth(ModelMap model, RoadLine item,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);

		if (user.getCustomer_type() != null
				&& user.getCustomer_type().equals("1")) {
			return mainUrl();
		}

		String startCityName = request.getParameter("startCityName");
		String endCityName = request.getParameter("endCityName");
		String sOrgId = request.getParameter("orgId");
		
		Integer orgId = 0;

		if(sOrgId == null || sOrgId.trim().equals("")){
			Orgzanation orgzanation = new Orgzanation();
			orgzanation.setAddress(request.getParameter("orgAddress"));
			orgzanation.setContract(request.getParameter("orgContract"));
			orgzanation.setPhone(request.getParameter("orgPhone"));
			orgzanation.setName(request.getParameter("orgName"));
			orgzanation.setType("1");
			orgId = (Integer) orgzanationService.save(orgzanation);
		}else{
			orgId = Integer.parseInt(sOrgId);
		}

		if (startCityName != null) {
			String[] startCityNames = startCityName.split("-");
			if (startCityNames.length > 2) {
				item.setStartProvince(startCityNames[0]);
				item.setStartCity(startCityNames[1]);
				item.setStart(startCityNames[2]);
			}
		}
		if (endCityName != null) {
			String[] endCityNames = endCityName.split("-");
			if (endCityNames.length > 2) {
				item.setEndProvince(endCityNames[0]);
				item.setEndCity(endCityNames[1]);
				item.setEnd(endCityNames[2]);
			}
		}
		item.setOrgId(orgId);
		roadLineService.save(item);

		UserInfo userInfo = new UserInfo();
		userInfo.setMobile(user.getMobile());
		List<UserInfo> userInfoList = userInfoService.findPage(userInfo)
				.getResult();
		if (userInfoList != null && userInfoList.size() > 0) {
			UserInfo userInfo2 = userInfoList.get(0);
			UserInfo userInfo3 = new UserInfo();
			userInfo3.setUid(userInfo2.getUid());
			userInfo3.setOrg_id(orgId);
			userInfoService.update(userInfo3);
		}

		user.setOrg_id(orgId + "");
		user.setCustomer_type("1");
		customerService.update(user);

		session.setAttribute(USER_FLAG, user);

	      String returnUrl = "redirect:/linecoupon/list.htm";
	        returnUrl += "?timestamp=" + new Date().getTime();
	        return returnUrl;

	}

	@RequestMapping(value = "/bindcount")
	public String bindcount(ModelMap model, CustomerInfo query,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);

		if (user.getIs_bind() != null && user.getIs_bind().equals("1")) {
			String returnUrl = "redirect:" + VIEW_BASE + "main.htm";
			returnUrl += "?timestamp=" + new Date().getTime();
			return returnUrl;
		}
		model.addAttribute("title", "帐号绑定");

		return VIEW_BASE + "bindcount";
	}

	@RequestMapping(value = "/saveBindcount")
	public String saveBindcount(ModelMap model, CustomerInfo item,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		CustomerInfo user = (CustomerInfo) session.getAttribute(USER_FLAG);

		if (user.getIs_bind() != null && user.getIs_bind().equals("1")) {
			return mainUrl();
		}

		user.setCustomer_type("0");

		UserInfo userInfo = new UserInfo();
		userInfo.setMobile(item.getMobile());
		int count = userInfoService.getCount(userInfo);
		if (count == 0) {
			userInfo.setNick(item.getName());
			userInfo.setOrg_id(0);
			userInfoService.save(userInfo);
		} else {
			List<UserInfo> userInfoList = userInfoService.find(userInfo);
			if (userInfoList.size() > 0) {
				UserInfo userInfo2 = userInfoList.get(0);
				Integer org_id = userInfo2.getOrg_id();
				if(org_id != null && org_id != 0){
					user.setOrg_id(org_id + "");
					user.setCustomer_type("1");
				}
			}
		}
		user.setName(item.getName());
		user.setMobile(item.getMobile());
		user.setIs_bind("1");
		customerService.update(user);
		session.setAttribute(USER_FLAG, user);
		return mainUrl();
	}

	@ResponseBody
	@RequestMapping(value = "/checkCount", method = RequestMethod.POST)
	public Object checkCount(ModelMap model, CustomerInfo query)
			throws Exception {
		boolean result = true;
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setMobile(query.getMobile());
		int count = customerService.getCountRepeat(customerInfo);
		if (count > 0) {
			result = false;
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/getCheckCode", method = RequestMethod.POST)
	public Object getCheckCode(ModelMap model, CustomerInfo query,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean result = false;
		String mobile = query.getMobile();
		HttpSession session = request.getSession();
		String checkCode = "";
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			checkCode += random.nextInt(10);
		}
		// ///////短信发送////////////////
		StringBuffer smsConentBuffer =  new StringBuffer("您的56同城账户验证码为 ");
		smsConentBuffer.append(checkCode);
		smsConentBuffer.append("  将该验证码输入微信页面，进行账户绑定。");
		result = SMSUtil.sendSMS(mobile, smsConentBuffer.toString());

		session.setAttribute(CHECK_CODE_FLAG, checkCode);
		return new JsonResp(true, null, null, result);
	}

	@ResponseBody
	@RequestMapping(value = "/cancelCheckCode", method = RequestMethod.POST)
	public Object cancelCheckCode(ModelMap model, CustomerInfo query,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean result = false;
		HttpSession session = request.getSession();
		session.setAttribute(CHECK_CODE_FLAG, null);
		return new JsonResp(true, null, null, result);
	}

	@ResponseBody
	@RequestMapping(value = "/confirmCheckCode", method = RequestMethod.POST)
	public Object confirmCheckCode(ModelMap model, CustomerInfo query,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean result = false;
		HttpSession session = request.getSession();
		Object checkCode = session.getAttribute(CHECK_CODE_FLAG);
		String verifyCode = request.getParameter("verify_code");
		if (checkCode != null && ((String) checkCode).equals(verifyCode)) {
			result = true;
		}
		return result;
	}
	

	@ResponseBody
	@RequestMapping(value = "/findOrgByName", method = RequestMethod.POST)
	public Object findOrgByName(ModelMap model,@RequestParam("param") java.lang.String param) throws Exception {
		Orgzanation item = orgzanationService.findByName(param);
		return new JsonResp(true, null, null, item);
	}
	
	private String mainUrl(){
		String returnUrl = "redirect:" + VIEW_BASE + "main.htm";
		returnUrl += "?timestamp=" + new Date().getTime();
		return returnUrl;
	}

}
