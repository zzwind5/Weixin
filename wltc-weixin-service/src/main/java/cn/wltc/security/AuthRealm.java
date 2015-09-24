package cn.wltc.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import cn.wltc.biz.system.model.SysAuthResourceInfo;
import cn.wltc.biz.system.model.SysRoleInfo;
import cn.wltc.biz.system.model.SysUserInfo;
import cn.wltc.biz.system.service.SysAuthResourceInfoService;
import cn.wltc.biz.system.service.SysRoleInfoService;
import cn.wltc.biz.system.service.SysUserInfoService;


public class AuthRealm extends AuthorizingRealm {

    private static Log               log       = LogFactory.getLog(AuthRealm.class);
    
	@Autowired
	private SysUserInfoService userService;
	
    @Autowired
    private SysAuthResourceInfoService resourceService;
    
    @Autowired
    private SysRoleInfoService roleService;   

	// 验证当前Subject（可理解为当前用户）所拥有的权限，且给其授权。
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("Principal对象不能为空");
		}
		
		// 获取当前登录的用户名
		SysUserInfo yhxxb =  (SysUserInfo) principals.getPrimaryPrincipal();
		
		List<SysRoleInfo>  roleList = roleService.findAllByYhid(yhxxb.getId());
		//用户角色
        Set<String> permissions = new HashSet<String>();
        Set<String> roles = new HashSet<String>();
		for (SysRoleInfo role : roleList) {
			roles.add(role.getJsdm());
		}
        List<SysAuthResourceInfo> resourceList = resourceService.findUserResulesByTypes(yhxxb.getId(),Arrays.asList("1","2")); // 页面 按钮资源

		//用户权限
		for (SysAuthResourceInfo resource : resourceList) {
			if (StringUtils.hasText(resource.getQxzybz())){
			    permissions.add(resource.getQxzybz());
			}
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 给当前用户设置角色
		info.addRoles(roles);
		// 给当前用户设置权限
		info.addStringPermissions(permissions);
		return info;
	}

	// 认证回调函数,登录时调用.
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();

		if (!StringUtils.hasText(userName)) {
			throw new AccountException("用户名不能为空！");
		}

		SysUserInfo user = null;
		
        /**
         * 为什么这里捕捉异常 shiro 对任何登陆时的异常都定义 AccountException 导致看不到原始异常
         */
        try {
            user = userService.getByYhm(userName);
        } catch (Exception ex) {
            log.error("登陆时刻异常", ex);
        }
		
		if (user == null) {
			throw new UnknownAccountException("用户不存在");
		}
		
		if (!"1".equals(user.getYhzt())){//账号停用
		    throw new DisabledAccountException();
		}
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				user, user.getKl(), user.getYhm());
		return info;
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
}
