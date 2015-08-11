package com.startcaft.hibernate.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.startcaft.hibernate.dao.BaseDao;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	private Class<T> clazz;
	
	/**
	 * 向DAO注入SessionFactory对象 
	 */
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	/**
	 * 通过构造方法指定DAO的具体实现类
	 */
	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
		System.out.println("DAO的真实实现类是:" + this.clazz.getName());
	}
	
	
	@Override
	public void save(T entity) {
		this.getSession().save(entity);
	}

	@Override
	public void update(T entity) {
		this.getSession().update(entity);
	}

	@Override
	public void delete(Serializable id) {
		this.getSession().delete(this.findById(id));
	}

	@Override
	public T findById(Serializable id) {
		return (T) this.getSession().get(this.clazz, id);
	}

	@Override
	public List<T> findByHQL(String hql, Object... params) {
		Query query = this.getSession().createQuery(hql);
		for(int i=0;params != null && i<params.length;i++){
			query.setParameter(i, params);
		}
		return query.list();
	}
}
