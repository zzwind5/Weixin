package cn.wltc.framework.web.editor;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.codehaus.jackson.io.NumberInput;

public class AllDateFormat extends DateFormat {
	private static final long serialVersionUID = 1L;
	static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	static final String DATE_FORMAT_STR_ISO8601_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
	static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
	static final String[] ALL_FORMATS = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" , "yyyy-MM", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH","yyyy" };
	static final SimpleDateFormat DATE_FORMAT_RFC1123;
	static final SimpleDateFormat DATE_FORMAT_ISO8601;
	static final SimpleDateFormat DATE_FORMAT_ISO8601_Z;
	static final SimpleDateFormat DATE_FORMAT_PLAIN;
	public static final AllDateFormat instance;
	transient SimpleDateFormat _formatRFC1123;
	transient SimpleDateFormat _formatISO8601;
	transient SimpleDateFormat _formatISO8601_z;
	transient SimpleDateFormat _formatPlain;

	public AllDateFormat clone() {
		return new AllDateFormat();
	}

	public static DateFormat getBlueprintISO8601Format() {
		return DATE_FORMAT_ISO8601;
	}

	public static DateFormat getISO8601Format(TimeZone tz) {
		DateFormat df = (SimpleDateFormat) DATE_FORMAT_ISO8601.clone();
		df.setTimeZone(tz);
		return df;
	}

	public static DateFormat getBlueprintRFC1123Format() {
		return DATE_FORMAT_RFC1123;
	}

	public static DateFormat getRFC1123Format(TimeZone tz) {
		DateFormat df = (SimpleDateFormat) DATE_FORMAT_RFC1123.clone();
		df.setTimeZone(tz);
		return df;
	}

	public Date parse(String dateStr) throws ParseException {
		dateStr = dateStr.trim();
		ParsePosition pos = new ParsePosition(0);
		Date result = parse(dateStr, pos);
		if (result != null) {
			return result;
		}

		StringBuilder sb = new StringBuilder();
		for (String f : ALL_FORMATS) {
			if (sb.length() > 0)
				sb.append("\", \"");
			else {
				sb.append('"');
			}
			sb.append(f);
		}
		sb.append('"');
		throw new ParseException(String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)", new Object[] { dateStr, sb.toString() }), pos.getErrorIndex());
	}

	public Date parse(String dateStr, ParsePosition pos) {
		if(StringUtils.isBlank(dateStr)) return null;
		else
			try {
				return DateUtils.parseDate(dateStr, ALL_FORMATS);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
	}

	public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
		if (this._formatISO8601 == null) {
			this._formatISO8601 = ((SimpleDateFormat) DATE_FORMAT_ISO8601.clone());
		}
		return this._formatISO8601.format(date, toAppendTo, fieldPosition);
	}

	protected boolean looksLikeISO8601(String dateStr) {
		return ((dateStr.length() >= 5) && (Character.isDigit(dateStr.charAt(0))) && (Character.isDigit(dateStr.charAt(3))) && (dateStr.charAt(4) == '-'));
	}

	protected Date parseAsISO8601(String dateStr, ParsePosition pos) {
		int len = dateStr.length();
		char c = dateStr.charAt(len - 1);
		SimpleDateFormat df;
		if ((len <= 10) && (Character.isDigit(c))) {
			df = this._formatPlain;
			if (df == null)
				df = this._formatPlain = (SimpleDateFormat) DATE_FORMAT_PLAIN.clone();
		} else if (c == 'Z') {
			df = this._formatISO8601_z;
			if (df == null) {
				df = this._formatISO8601_z = (SimpleDateFormat) DATE_FORMAT_ISO8601_Z.clone();
			}

		} else if (hasTimeZone(dateStr)) {
			c = dateStr.charAt(len - 3);
			if (c == ':') {
				StringBuilder sb = new StringBuilder(dateStr);
				sb.delete(len - 3, len - 2);
				dateStr = sb.toString();
			} else if ((c == '+') || (c == '-')) {
				dateStr = dateStr + "00";
			}
			df = this._formatISO8601;
			if (this._formatISO8601 == null) {
				df = this._formatISO8601 = (SimpleDateFormat) DATE_FORMAT_ISO8601.clone();
			}

		} else {
			StringBuilder sb = new StringBuilder(dateStr);

			int timeLen = len - dateStr.lastIndexOf(84) - 1;
			if (timeLen <= 8) {
				sb.append(".000");
			}
			sb.append('Z');
			dateStr = sb.toString();
			df = this._formatISO8601_z;
			if (df == null) {
				df = this._formatISO8601_z = (SimpleDateFormat) DATE_FORMAT_ISO8601_Z.clone();
			}
		}

		return df.parse(dateStr, pos);
	}

	protected Date parseAsRFC1123(String dateStr, ParsePosition pos) {
		if (this._formatRFC1123 == null) {
			this._formatRFC1123 = ((SimpleDateFormat) DATE_FORMAT_RFC1123.clone());
		}
		return this._formatRFC1123.parse(dateStr, pos);
	}

	private static final boolean hasTimeZone(String str) {
		int len = str.length();
		if (len >= 6) {
			char c = str.charAt(len - 6);
			if ((c == '+') || (c == '-'))
				return true;
			c = str.charAt(len - 5);
			if ((c == '+') || (c == '-'))
				return true;
			c = str.charAt(len - 3);
			if ((c == '+') || (c == '-'))
				return true;
		}
		return false;
	}

	static {
		TimeZone gmt = TimeZone.getTimeZone("GMT");
		DATE_FORMAT_RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
		DATE_FORMAT_RFC1123.setTimeZone(gmt);
		DATE_FORMAT_ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		DATE_FORMAT_ISO8601.setTimeZone(gmt);
		DATE_FORMAT_ISO8601_Z = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		DATE_FORMAT_ISO8601_Z.setTimeZone(gmt);
		DATE_FORMAT_PLAIN = new SimpleDateFormat("yyyy-MM-dd");
		DATE_FORMAT_PLAIN.setTimeZone(gmt);

		instance = new AllDateFormat();
	}
}
