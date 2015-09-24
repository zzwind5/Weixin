package cn.wltc.common.web.access;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.tools.view.context.ViewContext;

import cn.wltc.common.web.cookie.NavAgent;
import cn.wltc.common.web.cookie.UserAgent;
import cn.wltc.framework.web.cookyjar.Cookyjar;

public class UserAccessVMTool {

	private UserAgent agent;
	private NavAgent nav;

	public void init(Object obj) {
		if (!(obj instanceof ViewContext)) {
			throw new IllegalArgumentException("Tool can only be initialized with a ViewContext");
		}
		ViewContext viewContext = (ViewContext) obj;
		HttpServletRequest request = viewContext.getRequest();
		Cookyjar cookyjar = (Cookyjar) request.getAttribute(Cookyjar.CookyjarInRequest);
		if (cookyjar == null) {
			throw new IllegalStateException("Cookyjar not find in HttpServletRequest");
		}
		agent = (UserAgent) cookyjar.getObject(UserAgent.class);
		nav = (NavAgent) cookyjar.getObject(NavAgent.class);
	}

	public UserAgent getUser() {
		return agent;
	}

	public NavAgent getNav() {
		return nav;
	}

	public boolean has(String funcationName) {
		if (funcationName == null) {
			throw new IllegalArgumentException("unknow function name:" + funcationName);
		}
		return true;// this.agent.haveFunction(en);
	}

}
