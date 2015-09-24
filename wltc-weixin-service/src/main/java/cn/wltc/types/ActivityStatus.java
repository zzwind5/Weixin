package cn.wltc.types;

public enum ActivityStatus {
	DSH("0","待审核"),
	WFB("1","未发布"),
	YFB("2","已发布"),
	;
	private String key;
	private String value;
	
	private ActivityStatus(String key,String value){
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
		for (ActivityStatus type : ActivityStatus.values()){
			if (type.key.equals(key)){
				return type.value;
			}
		}
		return null;
	}
}
