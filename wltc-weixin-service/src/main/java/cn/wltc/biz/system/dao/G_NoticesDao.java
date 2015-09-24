package cn.wltc.biz.system.dao;


import cn.wltc.biz.system.model.Notices;
import cn.wltc.common.dao.BaseIbatisDao;
import cn.wltc.framework.service.page.Page;

@SuppressWarnings("unchecked")
public class G_NoticesDao extends BaseIbatisDao<Notices, java.lang.Integer>{

	public String getIbatisSqlMapNamespace() {
		return "Notices";
	}
	@Override
	public Class<Notices> getEntityClass() {
		return Notices.class;
	}
	
	public void saveOrUpdate(Notices entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page<?> findPage(Notices query) {
		return pageQuery("Notices.findPage",query);
	}
	

}
