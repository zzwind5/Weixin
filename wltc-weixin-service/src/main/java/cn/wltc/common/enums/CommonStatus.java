package cn.wltc.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用记录状态
 * 
 * @author Administrator
 *
 */
public class CommonStatus{
	/** 直营 */
	public static final String NEW = "0";
	/** 加盟, */
	public static final String VALID = "1";
	/** 禁用 */
	public static final String DISABLED = "8";
	/** 删除 */
	public static final String DELETED = "9";

	protected static final Map<String, String> itemMap = new HashMap<String, String>();
	
	

	static {
		itemMap.put(NEW, "新增加");
		itemMap.put(VALID, "可查询");
		itemMap.put(DISABLED, "已禁用");
		itemMap.put(DELETED, "已删除");
	}

	public Map<String, String> getItems() {
		return itemMap;
	}

	public String text(String key) {
		return itemMap.get(key);
	}

	public static String getText2(String key) {
		return itemMap.get(key);
	}

	public static Map<String, String> getItemMap2() {
		return itemMap;
	}

}
