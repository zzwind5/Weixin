package cn.wltc.framework.util.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class IPRequest {

	// 获取客户端IP地址，支持代理服务器
	public static String getIpAddress(HttpServletRequest request) {

		if (request == null){
			 try {
				return InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
				return "unknown";
			}
		}
		String ip = request.getHeader("x-forwarded-for");

		String localIP = "127.0.0.1";

		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

}
