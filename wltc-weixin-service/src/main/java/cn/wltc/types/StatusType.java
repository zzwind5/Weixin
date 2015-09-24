package cn.wltc.types;

public enum StatusType {


	WGZ("0","未关注"),
	GZ("1","关注"),
	;
	private String key;
	private String value;
	
	private StatusType(String key,String value){
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
		for (StatusType type : StatusType.values()){
			if (type.key.equals(key)){
				return type.value;
			}
		}
		return null;
	}


}
