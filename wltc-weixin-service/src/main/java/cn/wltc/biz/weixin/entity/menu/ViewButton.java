package cn.wltc.biz.weixin.entity.menu;

public class ViewButton extends Button {
	private String url;

	public ViewButton(){
		super.setType(VIEW);
	} 
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
