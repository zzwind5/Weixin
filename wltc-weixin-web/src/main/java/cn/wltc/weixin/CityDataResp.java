/**
 * 
 */
package cn.wltc.weixin;

/**
 * @author Chris
 *
 */
public class CityDataResp {
	public static final CityDataResp Success = new  CityDataResp(true,null,null,null);
	public static final CityDataResp Fail = new  CityDataResp(false,null,null,null);
	private boolean result;
	private String error;
	private String msg;
	private Object cityData;
	
	/**
	 * @param result
	 * @param error
	 * @param msg
	 * @param data
	 */
	public CityDataResp(boolean result, String error, String msg, Object data) {
		super();
		this.result = result;
		this.error = error;
		this.msg = msg;
		this.cityData = data;
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

    public Object getCityData() {
        return cityData;
    }
    
    public void setCityData(Object cityData) {
        this.cityData = cityData;
    }
}
