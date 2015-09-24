package cn.wltc.biz.system.model;

import java.util.Date;

import cn.wltc.framework.service.BaseQuery;

public class Notices extends BaseQuery{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4357595512119645641L;
	private Integer id;
	private String bt;
	private String nr;
	private String lx;
	private String sfzd;
	private String lb;
	private Date fbsj;
	private String fbr;
	private String sfsc;
	//////////////////////////////////////////////
	private String[] lxs;
	private String firstLink;
	
	public String getFirstLink() {
        return firstLink;
    }
    public void setFirstLink(String firstLink) {
        this.firstLink = firstLink;
    }
    public String[] getLxs() {
		return lxs;
	}
	public void setLxs(String[] lxs) {
		this.lxs = lxs;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getSfzd() {
		return sfzd;
	}
	public void setSfzd(String sfzd) {
		this.sfzd = sfzd;
	}
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public Date getFbsj() {
		return fbsj;
	}
	public void setFbsj(Date fbsj) {
		this.fbsj = fbsj;
	}
	public String getFbr() {
		return fbr;
	}
	public void setFbr(String fbr) {
		this.fbr = fbr;
	}
	public String getSfsc() {
		return sfsc;
	}
	public void setSfsc(String sfsc) {
		this.sfsc = sfsc;
	}
}
