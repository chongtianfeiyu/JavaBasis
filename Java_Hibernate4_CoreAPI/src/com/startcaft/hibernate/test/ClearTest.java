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
		System.out.println(t.getName());//����һ��SQL��䡣t ��� �־û�״̬���������ж�Ӧ�Ķ���
		
		session.clear();//�������󣬾ͻᷢ������SQL��䡣
		
		Teacher t2 = (Teacher) session.load(Teacher.class, 1);
		System.out.println(t2.getName());//��Ϊ1��Ӧ�ļ�¼�ڻ������У�����ֱ�Ӵӻ������á�
		
		session.getTransaction().commit();
	}
}
