package cn.wltc.common.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.wltc.framework.service.BaseQuery;
import cn.wltc.framework.service.EntityDao;
import cn.wltc.framework.service.page.Page;
import cn.wltc.framework.service.page.PageRequest;

public abstract class BaseIbatisDao<E,PK extends Serializable> implements EntityDao<E,PK> {
    protected final Log log = LogFactory.getLog(getClass());
    @Autowired
    private SqlMapClientTemplate sqlMapClientTemplate;
    
    public SqlMapClientTemplate getSqlMapClientTemplate(){
    	return sqlMapClientTemplate;
    }
    
    public abstract Class<E> getEntityClass();
    protected String getFindByPrimaryKeyQuery() {
        return getEntityClass().getSimpleName()+".getById";
    }

    protected String getInsertQuery() {
        return getEntityClass().getSimpleName()+".insert";
    }
    protected String getInsertSelectiveQuery() {
        return getEntityClass().getSimpleName()+".insertSelective";
    }

    protected String getUpdateQuery() {
    	return getEntityClass().getSimpleName()+".update";
    }
    
    protected String getUpdateSelectiveQuery() {
    	return getEntityClass().getSimpleName()+".updateSelective";
    }

    protected String getDeleteQuery() {
    	return getEntityClass().getSimpleName()+".delete";
    }
    protected String getDeleteSelectiveQuery() {
    	return getEntityClass().getSimpleName()+".deleteSelective";
    }

    protected String getCountQuery() {
		return getEntityClass().getSimpleName() +".count";
	}
    protected String getFindQuery() {
  		return getEntityClass().getSimpleName() +".find";
  	}
    protected String getFindPageQuery() {
  		return getEntityClass().getSimpleName() +".findPage";
  	}
    
    /* 根据主键查找记录
     * @see cn.wltc.framework.service.EntityDao#getById(java.io.Serializable)
     */
    public Object getById(PK primaryKey) {
        Object object = getSqlMapClientTemplate().queryForObject(getFindByPrimaryKeyQuery(), primaryKey);
        return object;
    }
    
	/* 删除1条记录
	 * @see cn.wltc.framework.service.EntityDao#deleteById(java.io.Serializable)
	 */
	public int deleteById(PK id) {
		return getSqlMapClientTemplate().delete(getDeleteQuery(), id);
	}
	
	/* 根据条件选择性删除
	 * @see cn.wltc.framework.service.EntityDao#deleteSelective(cn.wltc.framework.service.BaseQuery)
	 */
	public int deleteSelective(E query) {
		return getSqlMapClientTemplate().delete(getDeleteSelectiveQuery(), query);
	}
    /* 插入
     * @see cn.wltc.framework.service.EntityDao#save(java.lang.Object)
     */
    public Object save(E entity) {
		Object primaryKey = getSqlMapClientTemplate().insert(getInsertQuery(), entity); 
		return primaryKey;
    }
    /* 插入，只有有数据的列插入
     * @see cn.wltc.framework.service.EntityDao#saveSelective(java.lang.Object)
     */
	public Object saveSelective(E entity) {
		Object primaryKey = getSqlMapClientTemplate().insert(getInsertSelectiveQuery(), entity); 
		return primaryKey;
    }
    
	/* 更新
	 * @see cn.wltc.framework.service.EntityDao#update(java.lang.Object)
	 */
	public int update(E entity) {
		return getSqlMapClientTemplate().update(getUpdateQuery(), entity);
	}
    /* 更新，有数据列才更新
     * @see cn.wltc.framework.service.EntityDao#updateSelective(java.lang.Object)
     */
	public int updateSelective(E entity) {
		return getSqlMapClientTemplate().update(getUpdateSelectiveQuery(), entity);
	}
    /* 统计
     * @see cn.wltc.framework.service.EntityDao#count(cn.wltc.framework.service.BaseQuery)
     */
	public int count(BaseQuery query) {
  		return ((Long)getSqlMapClientTemplate().queryForObject(getCountQuery(), query)).intValue();
  	}
    /* 查找所有
     * @see cn.wltc.framework.service.EntityDao#find(cn.wltc.framework.service.BaseQuery)
     */
	public List<E> find(BaseQuery query) {
  		return getSqlMapClientTemplate().queryForList(getFindQuery(), query);
  	}
    /*查找一条记录
     * @see cn.wltc.framework.service.EntityDao#findOne(cn.wltc.framework.service.BaseQuery)
     */
	public E findOne(BaseQuery query) {
  		return (E)getSqlMapClientTemplate().queryForObject(getFindQuery(), query);
  	}
    
    /**分页查找
     * @param query
     * @return
     */
    public Page<E> findPage(BaseQuery query) {
		return pageQuery(getFindPageQuery(),query);
	}
    
    /**获取tops
     * @param query
     * @return
     */
	public List<E> findTops(BaseQuery query) {
		return FindTopsBase(getFindPageQuery(),query);
	}
    
    
    /**查找top行数
	 * @param statementName
	 * @param pageRequest
	 * @return
	 */
	protected List FindTopsBase(String statementName, PageRequest pageRequest) {
		List list = getSqlMapClientTemplate().queryForList(statementName, pageRequest);
		return list;
	}
    
	/**分页查找基础类
	 * @param statementName
	 * @param pageRequest
	 * @return
	 * @deprecated instead use pageQueryFix
	 */
	
	protected Page pageQuery(String statementName, PageRequest pageRequest) {
		Number totalCount = (Number) this.getSqlMapClientTemplate().queryForObject(getCountQuery(),pageRequest);
		Page page = new Page(pageRequest,totalCount.intValue());
		List list = getSqlMapClientTemplate().queryForList(statementName, pageRequest);
		page.setResult(list);
		return page;
	}
	
    public Page<E> findPageFix(String statementName,BaseQuery query) {
        return pageQueryFix(statementName,query);
    }
   protected Page pageQueryFix(String statementName, PageRequest pageRequest) {
        Number totalCount = (Number) this.getSqlMapClientTemplate().queryForObject(statementName + ".count",pageRequest);
        Page page = new Page(pageRequest,totalCount.intValue());
        List list = getSqlMapClientTemplate().queryForList(statementName, pageRequest);
        page.setResult(list);
        return page;
    }
	
	public List findAll() {
		return getSqlMapClientTemplate().queryForList(getFindQuery(), null);
	}

	public boolean isUnique(E entity, String uniquePropertyNames) {
		throw new UnsupportedOperationException();
	}
	
	public void flush() {
		//ignore
	}
	
	public Page<E> findPageFix(BaseQuery query){
	    return null;
	}
}
