package cn.wltc.biz.customerMgr.model;

import java.util.Date;

import cn.wltc.framework.service.BaseQuery;


public class CustomerInfo extends BaseQuery  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Integer customer_id;
	public String name;
	public String user_info_id;
	public String mobile;
	public String weixin;
	public String openid;
	public Date subscribe_time; 
	public Date unsubscribe_time;
	public String status;
	public Date last_active_time;
	public String customer_type;
	public String line_id;
	public String org_id;
	public String is_bind;
	public String verify_code;
	public Date verify_time;
	public String jzly;
	public String actname;
	public String linename;
	public String start;
	public String start_province;
	public String start_city;
	public String end_province;
	public String end_city;
	public String end;
	public Date get_time;//领取时间
	public Date consume_time;//使用时间
	public Date update_time;//发布时间
	public String flag;
	public String cnt;
	private String nickname;
	private String sex;
	private String province;
	private String city;
	private String country;
	private String headimgurl;
	private String isoauth;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getUser_info_id() {
		return user_info_id;
	}

	public void setUser_info_id(String user_info_id) {
		this.user_info_id = user_info_id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Date getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(Date subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public Date getUnsubscribe_time() {
		return unsubscribe_time;
	}

	public void setUnsubscribe_time(Date unsubscribe_time) {
		this.unsubscribe_time = unsubscribe_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLast_active_time() {
		return last_active_time;
	}

	public void setLast_active_time(Date last_active_time) {
		this.last_active_time = last_active_time;
	}

	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}

	public String getLine_id() {
		return line_id;
	}

	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getIs_bind() {
		return is_bind;
	}

	public void setIs_bind(String is_bind) {
		this.is_bind = is_bind;
	}

	public String getVerify_code() {
		return verify_code;
	}

	public void setVerify_code(String verify_code) {
		this.verify_code = verify_code;
	}

	public Date getVerify_time() {
		return verify_time;
	}

	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}

	public String getJzly() {
		return jzly;
	}

	public void setJzly(String jzly) {
		this.jzly = jzly;
	}

	public String getActname() {
		return actname;
	}

	public void setActname(String actname) {
		this.actname = actname;
	}

	public String getLinename() {
		return linename;
	}

	public void setLinename(String linename) {
		this.linename = linename;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getStart_province() {
		return start_province;
	}

	public void setStart_province(String start_province) {
		this.start_province = start_province;
	}

	public String getStart_city() {
		return start_city;
	}

	public void setStart_city(String start_city) {
		this.start_city = start_city;
	}

	public String getEnd_province() {
		return end_province;
	}

	public void setEnd_province(String end_province) {
		this.end_province = end_province;
	}

	public String getEnd_city() {
		return end_city;
	}

	public void setEnd_city(String end_city) {
		this.end_city = end_city;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Date getGet_time() {
		return get_time;
	}

	public void setGet_time(Date get_time) {
		this.get_time = get_time;
	}

	public Date getConsume_time() {
		return consume_time;
	}

	public void setConsume_time(Date consume_time) {
		this.consume_time = consume_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

    
    public String getNickname() {
        return nickname;
    }

    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    
    public String getSex() {
        return sex;
    }

    
    public void setSex(String sex) {
        this.sex = sex;
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

    
    public String getCountry() {
        return country;
    }

    
    public void setCountry(String country) {
        this.country = country;
    }

    
    public String getHeadimgurl() {
        return headimgurl;
    }

    
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    
    public String getIsoauth() {
        return isoauth;
    }

    
    public void setIsoauth(String isoauth) {
        this.isoauth = isoauth;
    }
    
    public boolean isOauthed(){
    		if("1".equals(isoauth)){
    			return true;
    		}
    		
    		return false;
    }
}
