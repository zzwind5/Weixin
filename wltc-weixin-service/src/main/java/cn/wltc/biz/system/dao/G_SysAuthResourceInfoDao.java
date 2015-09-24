package cn.wltc.biz.system.dao;


import cn.wltc.biz.system.model.SysAuthResourceInfo;
import cn.wltc.biz.system.vo.UserQuery;
import cn.wltc.common.dao.BaseIbatisDao;
import cn.wltc.framework.service.page.Page;

public class G_SysAuthResourceInfoDao extends BaseIbatisDao<SysAuthResourceInfo, java.lang.Integer>{

	public String getIbatisSqlMapNamespace() {
		return "SysAuthResourceInfo";
	}
	@Override
	public Class<SysAuthResourceInfo> getEntityClass() {
		return SysAuthResourceInfo.class;
	}
	
	public void saveOrUpdate(SysAuthResourceInfo entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(UserQuery query) {
		return pageQuery("SysAuthResourceInfo.findPage",query);
	}
	

}
