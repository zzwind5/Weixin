package cn.wltc.framework.util;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.wltc.framework.util.html.parse.HTMLParser;

/**
 * @author fish
 * @version $Id: HtmlUtils.java,v 1.2 2009/05/22 06:20:07 fish Exp $
 */
public final class HtmlUtils {

	private static final Log logger = LogFactory.getLog(HtmlUtils.class);

	private static final Pattern scriptTag = Pattern
			.compile("(?i)(?s)(<script.+?</script>)|(on.+?=)");

	private static final String emptyString = "";

	/**
	 * ȥ����ֹ�����html��ǩ,����˵script��
	 * 
	 * @param s
	 * @return
	 */
	public static final String escapeForbiddenHtml(String s) {
		return escapeScriptTag(s);
	}

	/**
	 * ȥ����ֹ�����script��ǩ,�Լ� onclick����ĺ���
	 * 
	 * @param s
	 * @return
	 */
	public static final String escapeScriptTag(String s) {
		if (s == null) {
			return null;
		}
		Matcher m = scriptTag.matcher(s);
		return m.replaceAll(emptyString);
	}

	/**
	 * �õ�ȥ��html��ǩ�������
	 * 
	 * @param s
	 * @return
	 */
	public static final String parseHtml(String s) {
		if (s == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		try {
			Reader r = new StringReader(s);
			HTMLParser parser = new HTMLParser(r);
			LineNumberReader reader = new LineNumberReader(parser.getReader());
			for (String l = reader.readLine(); l != null; l = reader.readLine()) {
				sb.append(l);
			}
			r.close();
		} catch (IOException e) {
			logger.error("error than parse html string", e);
		}
		return sb.toString();
	}

	/**
	 * �õ�ȥ��html��ǩ�������
	 * 
	 * @param s
	 * @param maxLength
	 *            ��󳤶�,
	 * @return s.length <= maxLength
	 */
	public static final String parseHtml(String s, int maxLength) {
		if (maxLength <= 0) {
			throw new java.lang.IllegalArgumentException(
					"maxLength must greate than zero");
		}
		if (s == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		try {
			Reader r = new StringReader(s);
			HTMLParser parser = new HTMLParser(r);
			LineNumberReader reader = new LineNumberReader(parser.getReader());
			for (String l = reader.readLine(); l != null; l = reader.readLine()) {
				sb.append(l);
				if (sb.length() >= maxLength) {
					break;
				}
			}
			r.close();
		} catch (IOException e) {
			logger.error("error than parse html string", e);
		}
		if (sb.length() >= maxLength) {
			return sb.substring(0, maxLength);
		}
		return sb.toString();
	}
}
