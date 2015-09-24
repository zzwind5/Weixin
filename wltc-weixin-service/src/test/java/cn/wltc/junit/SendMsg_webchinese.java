package cn.wltc.junit;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMsg_webchinese {
	private static final String SMS_USER_NAME = "wltchz";
	private static final String SMS_PASSWORD = "3920f2c57f94fdc3c6e4";
	public static void main(String[] args) throws Exception {

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
		post.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", SMS_USER_NAME),
				new NameValuePair("Key", SMS_PASSWORD),
				new NameValuePair("smsMob", "13588741060"),
				new NameValuePair("smsText", "您的验证码为1234") };
		post.setRequestBody(data);

		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes(
				"gbk"));
		System.out.println(result); // 打印返回消息状态

		post.releaseConnection();

	}
}
