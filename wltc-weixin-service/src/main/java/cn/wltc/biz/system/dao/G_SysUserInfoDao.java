package cn.wltc.biz.system.dao;


import cn.wltc.biz.system.model.SysUserInfo;
import cn.wltc.biz.system.vo.UserQuery;
import cn.wltc.common.dao.BaseIbatisDao;
import cn.wltc.framework.service.page.Page;

public class G_SysUserInfoDao extends BaseIbatisDao<SysUserInfo, java.lang.Integer>{

	public String getIbatisSqlMapNamespace() {
		return "SysUserInfo";
	}
	@Override
	public Class<SysUserInfo> getEntityClass() {
		return SysUserInfo.class;
	}
	
	public void saveOrUpdate(SysUserInfo entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(UserQuery query) {
		return pageQuery("SysUserInfo.findPage",query);
	}
	

}
