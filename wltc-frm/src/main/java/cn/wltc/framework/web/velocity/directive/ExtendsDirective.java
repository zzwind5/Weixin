package cn.wltc.framework.web.velocity.directive;

import org.apache.velocity.runtime.directive.Parse;

/**
 * @author badqiu
 */
public class ExtendsDirective extends Parse{
	@Override
	public String getName() {
		return "extends";
	}
	
}
