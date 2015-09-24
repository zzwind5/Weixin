package cn.wltc.types;

public enum CustomerType {

	PTKH("0","普通客户"),
	ZXKH("1","专线客户"),
	;
	private String key;
	private String value;
	
	private CustomerType(String key,String value){
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
		for (CustomerType type : CustomerType.values()){
			if (type.key.equals(key)){
				return type.value;
			}
		}
		return null;
	}

}
