package cn.wltc.biz.weixin.entity.test;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.wltc.framework.service.BaseEntity;

/**
 * @author Chris
 * @version 1.0
 * @since 1.0
 */

/**TestUser数据模型
 * @author Chris
 */
public class TestUser extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "TestUser";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERNAME = "用户姓名";
	public static final String ALIAS_REALNAME = "真实姓名";
	public static final String ALIAS_LOGINNAME = "登录姓名";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	/** id*/
	
	private java.lang.Integer id;
	/** 用户姓名*/
	private java.lang.String username;
	/** 真实姓名*/
	private java.lang.String realname;
	/** 登录姓名*/
	private java.lang.String loginname;
	//columns END

	public TestUser(){
	}

	public TestUser(
		java.lang.Integer id
	){
		this.id = id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	public void setRealname(java.lang.String value) {
		this.realname = value;
	}
	
	public java.lang.String getRealname() {
		return this.realname;
	}
	public void setLoginname(java.lang.String value) {
		this.loginname = value;
	}
	
	public java.lang.String getLoginname() {
		return this.loginname;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Username",getUsername())
			.append("Realname",getRealname())
			.append("Loginname",getLoginname())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TestUser == false) return false;
		if(this == obj) return true;
		TestUser other = (TestUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	public TestUser clone(){
		TestUser newobj=new TestUser();
			 newobj.setId(this.getId());
			 newobj.setUsername(this.getUsername());
			 newobj.setRealname(this.getRealname());
			 newobj.setLoginname(this.getLoginname());
		return newobj;
	}
}

