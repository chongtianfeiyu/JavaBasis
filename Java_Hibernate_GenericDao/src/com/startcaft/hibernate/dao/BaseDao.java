package com.startcaft.hibernate.dao;

import java.io.Serializable;
import java.util.List;

/**
 * BaseDAO 定义DAO的通用操作
 */
public interface BaseDao<T> {
	
	public void save(T entity);
	
	public void update(T entity);
	
	public void delete(Serializable id);
	
	public T findById(Serializable id);
	
	public List<T> findByHQL(String hql,Object... params);
}
