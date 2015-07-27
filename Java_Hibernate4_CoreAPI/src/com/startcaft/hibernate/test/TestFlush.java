package com.startcaft.hibernate.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Teacher;
import com.startcaft.hibernate.util.HibernateUtil;

public class TestFlush {

	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void initSessionFactory() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	@AfterClass
	public static void free() {
		sessionFactory.close();
	}
	
	@Test
	public void testFlush(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Teacher t = (Teacher) session.load(Teacher.class, 1);//持久化状态，缓存中有，数据库中也有
		
		t.setName("ttt");
		
		session.flush();//强制让缓存与数据库内容同步。
		
		t.setName("tttt");
		
		session.getTransaction().commit();//commit默认就会flush。可以使用Session.setFlushMod()设置flush的时机，【了解即可】。
	}

}
