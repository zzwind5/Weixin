package cn.wltc.biz.weixin.util;

import java.security.MessageDigest;
import java.util.Arrays;

public class SignUtil {

	private static String token = "56tchzdev01";
	
	public static boolean checkSignature(String signature, String timeStamp, String nonce){
		String[] paramArr = new String[]{ token,timeStamp,nonce};
		Arrays.sort(paramArr);
		String content = paramArr[0].concat(paramArr[1].concat(paramArr[2]));
		String ciphertext = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.toString().getBytes());
			ciphertext = byteToString(digest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
	}
	
	private static String byteToString(byte[] byteArray){
		String strDigestr = "";
		for(int i = 0; i < byteArray.length; i++){
			strDigestr += byteToHexStr(byteArray[i]);			
		}
		
		return strDigestr;
	}
	
	public static String byteToHexStr(byte mByte) {
		char[] Digit = { '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		
		return new String(tempArr);		
	}
}
