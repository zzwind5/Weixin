package cn.wltc.biz.wxmgr.model;

import java.util.Date;

import cn.wltc.framework.service.BaseQuery;

public class ActivityAuth extends BaseQuery {

	private static final long serialVersionUID = -2676009903198320969L;
	private Integer activity_auth_id = null; // int(11) NOT NULL '优惠活动审核ID'
	private Integer activity_id = null; // int(11) DEFAULT NULL '活动ID'
	private Integer line_id = null; // int(11) DEFAULT NULL '专线ID'
	private Integer org_id = null; // int(11) DEFAULT NULL '组织ID'
	private String apply_man = null; // varchar(10) DEFAULT NULL '申请人'
	private Date apply_time = null; // datetime DEFAULT NULL '申请时间'
	private String apply_note = null; // varchar(200) DEFAULT NULL '申请备注'
	private String audit_man = null; // varchar(10) DEFAULT NULL '审核人'
	private Date audit_time = null; // datetime DEFAULT NULL '审核时间'
	private String audit_note = null; // varchar(200) DEFAULT NULL '审核意见'
	private String status = null; // char(1) DEFAULT NULL '状态'
	private String source = null; // char(1) DEFAULT NULL '来源： 0：平台 1：微信 2：App'

	public ActivityAuth() {
		super();
	}

	public Integer getActivity_auth_id() {
		return activity_auth_id;
	}

	public void setActivity_auth_id(Integer activity_auth_id) {
		this.activity_auth_id = activity_auth_id;
	}

	public Integer getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Integer activity_id) {
		this.activity_id = activity_id;
	}

	public Integer getLine_id() {
		return line_id;
	}

	public void setLine_id(Integer line_id) {
		this.line_id = line_id;
	}

	public Integer getOrg_id() {
		return org_id;
	}

	public void setOrg_id(Integer org_id) {
		this.org_id = org_id;
	}

	public String getApply_man() {
		return apply_man;
	}

	public void setApply_man(String apply_man) {
		this.apply_man = apply_man;
	}

	public Date getApply_time() {
		return apply_time;
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}

	public String getApply_note() {
		return apply_note;
	}

	public void setApply_note(String apply_note) {
		this.apply_note = apply_note;
	}

	public String getAudit_man() {
		return audit_man;
	}

	public void setAudit_man(String audit_man) {
		this.audit_man = audit_man;
	}

	public Date getAudit_time() {
		return audit_time;
	}

	public void setAudit_time(Date audit_time) {
		this.audit_time = audit_time;
	}

	public String getAudit_note() {
		return audit_note;
	}

	public void setAudit_note(String audit_note) {
		this.audit_note = audit_note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
