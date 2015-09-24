package cn.wltc.types;

public enum DkbjType {
	DK("1","到课"),
	QK("2","缺课"),
	QJ("3","请假"),
	;
	private String key;
	private String value;
	
	private DkbjType(String key,String value){
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
		for (DkbjType type : DkbjType.values()){
			if (type.key.equals(key)){
				return type.value;
			}
		}
		return null;
	}
}
