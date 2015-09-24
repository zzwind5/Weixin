package cn.wltc.types;

public enum TrainProject {
	XIANGQI("1","象棋"),
	WEIQI("2","围棋"),
	GUOXIANG("3","国象"),
	GUOTIAO("4","国跳"),
	;
	private String key;
	private String value;
	
	private TrainProject(String key,String value){
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
		for (TrainProject type : TrainProject.values()){
			if (type.key.equals(key)){
				return type.value;
			}
		}
		return null;
	}
}
