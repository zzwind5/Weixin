package cn.wltc.framework.util.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetMacAddress {

	private static final String regExpWin = "((([0-9,A-F,a-f]{1,2}" + "-"
			+ "){1,5})[0-9,A-F,a-f]{1,2})";
	private static final String regExpLinux = "((([0-9,A-F,a-f]{1,2}" + ":"
			+ "){1,5})[0-9,A-F,a-f]{1,2})";
	private static Pattern patternWin = null;
	private static Pattern patternLinux = null;
	static {
		patternWin = Pattern.compile(regExpWin);
		patternLinux = Pattern.compile(regExpLinux);
	}

	public static String callCmd(String[] cmd,String ip) {
		//String result = "";
		String line = "";
		try {
			long t1  = System.currentTimeMillis();
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStreamReader is = new InputStreamReader(proc.getInputStream());
			BufferedReader br = new BufferedReader(is);
			while ((line = br.readLine()) != null) {
				//result += line;
//				if (line.contains(ip)){
//					return line;
//				}
			}
			System.out.println(System.currentTimeMillis() - t1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String callCmd(String[] cmd, String[] another,String ip) {
		String result = "";
		String line = "";
		try {
			long t1  = System.currentTimeMillis();
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd);
			proc.waitFor(); //
			proc = rt.exec(another);
			InputStreamReader is = new InputStreamReader(proc.getInputStream());
			BufferedReader br = new BufferedReader(is);
			while ((line = br.readLine()) != null) {
				result += line;
//				if (line.contains(ip)){
//					//return line;
//				}				
			}
			System.out.println(System.currentTimeMillis() - t1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String filterMacAddress(final String ip,
			final String sourceString, final String macSeparator) {
		String result = "";
		// String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator
		// + "){1,5})[0-9,A-F,a-f]{1,2})";
		// Pattern pattern = Pattern.compile(regExp);

		Matcher matcher = null;
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("windows")) {
			matcher = patternWin.matcher(sourceString);
		} else {
			matcher = patternLinux.matcher(sourceString);
		}
		while (matcher != null && matcher.find()) {
			result = matcher.group(1);
			if (sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher
					.group(1))) {
				break; 
			}
		}

		return result;
	}

	public static String getMacInWindows(final String ip) {
		String result = "";
		String[] cmd = { "cmd", "/c", "ping " + ip };
		String[] another = { "cmd", "/c", "arp -a" };

		String cmdResult = callCmd(cmd, another,ip);
		result = filterMacAddress(ip, cmdResult, "-");

		return result;
	}

	public static String getMacInLinux(final String ip) {
		String result = "";
		String[] cmd = { "/bin/sh", "-c", "ping " + ip + " -c 2" };
		String[] another = { "/bin/sh", "-c", "arp -a" };
		String cmdResult = callCmd(cmd,another,ip);
		result = filterMacAddress(ip, cmdResult, ":");

		return result;
	}

	public static String getMacAddress(String ip) {
		String macAddress = "";
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("windows")) {
			macAddress = getMacInWindows(ip);
		} else {
			macAddress = getMacInLinux(ip);
		}
		return macAddress;
	}

	public static void main(String[] args) {
		System.out.println(getMacAddress(args[0]));
	}
}
