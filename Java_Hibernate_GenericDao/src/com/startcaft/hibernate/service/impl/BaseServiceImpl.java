package com.startcaft.hibernate.service.impl;

import java.io.Serializable;
import java.util.List;


import com.startcaft.hibernate.dao.BaseDao;
import com.startcaft.hibernate.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {
	
	/**
	 * ×¢ÈëBaseDao
	 */
	private BaseDao<T> dao;
	
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}
	
	@Override
	public void save(T entity) {
		this.dao.save(entity);
	}

	@Override
	public void update(T entity) {
		this.dao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		this.dao.delete(id);
	}

	@Override
	public T getById(Serializable id) {
		return this.dao.findById(id);
	}

	@Override
	public List<T> getByHQL(String hql, Object... params) {
		return this.dao.findByHQL(hql, params);
	}
}
