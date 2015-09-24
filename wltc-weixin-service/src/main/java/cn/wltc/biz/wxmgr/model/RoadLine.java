package cn.wltc.biz.wxmgr.model;

import java.util.Date;

import cn.wltc.framework.service.BaseQuery;

public class RoadLine extends BaseQuery {

	private static final long serialVersionUID = 4954592252626775581L;

	private Integer id;
	private Integer orgId;
	private String orgName;
	private String name;
	private String lineDesc;
	private String hasData;
	private Float lineDistance;
	private String term;
	private String service;
	private String start;
	private String startProvince;
	private String startCity;
	private String endProvince;
	private String endCity;
	private String end;
	private String startCd;
	private String endCd;
	private String startCode;
	private String endCode;
	private String orgContract;
	private String orgPhone;
	private String contract;
	private String contract2;
	private String deliveryScope;
	private String phone2;
	private Integer customerId;
	private Date collectTime;
	private String phone;
	private String transit;
	private Integer collectedTotle;
	private Integer activeTotle;
    public String getTransit() {
        return transit;
    }

    public void setTransit(String transit) {
        this.transit = transit;
    }

    public Date getCollectTime() {
        return collectTime;
    }
    
    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }
    public String getStartCode() {
		return startCode;
	}
	public void setStartCode(String startCode) {
		this.startCode = startCode;
	}
	public String getEndCode() {
		return endCode;
	}
	public void setEndCode(String endCode) {
		this.endCode = endCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLineDesc() {
		return lineDesc;
	}
	public void setLineDesc(String lineDesc) {
		this.lineDesc = lineDesc;
	}
	public String getHasData() {
		return hasData;
	}
	public void setHasData(String hasData) {
		this.hasData = hasData;
	}
	public Float getLineDistance() {
		return lineDistance;
	}
	public void setLineDistance(Float lineDistance) {
		this.lineDistance = lineDistance;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getStartProvince() {
		return startProvince;
	}
	public void setStartProvince(String startProvince) {
		this.startProvince = startProvince;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getEndProvince() {
		return endProvince;
	}

	public void setEndProvince(String endProvince) {
		this.endProvince = endProvince;
	}

	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getStartCd() {
		return startCd;
	}
	public void setStartCd(String startCd) {
		this.startCd = startCd;
	}
	public String getEndCd() {
		return endCd;
	}
	public void setEndCd(String endCd) {
		this.endCd = endCd;
	}
	public String getOrgContract() {
		return orgContract;
	}
	public void setOrgContract(String orgContract) {
		this.orgContract = orgContract;
	}
	public String getOrgPhone() {
		return orgPhone;
	}
	public void setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getContract2() {
		return contract2;
	}

	public void setContract2(String contract2) {
		this.contract2 = contract2;
	}

	public String getDeliveryScope() {
		return deliveryScope;
	}

	public void setDeliveryScope(String deliveryScope) {
		this.deliveryScope = deliveryScope;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getCollectedTotle() {
		return collectedTotle;
	}

	public void setCollectedTotle(Integer collectedTotle) {
		this.collectedTotle = collectedTotle;
	}

	public Integer getActiveTotle() {
		return activeTotle;
	}

	public void setActiveTotle(Integer activeTotle) {
		this.activeTotle = activeTotle;
	}
	
}
