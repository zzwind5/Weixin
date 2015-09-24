package cn.wltc.framework.util.crypto;

/**
 * @author fish
 * 
 */
public interface Crypto {

	public enum Encoding {
		Base64, Base32
	}
	
	public String encrypt(String s);

	public String encrypt(String s, Encoding en);

	public String encrypt(String s, String charset);

	public String encrypt(String s, Encoding en, String charset);

	public String dectypt(String s);

	public String dectypt(String s, Encoding en);
	
	public String dectypt(String s, String encoding);

	public String dectypt(String s, Encoding en, String encoding);

	public byte[] encrypt(byte[] bytes);

	public byte[] dectypt(byte[] bytes);

}
