package cn.wltc.common.web.access;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * administer权限控制,缺省值为登录用户
 * demo假设权限投票都为'或'逻辑
 * 
 * @author Chris
 * 
 */
@Target( { ElementType.METHOD, ElementType.TYPE, ElementType.PACKAGE })
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleAccess {
	String[] role() default {};
	String[] function() default {};
}