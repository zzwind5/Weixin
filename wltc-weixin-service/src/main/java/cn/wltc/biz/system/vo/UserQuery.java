package cn.wltc.biz.system.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.wltc.biz.system.model.SysUserInfo;


public class UserQuery extends SysUserInfo {
   
    private static final long serialVersionUID = 712091348505279543L;

    private String isUserInRole;
    
    private Integer userRoleId;

    public Integer getUserRoleId() {
        return userRoleId;
    }
    
    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getIsUserInRole() {
        return isUserInRole;
    }

    public void setIsUserInRole(String isUserInRole) {
        this.isUserInRole = isUserInRole;
    }

    public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

