package com.startcaft.hibernate.service;

import java.io.Serializable;
import java.util.List;

/**
 * BaseService 定义service的通用操作
 */
public interface BaseService<T> {
	
	public void save(T entity);  
	  
    public void update(T entity);  
  
    public void delete(Serializable id);  
  
    public T getById(Serializable id);  
  
    public List<T> getByHQL(String hql, Object... params); 
}
