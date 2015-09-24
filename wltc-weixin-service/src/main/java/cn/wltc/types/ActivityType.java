package cn.wltc.types;

public enum ActivityType {
	XJYH("0","现金优惠"),
	ZKYH("1","折扣优惠"),
	JMYH("2","减免优惠"),
	;
	private String key;
	private String value;
	
	private ActivityType(String key,String value){
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String getValue(String key){
		for (ActivityType type : ActivityType.values()){
			if (type.key.equals(key)){
				return type.value;
			}
		}
		return null;
	}
}
