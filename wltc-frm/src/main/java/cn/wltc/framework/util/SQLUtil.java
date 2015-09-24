/**
 * 
 */
package cn.wltc.framework.util;

public class SQLUtil {
	public static String transer(String keyword) {
		if (keyword == null || keyword.length() == 0){
		    return null;
		}
		keyword = keyword.trim();
		if (keyword.contains("'") || keyword.contains("_")
				|| keyword.contains("\\") || keyword.contains("%")) {
		    keyword = keyword.replaceAll("'", "\\'").replaceAll("\\\\", "\\\\\\\\")
					.replaceAll("\\_", "\\\\_").replaceAll("%", "\\%");
		}
		return keyword;
	}

	public static String truncate(String keyword) {
		if (keyword == null)
			return null;
		keyword = keyword.trim();
		if (keyword.contains("'") || keyword.contains("_")
				|| keyword.contains("\\") || keyword.contains("%")) {
			keyword = keyword.replaceAll("'", "").replaceAll("\\\\", "")
					.replaceAll("_", "").replaceAll("%", "");
		}
		return keyword;
	}
}
