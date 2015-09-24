package cn.wltc.biz.wxmgr.model;

import java.util.Date;

import cn.wltc.biz.customerMgr.model.CustomerInfo;
import cn.wltc.framework.service.BaseQuery;

/**
 * 优惠活动
 * 
 * @author qupenghui
 */
public class Activity extends BaseQuery {

    private static final long serialVersionUID = -3096365126884766772L;

    private Integer           id;
    private String            name;
    private String            type;
    private String            beginTime;
    private String            endTime;
    private String            content;
    private String[]          contents;
    private Orgzanation       orgzanation;
    private CustomerInfo      customer;

    private Integer           lineId;
    private String            status;
    private String            updateTime;
    private Date              baseDate;
    private Integer           couponCount;
    private String            lineName;
    private String            linePhone;
    private Integer           customerId;
    private String            customerName;
    private Integer           lineCustomerId;
    private String            lineCustomerName;
    private Integer           activityIdCount;
    private Integer           orgzanationId;
    private String            orgzanationName;
    private String            orgzanationcontract;
    private Date              begin_time;
    private Date              end_time;

    private String            startCity;
    private String            endCity;
    private Integer           customerCount;

    private String            getTime;
    private String            lineDealFeedback;
    private String            initStatus;
    private String            progressStatus;
    private String            endStatus;

    private String            shyj;
    private String            shzt;
    private String            shr;

    private String            lineDealStatus;
    private String            lineDealComment;
    private String            couponStatus;

    private String            authStatus;
    private String            auditNote;
    private Date              collectTime;
    private String            pageType;

    public String[] getContents() {
        return contents;
    }

    public void setContents(String[] contents) {
        this.contents = contents;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getOrgzanationcontract() {
        return orgzanationcontract;
    }

    public void setOrgzanationcontract(String orgzanationcontract) {
        this.orgzanationcontract = orgzanationcontract;
    }

    public String getShyj() {
        return shyj;
    }

    public void setShyj(String shyj) {
        this.shyj = shyj;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    public Integer getLineCustomerId() {
        return lineCustomerId;
    }

    public void setLineCustomerId(Integer lineCustomerId) {
        this.lineCustomerId = lineCustomerId;
    }

    public String getLineCustomerName() {
        return lineCustomerName;
    }

    public void setLineCustomerName(String lineCustomerName) {
        this.lineCustomerName = lineCustomerName;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getOrgzanationId() {
        return orgzanationId;
    }

    public void setOrgzanationId(Integer orgzanationId) {
        this.orgzanationId = orgzanationId;
    }

    public String getOrgzanationName() {
        return orgzanationName;
    }

    public void setOrgzanationName(String orgzanationName) {
        this.orgzanationName = orgzanationName;
    }

    public Integer getActivityIdCount() {
        return activityIdCount;
    }

    public void setActivityIdCount(Integer activityIdCount) {
        this.activityIdCount = activityIdCount;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Orgzanation getOrgzanation() {
        return orgzanation;
    }

    public void setOrgzanation(Orgzanation orgzanation) {
        this.orgzanation = orgzanation;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public Date getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(Date baseDate) {
        this.baseDate = baseDate;
    }

    public String getLinePhone() {
        return linePhone;
    }

    public void setLinePhone(String linePhone) {
        this.linePhone = linePhone;
    }

    public Date getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Integer getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(Integer customerCount) {
        this.customerCount = customerCount;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public String getLineDealFeedback() {
        return lineDealFeedback;
    }

    public void setLineDealFeedback(String lineDealFeedback) {
        this.lineDealFeedback = lineDealFeedback;
    }

    public String getInitStatus() {
        return initStatus;
    }

    public void setInitStatus(String initStatus) {
        this.initStatus = initStatus;
    }

    public CustomerInfo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerInfo customer) {
        this.customer = customer;
    }

    public String getLineDealStatus() {
        return lineDealStatus;
    }

    public void setLineDealStatus(String lineDealStatus) {
        this.lineDealStatus = lineDealStatus;
    }

    public String getLineDealComment() {
        return lineDealComment;
    }

    public void setLineDealComment(String lineDealComment) {
        this.lineDealComment = lineDealComment;
    }

    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

    public String getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus) {
        this.progressStatus = progressStatus;
    }

    public String getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(String endStatus) {
        this.endStatus = endStatus;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public String getAuditNote() {
        return auditNote;
    }

    public void setAuditNote(String auditNote) {
        this.auditNote = auditNote;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

}
