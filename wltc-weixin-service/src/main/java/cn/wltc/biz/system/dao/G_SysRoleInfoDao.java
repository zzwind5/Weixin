package cn.wltc.biz.system.dao;


import cn.wltc.biz.system.model.SysRoleInfo;
import cn.wltc.biz.system.vo.UserQuery;
import cn.wltc.common.dao.BaseIbatisDao;
import cn.wltc.framework.service.page.Page;

public class G_SysRoleInfoDao extends BaseIbatisDao<SysRoleInfo, java.lang.Integer>{

	public String getIbatisSqlMapNamespace() {
		return "SysRoleInfo";
	}
	@Override
	public Class<SysRoleInfo> getEntityClass() {
		return SysRoleInfo.class;
	}
	
	public void saveOrUpdate(SysRoleInfo entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(UserQuery query) {
		return pageQuery("SysRoleInfo.findPage",query);
	}
	

}
