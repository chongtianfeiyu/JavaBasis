package com.startcaft.m2ssh.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 定义DAO的通用操作
 * 
 * @author startcaft
 *
 * @param <T> 实体类
 */
public interface BaseDao<T> {
	
	/**保存实体对象**/
	public void save(T entity) throws Exception;  
	
	/**更新实体对象**/
    public void update(T entity) throws Exception;  
    
    /**删除实体对象**/
    public void delete(Serializable id) throws Exception;  
    
    /**获取一个实体对象**/
    public T findById(Serializable id) throws Exception;  
    
    /**根据hql查询**/
    public List<T> findByHQL(String hql, Object... params) throws Exception;
    
    /**根据hql分页查询**/
    public List<T> findPageByHQL(String hql,Integer pageIndex,Integer pageSize,Object... params) throws Exception;
    
    /**根据hql查询总数**/
    public Long findCountByHQL(String hql,Object... params) throws Exception;
}
