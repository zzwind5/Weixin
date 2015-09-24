package cn.wltc.biz.system.dao;


import cn.wltc.biz.system.model.SysLog;
import cn.wltc.biz.system.vo.UserQuery;
import cn.wltc.common.dao.BaseIbatisDao;
import cn.wltc.framework.service.page.Page;

public class G_SysLogDao extends BaseIbatisDao<SysLog, java.lang.Integer>{

	public String getIbatisSqlMapNamespace() {
		return "SysLog";
	}
	@Override
	public Class<SysLog> getEntityClass() {
		return SysLog.class;
	}
	
	public void saveOrUpdate(SysLog entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(UserQuery query) {
		return pageQuery("SysLog.findPage",query);
	}
	

}
