package cn.wltc.biz.wxmgr.model;

import java.util.Date;

import cn.wltc.biz.customerMgr.model.CustomerInfo;
import cn.wltc.framework.service.BaseQuery;

/**
 * 优惠券
 * @author qupenghui
 *
 */
public class Coupon extends BaseQuery {

	private static final long serialVersionUID = -2421248859131455347L;

	private Integer id;
	private Integer activityId;
	private CustomerInfo customer;
	private String code;
	private String status;
	private String getTime;
	private String consumeTime;
	private String overdueTime;
	private String serviceDealStatus;
	private String lineDealStatus;
	private Integer customerId;
	private String lineDealComment;
	private Date lineDealTime;
	private String cargo_name;
	
	
    public String getCargo_name() {
        return cargo_name;
    }
    
    public void setCargo_name(String cargo_name) {
        this.cargo_name = cargo_name;
    }
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public CustomerInfo getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerInfo customer) {
		this.customer = customer;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGetTime() {
		return getTime;
	}
	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}
	public String getConsumeTime() {
		return consumeTime;
	}
	public void setConsumeTime(String consumeTime) {
		this.consumeTime = consumeTime;
	}
	public String getOverdueTime() {
		return overdueTime;
	}
	public void setOverdueTime(String overdueTime) {
		this.overdueTime = overdueTime;
	}
	public String getServiceDealStatus() {
		return serviceDealStatus;
	}
	public void setServiceDealStatus(String serviceDealStatus) {
		this.serviceDealStatus = serviceDealStatus;
	}
	public String getLineDealStatus() {
		return lineDealStatus;
	}
	public void setLineDealStatus(String lineDealStatus) {
		this.lineDealStatus = lineDealStatus;
	}
    
    public Integer getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
	public String getLineDealComment() {
		return lineDealComment;
	}
	public void setLineDealComment(String lineDealComment) {
		this.lineDealComment = lineDealComment;
	}
	public Date getLineDealTime() {
		return lineDealTime;
	}
	public void setLineDealTime(Date lineDealTime) {
		this.lineDealTime = lineDealTime;
	}
	
	
}
