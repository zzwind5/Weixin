package cn.wltc.biz.weixin.entity.menu;

public class ClickButton extends Button {
	private String key;
	
	public ClickButton(){
		super.setType(CLICK);
	} 
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}