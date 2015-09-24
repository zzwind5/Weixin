package cn.wltc.biz.wxmgr.model;

import cn.wltc.framework.service.BaseQuery;

public class UserInfo extends BaseQuery {

	private static final long serialVersionUID = -9120616257557397728L;
	private Integer uid = null; // int(11) NOT NULL AUTO_INCREMENT
	private Integer org_id = null; // int(11) NOT NULL '企业id'
	private String login_name = null; // varchar(20) DEFAULT NULL '用户名'
	private String nick = null; // varchar(10) DEFAULT NULL '昵称/真实姓名'
	private String avatar = null; // varchar(100) DEFAULT NULL '头像(地址)'
	private String mobile = null; // char(11) DEFAULT NULL '手机'
	private String email = null; // varchar(50) DEFAULT NULL '邮箱地址'
	private String county_code = null; // char(6) DEFAULT NULL '详细-行政区编码'
	private String county = null; // varchar(20) DEFAULT NULL '详细-行政区'
	private String address = null; // varchar(100) DEFAULT NULL '详细-详细地址'
	private String sex = null; // char(1) DEFAULT NULL '详细-性别'

	public UserInfo() {
		super();
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getOrg_id() {
		return org_id;
	}

	public void setOrg_id(Integer org_id) {
		this.org_id = org_id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCounty_code() {
		return county_code;
	}

	public void setCounty_code(String county_code) {
		this.county_code = county_code;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
