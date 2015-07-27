package com.startcaft.hibernate.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Teacher;
import com.startcaft.hibernate.util.HibernateUtil;

public class ClearTest {

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
	public void testClear(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Teacher t = (Teacher) session.load(Teacher.class, 1);
		System.out.println(t.getName());//发送一条SQL语句。t 变成 持久化状态，缓存中有对应的对象。
		
		session.clear();//清除缓存后，就会发送两条SQL语句。
		
		Teacher t2 = (Teacher) session.load(Teacher.class, 1);
		System.out.println(t2.getName());//因为1对应的记录在缓存中有，所以直接从缓存中拿。
		
		session.getTransaction().commit();
	}
}
