package cn.wltc.biz.weixin.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SMSUtil {
	private static final String SMS_USER_NAME = "wltchz";
	private static final String SMS_PASSWORD = "3920f2c57f94fdc3c6e4";

	public static boolean sendSMS(String mobile, String content) {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
		try {
			post.addRequestHeader("Content-Type",
					"application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
			NameValuePair[] data = { new NameValuePair("Uid", SMS_USER_NAME),
					new NameValuePair("Key", SMS_PASSWORD),
					new NameValuePair("smsMob", mobile),
					new NameValuePair("smsText", content) };
			post.setRequestBody(data);
			client.executeMethod(post);
			int statusCode = post.getStatusCode();
			if (statusCode <= 0) {
				return false;
			}
		} catch (Exception ex) {
			return false;
		} finally {
			post.releaseConnection();
		}
		return true;
	}
}
