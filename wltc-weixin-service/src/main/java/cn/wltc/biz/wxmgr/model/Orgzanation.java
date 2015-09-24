package cn.wltc.biz.wxmgr.model;

import cn.wltc.framework.service.BaseQuery;

public class Orgzanation extends BaseQuery {

	private static final long serialVersionUID = -7356913199562030523L;

	private Integer id;
	private String name;
	private String type;
	private String countyCode;
	private String province;
	private String city;
	private String county;
	private String address;
	private String contract;
	private String phone;
	private String fax;
	private String email;
	private String zip;
	private String websie;
	
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
	public String getCountyCode() {
		return countyCode;
	}
	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getWebsie() {
		return websie;
	}
	public void setWebsie(String websie) {
		this.websie = websie;
	}
	
	
}
