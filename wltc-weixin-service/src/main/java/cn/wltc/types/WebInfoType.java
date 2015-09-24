package cn.wltc.types;

public enum WebInfoType {
	QYJS(1,"棋院介绍"),
	YZJS(2,"院长介绍"),
	YZZC(3,"院长致辞"),
	JZXZ(4,"家长须知"),
	QYLN(5,"棋院理念"),
	JLSZ(6,"教练守则"),
	XYSZ(7,"学员守则"),
	SDSF(8,"师德师风"),
	RYQ(9,"荣誉墙"),
	;
	
	private int key;
	private String value;
	
	private WebInfoType(int key,String value){
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String getValueByKey(int key){
		for (WebInfoType type : WebInfoType.values()){
			if (type.key == key){
				return type.value;
			}
		}
		return null;
	}
}
