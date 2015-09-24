package cn.wltc.framework.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import cn.wltc.framework.service.page.Page;
/**
 * @author badqiu
 */
public interface EntityDao <E,PK extends Serializable>{

	public Object getById(PK id) throws DataAccessException;
	
	public int deleteById(PK id) throws DataAccessException;
	
	/** 插入数据 */
	public Object save(E entity) throws DataAccessException;
	
	/** 更新数据 */
	public int update(E entity) throws DataAccessException;

	/** 根据id检查是否插入或是更新数据 */
	public void saveOrUpdate(E entity) throws DataAccessException;

	public boolean isUnique(E entity, String uniquePropertyNames) throws DataAccessException;
	
	/** 用于hibernate.flush() 有些dao实现不需要实现此类  */
	public void flush() throws DataAccessException;
	
	public List<E> findAll() throws DataAccessException;

	public abstract E findOne(BaseQuery entity);

	public abstract List<E> find(BaseQuery entity);

	public abstract int count(BaseQuery entity);

	public abstract int updateSelective(E entity);

	public abstract int deleteSelective(E entity);

	public abstract Object saveSelective(E entity);

	public abstract List<E> findTops(BaseQuery query);

	public Page<E> findPage(BaseQuery query);
	public Page<E> findPageFix(BaseQuery query);
	
}
