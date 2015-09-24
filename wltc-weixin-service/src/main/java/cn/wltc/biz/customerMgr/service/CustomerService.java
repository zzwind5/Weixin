package cn.wltc.biz.customerMgr.service;

import java.util.Date;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





import cn.wltc.biz.customerMgr.dao.CustomerDao;
import cn.wltc.biz.customerMgr.model.CustomerInfo;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.service.EntityDao;
import cn.wltc.framework.service.page.Page;
import cn.wltc.framework.service.page.PageRequest;

@Service("CustomerService_")
public class CustomerService extends BaseService<CustomerInfo, java.lang.Integer> {

    @Autowired
    private CustomerDao customerDao;

    @Override
    protected EntityDao getEntityDao() {
        return customerDao;
    }

    public void subscribe(String openId) {
        CustomerInfo user = new CustomerInfo();
        user.setOpenid(openId);
        user = customerDao.findOne(user);
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setOpenid(openId);
        customerInfo.setSubscribe_time(new Date());
        customerInfo.setLast_active_time(new Date());
        customerInfo.setStatus("1");
        if (user == null) {
            save(customerInfo);
        } else {
            customerInfo.setCustomer_id(user.getCustomer_id());
            update(customerInfo);
        }
    }

	public Page findPageCy(PageRequest pageRequest) {
		return customerDao.findPageCy(pageRequest);
	}
    public void unSubscribe(String openId) {
        CustomerInfo user = new CustomerInfo();
        user.setOpenid(openId);
        user = customerDao.findOne(user);
        if (user != null) {
            CustomerInfo customerInfo = new CustomerInfo();
            customerInfo.setCustomer_id(user.getCustomer_id());
            customerInfo.setOpenid(openId);
            customerInfo.setUnsubscribe_time(new Date());
            customerInfo.setStatus("0");
            update(customerInfo);
        }
    }
	public Page findPageFb(PageRequest pageRequest) {
		return customerDao.findPageFb(pageRequest);
	}
	
	public CustomerInfo getCustomerInfo(String openId){
        CustomerInfo user = new CustomerInfo();
        user.setOpenid(openId);
        user = customerDao.findOne(user);
        return user;
	}
	
	public int getCountRepeat(CustomerInfo query) {
		return customerDao.getCountRepeat(query);
	}
}
