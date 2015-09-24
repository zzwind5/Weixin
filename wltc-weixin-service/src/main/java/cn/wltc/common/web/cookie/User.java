package cn.wltc.common.web.cookie;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class User implements Serializable {
	private static final long serialVersionUID = -4924645412681855140L;
	private String userid;
	private String username;
	private String password;
	private String realName;
	private String role;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		if (StringUtils.isBlank(realName))
			return realName;
		else
			return this.username;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
