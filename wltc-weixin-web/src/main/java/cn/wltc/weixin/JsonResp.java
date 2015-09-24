/**
 * 
 */
package cn.wltc.weixin;

/**
 * @author Chris
 *
 */
public class JsonResp {
	public static final JsonResp Success = new  JsonResp(true,null,null,null);
	public static final JsonResp Fail = new  JsonResp(false,null,null,null);
	private boolean result;
	private String error;
	private String msg;
	private Object data;
	
	/**
	 * @param result
	 * @param error
	 * @param msg
	 * @param data
	 */
	public JsonResp(boolean result, String error, String msg, Object data) {
		super();
		this.result = result;
		this.error = error;
		this.msg = msg;
		this.data = data;
	}

	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
