package cn.wltc.common.web.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import cn.wltc.common.web.cookie.UserAgent;
import cn.wltc.framework.web.cookyjar.Cookyjar;

public class UserAgentArgumentResolver implements WebArgumentResolver {

	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
		if (methodParameter.getParameterType().equals(UserAgent.class)) {
			Cookyjar cookyjar = (Cookyjar) webRequest
					.getAttribute(Cookyjar.CookyjarInRequest,
							RequestAttributes.SCOPE_REQUEST);
			if (cookyjar != null) {
				return cookyjar.getObject(UserAgent.class);
			}
		}
		return UNRESOLVED;
	}
}
