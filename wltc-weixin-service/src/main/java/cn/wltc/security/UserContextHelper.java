package cn.wltc.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;

import cn.wltc.biz.system.model.SysUserInfo;

public class UserContextHelper {

    public static SysUserInfo getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        return (SysUserInfo) currentUser.getPrincipal();
    }

    public static String getCurrentYhm() {
        return getCurrentUser().getYhm();
    }

    public static void clearCacheIfAvailable() {
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        AuthRealm userRealm = (AuthRealm) securityManager.getRealms().iterator().next();
        if (userRealm.getAuthorizationCache() != null) {
            userRealm.getAuthorizationCache().remove(SecurityUtils.getSubject().getPrincipals());
        }
        if (userRealm.getAuthenticationCache() != null) {
            userRealm.getAuthenticationCache().remove(SecurityUtils.getSubject().getPrincipals());
        }        
    }

}
