package cn.wltc.biz.system.dao;


import cn.wltc.biz.system.model.SysUserRoleInfo;
import cn.wltc.biz.system.vo.UserQuery;
import cn.wltc.common.dao.BaseIbatisDao;
import cn.wltc.framework.service.page.Page;

public class G_SysUserRoleInfoDao extends BaseIbatisDao<SysUserRoleInfo, java.lang.Integer>{

	public String getIbatisSqlMapNamespace() {
		return "SysUserRoleInfo";
	}
	@Override
	public Class<SysUserRoleInfo> getEntityClass() {
		return SysUserRoleInfo.class;
	}
	
	public void saveOrUpdate(SysUserRoleInfo entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(UserQuery query) {
		return pageQuery("SysUserRoleInfo.findPage",query);
	}
	

}
