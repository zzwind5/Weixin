package cn.wltc.biz.system.dao;


import cn.wltc.biz.system.model.SysRoleAuthInfo;
import cn.wltc.biz.system.vo.UserQuery;
import cn.wltc.common.dao.BaseIbatisDao;
import cn.wltc.framework.service.page.Page;

public class G_SysRoleAuthInfoDao extends BaseIbatisDao<SysRoleAuthInfo, java.lang.Integer>{

	public String getIbatisSqlMapNamespace() {
		return "SysRoleAuthInfo";
	}
	@Override
	public Class<SysRoleAuthInfo> getEntityClass() {
		return SysRoleAuthInfo.class;
	}
	
	public void saveOrUpdate(SysRoleAuthInfo entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(UserQuery query) {
		return pageQuery("SysRoleAuthInfo.findPage",query);
	}
	

}
