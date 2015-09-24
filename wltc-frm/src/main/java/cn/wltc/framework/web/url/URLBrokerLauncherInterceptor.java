package cn.wltc.framework.web.url;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.wltc.framework.util.StringUtil;

/**
 * 
 * @author fish
 *
 */
public class URLBrokerLauncherInterceptor extends HandlerInterceptorAdapter {
	private static String myTag = "_"
			+ URLBrokerLauncherInterceptor.class.getName();

	private static String tagValue = "1";

	private Map<String, URLBroker> brokers;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Object tag = request.getAttribute(myTag);
		if (tag == null) {
			for (Entry<String, URLBroker> entry : brokers.entrySet()) {
				request.setAttribute(entry.getKey(), entry.getValue());
			}
			request.setAttribute(myTag, tagValue);
		}
		
		if (StringUtil.isEmpty(request.getQueryString())){
			request.setAttribute("currentUri", request.getRequestURI());
		} else {
			request.setAttribute("currentUri", request.getRequestURI()+"?"+request.getQueryString());
		}
		return super.preHandle(request, response, handler);
	}

	public Map<String, URLBroker> getBrokers() {
		return brokers;
	}

	public void setBrokers(Map<String, URLBroker> brokers) {
		this.brokers = brokers;
	}

}
