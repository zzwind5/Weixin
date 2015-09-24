package cn.wltc.biz.customerMgr.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;


import org.springframework.stereotype.Repository;

import cn.wltc.biz.customerMgr.model.CustomerInfo;
import cn.wltc.common.dao.BaseIbatisDao;
import cn.wltc.framework.service.page.Page;
import cn.wltc.framework.service.page.PageRequest;

@Repository("CustomerDao_")
public class CustomerDao extends BaseIbatisDao<CustomerInfo, java.lang.Integer> {

	public String getIbatisSqlMapNamespace() {
		return "CustomerInfo";
	}

	@Override
	public Class<CustomerInfo> getEntityClass() {
		return CustomerInfo.class;
	}

	public void saveOrUpdate(CustomerInfo entity) {
		if (entity.getCustomer_id() >0 )
			save(entity);
		else
			update(entity);
	}

	public Page findPage(CustomerInfo query) {
		return pageQuery("CustomerInfo.findPage", query);
	}

	
	public Page findPageCy(PageRequest pageRequest) {
		Number totalCount = (Number) this.getSqlMapClientTemplate().queryForObject("CustomerInfo.countCy",pageRequest);
		Page page = new Page(pageRequest,totalCount.intValue());
		List list = getSqlMapClientTemplate().queryForList("CustomerInfo.findPageCy", pageRequest);
		page.setResult(list);
		return page;
	}
	
	public Page findPageFb(PageRequest pageRequest) {
		Number totalCount = (Number) this.getSqlMapClientTemplate().queryForObject("CustomerInfo.countFb",pageRequest);
		Page page = new Page(pageRequest,totalCount.intValue());
		List list = getSqlMapClientTemplate().queryForList("CustomerInfo.findPageFb", pageRequest);
		page.setResult(list);
		return page;
	}
	
	public int getCountRepeat(CustomerInfo query) {
		return ((Integer) getSqlMapClientTemplate().queryForObject(
				getEntityClass().getSimpleName() + ".getCountRepeat", query));
	}

}
