package cn.wltc.common.web.cookie;

import java.io.Serializable;
import java.math.BigInteger;

import org.apache.commons.lang.StringUtils;

import cn.wltc.framework.web.cookyjar.SelfDependence;

public class NavAgent implements Serializable, SelfDependence {
	private static final long serialVersionUID = -3921778874150895446L;
	private String top;
	private String group;
	private String menu;
	private BigInteger functions;// 以2进制位纪录管理员用户的权限

	public NavAgent() {
		super();
	}

	/**
	 * 在指定的2进制位上是否有权限
	 * 
	 * @param index
	 * @return
	 */
	public boolean haveFunction(int index) {
		return this.functions.testBit(index);
	}

	public void setFunctions(int pos) {
		if (this.functions == null) {
			this.functions = new BigInteger("0");
		}
		this.functions = this.functions.setBit(pos);
	}

	public String lieDown() {
		return StringUtils.join(new String[] { top, group, menu }, '_');
	}

	public SelfDependence riseUp(String value) {
		String[] values = StringUtils.splitPreserveAllTokens(value, '_');
		this.top = values[0];
		this.group = values[1];
		this.menu = values[2];
		return this;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
}
