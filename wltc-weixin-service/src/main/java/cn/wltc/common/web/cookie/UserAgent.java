package cn.wltc.common.web.cookie;

import java.io.Serializable;

import cn.wltc.framework.web.cookyjar.SelfDependence;
import cn.wltc.framework.web.cookyjar.util.SelfUtil;

public class UserAgent implements Serializable, SelfDependence {
	private static final long serialVersionUID = -3921778874150895446L;
	private String userid;
	private String realName;
	private String role;

	public UserAgent() {
		super();
	}

	public UserAgent(User admin) {
		super();
		this.userid = admin.getUserid();
		this.realName = admin.getRealName();
		this.role = admin.getRole();
	}

	public String lieDown() {
		// return SelfUtil.format(this.loginId, this.functions.toString(36));
		return SelfUtil.format(userid, role, realName);
	}

	public SelfDependence riseUp(String value) {
		String[] values = SelfUtil.recover(value);
		this.userid = values[0];
		this.role = values[1];
		this.realName = values[2];
		// this.functions = new BigInteger(values[1], 36);
		return this;
	}

	public String getUserid() {
		return userid;
	}

	public String getRealName() {
		return realName;
	}

	public String getRole() {
		return role;
	}
}
