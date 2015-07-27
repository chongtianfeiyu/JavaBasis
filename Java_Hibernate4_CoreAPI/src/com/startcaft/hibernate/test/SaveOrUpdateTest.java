package com.startcaft.hibernate.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Teacher;
import com.startcaft.hibernate.entity.ZhiCheng;
import com.startcaft.hibernate.util.HibernateUtil;

public class SaveOrUpdateTest {

	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void initSessionFactory() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	@AfterClass
	public static void free() {
		sessionFactory.close();
	}
	
	
	/*
	 * Session.saveOrUpdate()�����ڲ��ܸ��ӡ�
	 */
	@Test
	public void testSaveOrUpdate(){
		
		Teacher teacher = new Teacher();
		teacher.setName("t2");
		teacher.setZhiCheng(ZhiCheng.B);
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(teacher);//ִ��save����Ϊû��oid��
		session.getTransaction().commit();
		
		teacher.setName("ttt2");
		
		Session session2 = sessionFactory.getCurrentSession();
		session2.beginTransaction();
		session2.saveOrUpdate(teacher);//ִ��update����id
		session2.getTransaction().commit();
	}
}
