package cn.wltc.types;

public enum SkbxType {
	YX("1","优秀"),
	LH("2","良好"),
	YB("3","一般"),
	JC("4","较差"),
	;
	private String key;
	private String value;
	
	private SkbxType(String key,String value){
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
		for (SkbxType type : SkbxType.values()){
			if (type.key.equals(key)){
				return type.value;
			}
		}
		return null;
	}
}
