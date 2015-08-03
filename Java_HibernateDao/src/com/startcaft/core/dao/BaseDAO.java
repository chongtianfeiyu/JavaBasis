package com.startcaft.core.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.startcaft.core.tools.HibernateUtil;

public class BaseDAO {

	/**
	 * 添加实体
	 * 
	 * @param 待添加的实体对象
	 * @throws Exception
	 */
	public void add(Object obj) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.save(obj);
			session.getTransaction().commit();

			if (HibernateUtil.getSessionFactory() != null) {
				HibernateUtil.getSessionFactory().close();
			}
		} catch (RuntimeException e) {
			session.beginTransaction().rollback();
			if (HibernateUtil.getSessionFactory() != null) {
				HibernateUtil.getSessionFactory().close();
			}
			throw e;
		}
	}

	/**
	 * 删除一个实体对象
	 * 
	 * @param 待删除的实体对象
	 * @throws Exception
	 */
	public void delete(Object obj) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.delete(obj);
			session.getTransaction().commit();

			if (HibernateUtil.getSessionFactory() != null) {
				HibernateUtil.getSessionFactory().close();
			}
		} catch (RuntimeException e) {
			session.beginTransaction().rollback();
			if (HibernateUtil.getSessionFactory() != null) {
				HibernateUtil.getSessionFactory().close();
			}
			throw e;
		}
	}

	/**
	 * 更新实体
	 * 
	 * @param 待更新实体对象
	 * @throws Exception
	 */
	public void update(Object obj) throws Exception {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.update(obj);
			session.getTransaction().commit();

			if (HibernateUtil.getSessionFactory() != null) {
				HibernateUtil.getSessionFactory().close();
			}
		} catch (RuntimeException e) {
			session.beginTransaction().rollback();
			if (HibernateUtil.getSessionFactory() != null) {
				HibernateUtil.getSessionFactory().close();
			}
			throw e;
		}
	}

	/**
	 * 根据指定的实体类型和主键的值，查找实体对象
	 * @param cls 实体的类
	 * @param key 主键的值
	 * @return 查找的实体对象
	 * @throws Exception
	 */
	public Object findById(String cls, Serializable key) throws Exception {
		try {
			Object instance = (Object) HibernateUtil.getSessionFactory().getCurrentSession()
					.get(cls, key);
			return instance;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<?> findByHQL(String hql) throws Exception{
		try{
			Query query = HibernateUtil.getSessionFactory().getCurrentSession().createQuery(hql);
			return query.list();
		}
		catch(Exception e){
			throw e;
		}
	}
}
