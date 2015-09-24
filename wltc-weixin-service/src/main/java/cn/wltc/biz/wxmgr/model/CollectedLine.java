package cn.wltc.biz.wxmgr.model;

import java.util.Date;

import cn.wltc.framework.service.BaseQuery;

public class CollectedLine extends BaseQuery {
	private static final long serialVersionUID = -3096365126884766772L;
	private Integer customerId;
	private Integer lineId;
	private Date collectTime;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public Date getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

}
