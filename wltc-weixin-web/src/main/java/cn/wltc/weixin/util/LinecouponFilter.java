package cn.wltc.weixin.util;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.GenericFilterBean;

import cn.wltc.biz.customerMgr.model.CustomerInfo;

public class LinecouponFilter extends GenericFilterBean implements Filter {

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");

		String contextPath = request.getContextPath();
		boolean flag = false;
		String url = null;
		if (object != null) {
			CustomerInfo user = (CustomerInfo) object;
			flag = isBindCount(user);
			if (flag) {
				flag = isLindAuth(user);
				if (!flag) {
					url = "/mywltc/lineauth.htm";
				}
			} else {
				url = "/mywltc/bindcount.htm";
			}
		} else {
			url = "/mywltc/main.htm";
		}
		if (url != null) {
			if (url.indexOf("?") > 0) {
				url += "&";
			} else {
				url += "?";
			}
			url += "timestamp=" + new Date().getTime();
			response.sendRedirect(contextPath + url);
			return;
		}else{
			filterChain.doFilter(request, response);
		}
	}

	private boolean isBindCount(CustomerInfo user) {
		boolean result = false;
		if (user != null && user.getIs_bind() != null
				&& user.getIs_bind().equals("1")) {
			result = true;
		}
		return result;
	}

	private boolean isLindAuth(CustomerInfo user) {
		boolean result = false;
		if (user != null && user.getCustomer_type() != null
				&& user.getCustomer_type().equals("1") && user.getOrg_id() != null) {
			result = true;
		}
		return result;
	}

}
