package cn.wltc.common.web.resolver;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.wltc.common.web.access.UserAccessDeniedException;
import cn.wltc.common.web.cookie.UserAgent;
import cn.wltc.framework.web.cookyjar.Cookyjar;

public class MelodyDmoExceptionResolver implements HandlerExceptionResolver {
	private static final Log logger = LogFactory
			.getLog(MelodyDmoExceptionResolver.class);

	private String webEncoding = "UTF-8";

	private String errorPage = "/error";

	private String adminLoginPath = "/admin/login.htm";

	private String adminDeniedPage = "/admin/accessDenied";

	private String adminLoginReturnParameterName = "backto";

	public void setWebEncoding(String webEncoding) {
		this.webEncoding = webEncoding;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	public void setAdminLoginPath(String adminLoginPath) {
		this.adminLoginPath = adminLoginPath;
	}

	public void setAdminDeniedPage(String adminDeniedPage) {
		this.adminDeniedPage = adminDeniedPage;
	}

	public void setAdminLoginReturnParameterName(
			String adminLoginReturnParameterName) {
		this.adminLoginReturnParameterName = adminLoginReturnParameterName;
	}

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		if (ex instanceof UserAccessDeniedException) {
			return resolveAdminAccessDeniedException(request);
		}
		logger.error("web error", ex);
		return new ModelAndView(this.errorPage);
	}

	private ModelAndView resolveAdminAccessDeniedException(
			HttpServletRequest request) {
		Cookyjar cookyjar = (Cookyjar) request
				.getAttribute(Cookyjar.CookyjarInRequest);
		UserAgent agent = (UserAgent) cookyjar
				.getObject(UserAgent.class);
		if (agent == null) {// û��¼�����򵽵�¼����
			String returnUrl = getReturnUrl(request);
			return new ModelAndView("redirect:" + adminLoginPath,
					adminLoginReturnParameterName, returnUrl);
		}
		return new ModelAndView(this.adminDeniedPage);
	}

	private String getReturnUrl(HttpServletRequest request) {
		StringBuffer sb = request.getRequestURL();
		appendRequestParameters(sb, request);
		try {
			return URLEncoder.encode(sb.toString(), this.webEncoding);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	private void appendRequestParameters(StringBuffer sb,
			HttpServletRequest request) {
		Enumeration en = request.getParameterNames();
		if (!en.hasMoreElements()) {
			return;
		}
		sb.append('?');
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			String[] values = request.getParameterValues(name);
			if (values == null || values.length == 0) {
				continue;
			}
			for (String v : values) {
				try {
					v = URLEncoder.encode(v, this.webEncoding);
				} catch (UnsupportedEncodingException ignore) {
				}
				sb.append(name).append('=').append(v).append('&');
			}
		}
		sb.deleteCharAt(sb.length() - 1);
	}
}
