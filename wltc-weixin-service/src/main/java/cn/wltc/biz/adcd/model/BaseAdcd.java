package cn.wltc.biz.adcd.model;

import cn.wltc.framework.service.BaseQuery;


public class BaseAdcd extends BaseQuery  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String adcd;
	public String adnm;
	public String adnm_short;
	public String pinyin;
	public String pinyin_short;
	public String lng;
	public String lat; 
	public Integer level;
	public String govcd;
	public String govnm;
	public String hot;
	public Integer maplevel;
	public Integer hide;
    
    public String getAdcd() {
        return adcd;
    }
    
    public void setAdcd(String adcd) {
        this.adcd = adcd;
    }
    
    public String getAdnm() {
        return adnm;
    }
    
    public void setAdnm(String adnm) {
        this.adnm = adnm;
    }
    
    public String getAdnm_short() {
        return adnm_short;
    }
    
    public void setAdnm_short(String adnm_short) {
        this.adnm_short = adnm_short;
    }
    
    public String getPinyin() {
        return pinyin;
    }
    
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
    
    public String getPinyin_short() {
        return pinyin_short;
    }
    
    public void setPinyin_short(String pinyin_short) {
        this.pinyin_short = pinyin_short;
    }
    
    public String getLng() {
        return lng;
    }
    
    public void setLng(String lng) {
        this.lng = lng;
    }
    
    public String getLat() {
        return lat;
    }
    
    public void setLat(String lat) {
        this.lat = lat;
    }
    
    public Integer getLevel() {
        return level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }
    
    public String getGovcd() {
        return govcd;
    }
    
    public void setGovcd(String govcd) {
        this.govcd = govcd;
    }
    
    public String getGovnm() {
        return govnm;
    }
    
    public void setGovnm(String govnm) {
        this.govnm = govnm;
    }
    
    public String getHot() {
        return hot;
    }
    
    public void setHot(String hot) {
        this.hot = hot;
    }
    
    public Integer getMaplevel() {
        return maplevel;
    }
    
    public void setMaplevel(Integer maplevel) {
        this.maplevel = maplevel;
    }
    
    public Integer getHide() {
        return hide;
    }
    
    public void setHide(Integer hide) {
        this.hide = hide;
    }
	
	
}
