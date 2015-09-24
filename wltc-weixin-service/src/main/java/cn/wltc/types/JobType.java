package cn.wltc.types;

public enum JobType {
	YZ("1","院长"),
	JL("2","教练"),
	;
	private String key;
	private String value;
	
	private JobType(String key,String value){
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
		for (JobType type : JobType.values()){
			if (type.key.equals(key)){
				return type.value;
			}
		}
		return null;
	}
}
