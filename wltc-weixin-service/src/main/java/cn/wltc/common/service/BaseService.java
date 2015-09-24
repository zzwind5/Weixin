package cn.wltc.common.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import cn.wltc.framework.service.BaseQuery;
import cn.wltc.framework.service.EntityDao;
import cn.wltc.framework.service.page.Page;

//@Transactional
public abstract class BaseService <E,PK extends Serializable>{
	
	protected Log log = LogFactory.getLog(getClass());

	protected abstract EntityDao getEntityDao();

	//@Transactional(readOnly=true)
	public E getById(PK id) throws DataAccessException{
		return (E)getEntityDao().getById(id);
	}
	
	//@Transactional(readOnly=true)
	public List<E> findAll() throws DataAccessException{
		return getEntityDao().findAll();
	}
	
	public List<E> find(BaseQuery entity) throws DataAccessException{
		return getEntityDao().find(entity);
	}
	public List<E> findTops(BaseQuery entity) throws DataAccessException{
		return getEntityDao().findTops(entity);
	}
	public E findOne(BaseQuery entity) throws DataAccessException{
		return (E)getEntityDao().findOne(entity);
	}
	public Page<E> findPage(BaseQuery query) {
		return (Page<E>)getEntityDao().findPage(query);
	}
	
	public void saveOrUpdate(E entity) throws DataAccessException{
		getEntityDao().saveOrUpdate(entity);
	}
	
	public Object save(E entity) throws DataAccessException{
		return getEntityDao().save(entity);
	}
	public void saveSelective(E entity) throws DataAccessException{
		getEntityDao().saveSelective(entity);
	}
	
	public void count(BaseQuery entity) throws DataAccessException{
		getEntityDao().count(entity);
	}
	
	public boolean removeById(PK id) throws DataAccessException{
		return getEntityDao().deleteById(id) > 0;
	}
	
	public void removeSelective(E query) throws DataAccessException{
		getEntityDao().deleteSelective(query);
	}
	
	public void update(E entity) throws DataAccessException{
		getEntityDao().update(entity);
	}
	public void updateSelective(E entity) throws DataAccessException{
		getEntityDao().updateSelective(entity);
	}
	
	//@Transactional(readOnly=true)
	public boolean isUnique(E entity, String uniquePropertyNames) throws DataAccessException {
		return getEntityDao().isUnique(entity, uniquePropertyNames);
	}
	
	public int getCount(BaseQuery entity) throws DataAccessException{
		return getEntityDao().count(entity);
	}
}
