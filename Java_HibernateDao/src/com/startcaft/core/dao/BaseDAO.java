package com.startcaft.core.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.startcaft.core.tools.HibernateUtil;

public class BaseDAO {

	/**
	 * ���ʵ��
	 * 
	 * @param ����ӵ�ʵ�����
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
	 * ɾ��һ��ʵ�����
	 * 
	 * @param ��ɾ����ʵ�����
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
	 * ����ʵ��
	 * 
	 * @param ������ʵ�����
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
	 * ����ָ����ʵ�����ͺ�������ֵ������ʵ�����
	 * @param cls ʵ�����
	 * @param key ������ֵ
	 * @return ���ҵ�ʵ�����
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
