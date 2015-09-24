package cn.wltc.biz.wxmgr.model;

import cn.wltc.framework.service.BaseQuery;


public class CouponOrder extends BaseQuery {

    private static final long serialVersionUID = -2421248859131455347L;

    private Integer orderId;
    private Integer couponId;
    private Integer lineId;
    private Integer orgId;
    private String customerOrgname;
    private String cargoName;
    private String cargoNumber;
    private String cargoUnit;
    private String destination;


    public String getCustomerOrgname() {
        return customerOrgname;
    }


    public void setCustomerOrgname(String customerOrgname) {
        this.customerOrgname = customerOrgname;
    }


    public Integer getOrderId() {
        return orderId;
    }


    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public Integer getCouponId() {
        return couponId;
    }


    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }


    public Integer getLineId() {
        return lineId;
    }


    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }


    public Integer getOrgId() {
        return orgId;
    }


    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }


    public String getCargoName() {
        return cargoName;
    }


    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }


    public String getCargoNumber() {
        return cargoNumber;
    }


    public void setCargoNumber(String cargoNumber) {
        this.cargoNumber = cargoNumber;
    }


    public String getCargoUnit() {
        return cargoUnit;
    }


    public void setCargoUnit(String cargoUnit) {
        this.cargoUnit = cargoUnit;
    }


    public String getDestination() {
        return destination;
    }


    public void setDestination(String destination) {
        this.destination = destination;
    }
}
