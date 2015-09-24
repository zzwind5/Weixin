package cn.wltc.weixin.util;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import cn.wltc.biz.customerMgr.model.CustomerInfo;
import cn.wltc.biz.customerMgr.service.CustomerService;
import cn.wltc.biz.weixin.util.CommonUtil;
import cn.wltc.framework.util.StringUtil;

public class CheckLoginFilter extends GenericFilterBean implements Filter {

	@Autowired
	private CustomerService customerService;

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
	    
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		System.out.println("start ...." + request.getRequestURI());
		String serverName = request.getServerName();
		String context = request.getContextPath();
		String serverHost = "http://" + serverName + context;

		HttpSession session = request.getSession();
		// //////////////test//////////////
		 CustomerInfo user11 = customerService.getById(5);
		 session.setAttribute("user", user11);
		// ////////////////////////////
		if (session != null && session.getAttribute("user") != null) {
			filterChain.doFilter(request, response);
			return;
		}

		String code = request.getParameter("code");
		String state = request.getParameter("state");
		if (StringUtil.isNotBlank(code) && "wltc".equals(state)) { 
			String openId = CommonUtil.getOpenidByCode(code);
			CustomerInfo user = customerService.getCustomerInfo(openId);			
			if(user == null){
				customerService.subscribe(openId);
				user = customerService.getCustomerInfo(openId);	
			}
			
			session.setAttribute("customer_id", user.getCustomer_id());
			if (!user.isOauthed()) {
			    System.out.println("redirect:auth");
				String target = URLEncoder.encode(serverHost + request.getServletPath(), "utf-8");
				response.sendRedirect(CommonUtil.getCodeUrlUserInfo(target));
				return;
			} else {
			    session.setAttribute("user", user);
			}
		} else if (StringUtil.isNotBlank(code) && "wltcuserinfo".equals(state)) {
            JSONObject userInfoJson = CommonUtil.getWeixinUserInfo(code);
            CustomerInfo userInfo = new CustomerInfo();
            //userInfo.setOpenid(userInfoJson.getString("openid"));
            userInfo.setNickname(userInfoJson.getString("nickname"));
            System.out.println(userInfoJson.getString("headimgurl"));
            userInfo.setSex(userInfoJson.getString("sex"));
            userInfo.setProvince(userInfoJson.getString("province"));
            userInfo.setCity(userInfoJson.getString("city"));
            userInfo.setCountry(userInfoJson.getString("country"));
            userInfo.setHeadimgurl(userInfoJson.getString("headimgurl"));
            userInfo.setCustomer_id((Integer)session.getAttribute("customer_id"));
            userInfo.setIsoauth("1");
            customerService.update(userInfo);
            CustomerInfo user = customerService.getById((Integer)session.getAttribute("customer_id"));
            session.setAttribute("user", user);
		} else if (StringUtil.isBlank(code) && StringUtil.isNotBlank(state)) {
			// TODO:拒絕
		} else {
			String target = URLEncoder.encode(serverHost + request.getServletPath(), "utf-8");
			response.sendRedirect(CommonUtil.getCodeUrlBase(target));
			return;
		}
		filterChain.doFilter(request, response);
	}

}
