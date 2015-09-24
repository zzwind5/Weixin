package cn.wltc.biz.base.model;

import java.util.Date;


public class UserFile {

	private Integer id;
	private String fjmc;
	private String fjlj;
	private Date scsj;
	private String scr;
	private String sfsc;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFjmc() {
		return fjmc;
	}
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	public String getFjlj() {
		return fjlj;
	}
	public void setFjlj(String fjlj) {
		this.fjlj = fjlj;
	}
	public Date getScsj() {
		return scsj;
	}
	public void setScsj(Date scsj) {
		this.scsj = scsj;
	}
	public String getScr() {
		return scr;
	}
	public void setScr(String scr) {
		this.scr = scr;
	}
	public String getSfsc() {
		return sfsc;
	}
	public void setSfsc(String sfsc) {
		this.sfsc = sfsc;
	}
	
}
