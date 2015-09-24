package cn.wltc.biz.weixin.entity.menu;

public class Button {
	private String name;
	private String type;
	protected final String VIEW = "view";
	protected final String CLICK = "click";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
