package cn.wltc.types;

public enum NoticesType {

	WZTZ("1","网站通知"),
	SSGG("2","赛事公告"),
	SHDT("3","山海动态"),
	SHWH("4","山海文化"),
	;
	
	private String key;
	private String value;
	
	private NoticesType(String key,String value){
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
	
	public static String getValueByKey(String key){
		for (NoticesType type : NoticesType.values()){
			if (type.key.equals(key)){
				return type.value;
			}
		}
		return null;
	}
}
