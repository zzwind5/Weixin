package cn.wltc.types;

public enum IsNotType {
	YES("1","是"),
	NO("0","否"),
	;
	private String key;
	private String value;
	
	private IsNotType(String key,String value){
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
		for (IsNotType type : IsNotType.values()){
			if (type.key.equals(key)){
				return type.value;
			}
		}
		return null;
	}
}
