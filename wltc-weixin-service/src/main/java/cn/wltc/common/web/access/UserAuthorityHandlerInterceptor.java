package cn.wltc.common.web.access;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import cn.wltc.common.web.cookie.UserAgent;
import cn.wltc.framework.web.adapter.AnnotationMethodHandlerInterceptorAdapter;
import cn.wltc.framework.web.cookyjar.Cookyjar;

/**
 * 管理端权限拦截控制器，根据 @AdminAccess annotation來標記這個類的方法需要權限控制，
 * 
 * 
 */
public class UserAuthorityHandlerInterceptor extends
		AnnotationMethodHandlerInterceptorAdapter {

	private static final Integer placeholder = Integer.valueOf(0);

	@Override
	public void preInvoke(Method handlerMethod, Object handler,
			ServletWebRequest webRequest) {
		Cookyjar cookyjar = (Cookyjar) webRequest.getAttribute(
				Cookyjar.CookyjarInRequest, RequestAttributes.SCOPE_REQUEST);
		if (cookyjar == null) {
			throw new IllegalStateException("cookyjar not find in request");
		}
		UserAgent agent = (UserAgent) cookyjar
				.getObject(UserAgent.class);
		if (!pass(agent, handlerMethod, handler)) {
			throw new UserAccessDeniedException();
			// 到异常控制类中去处理
		}
	}

	private Map<Method, String[]> cacheRoles = new ConcurrentHashMap<Method, String[]>();
	private Map<Method, String[]> cacheFunctionss = new ConcurrentHashMap<Method, String[]>();

	private Map<Method, Integer> noControlCaches = new ConcurrentHashMap<Method, Integer>();// 没有配置AdminAccess的method

	private boolean pass(UserAgent user, Method handlerMethod,
			Object handler) {
		String[] funs = this.cacheFunctionss.get(handlerMethod);
		String[] roles = this.cacheRoles.get(handlerMethod);
		if (funs == null && roles==null ) {
			if (noControlCaches.containsKey(handlerMethod)) {
				// 没有AdminAccess 配置，允许任意访问
				return true;
			}
			RoleAccess access = AnnotationUtils.getAnnotation(handlerMethod,
					RoleAccess.class);
			if (access == null) {
				// 没有配置AdminAccess
				noControlCaches.put(handlerMethod, placeholder);
				return true;
			}
			funs = access.function();
			roles = access.role();
			this.cacheFunctionss.put(handlerMethod, funs);
			this.cacheRoles.put(handlerMethod, roles);
		}
		if (funs.length == 0 && roles.length==0) {
			// 如果配置了缺省的UserAccess,表示只要登录就能访问
			return user != null;
		}
		// 配置了UserAccess
		if(user==null) return false;
		//1先检查角色
			/*for (String em : funs) {
				if (user.haveFunction(em)) {
					return true;
				}
			}*/
		//2检查 功能
		
		return false;
	}
}
