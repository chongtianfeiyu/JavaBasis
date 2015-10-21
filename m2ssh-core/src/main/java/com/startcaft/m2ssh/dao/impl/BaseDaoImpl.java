package com.startcaft.m2ssh.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.startcaft.m2ssh.dao.BaseDao;

/**
 * DAO通用操作的具体实现
 * @author startcaft
 *
 * @param <T> 实体类
 */
@SuppressWarnings("unchecked")  
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	/*实体类的对应的Class对象*/
	private Class<T> clazz;
	
	/*通过Spring容器注入的SessionFactory对象*/
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() {  
        return this.sessionFactory.getCurrentSession();  
    }  
	
	public BaseDaoImpl() {
		//返回该类直接父类的参数化类型
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		//参数化类型数组的第一个就是具体的实体类的类型
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	

	public void save(T entity) throws Exception{
		this.getSession().save(entity);
	}

	public void update(T entity) throws Exception{
		this.getSession().update(entity);
	}

	public void delete(Serializable id) throws Exception{
		this.getSession().delete(this.findById(id));
	}

	public T findById(Serializable id) throws Exception{
		return (T) this.getSession().get(clazz, id);
	}

	public List<T> findByHQL(String hql, Object... params) throws Exception{
		
		Query query = this.getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.list();
	}

	public List<T> findPageByHQL(String hql, Integer pageIndex,
			Integer pageSize, Object... params) throws Exception{
		
		Query query = this.getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		query.setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageIndex * pageSize);
		return query.list();
	}

	public Long findCountByHQL(String hql, Object... params) throws Exception {
		
		Query query = this.getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return (Long) query.uniqueResult();
	}

}
