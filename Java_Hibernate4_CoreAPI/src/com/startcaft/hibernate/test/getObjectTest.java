package com.startcaft.hibernate.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Teacher;
import com.startcaft.hibernate.util.HibernateUtil;

public class getObjectTest {
	
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
	 * load��get������:(find�Ѿ���ʱ��)
	 * 1�������ڶ�Ӧ��¼ʱ�ı��ֲ�һ����
	 * 2��load���ص��Ǵ�����󣬵�����ʹ�õ������ʱ��ŷ���sql��䡣��ν���ӳټ��ء�
	 * 3��getֱ�Ӵ����ݿ���أ������ӳ١�
	 * 
	 * ��֮����get��load�ĸ�������һ�仰��hibernate����load������Ϊ�����������ݿ���һ�����ڣ����Է��ĵ�ʹ�ô������ӳټ��أ�
	 * �����ʹ�ù����з��������⣬ֻ�����쳣��
	 * ������get������hibernateһ��Ҫ��ȡ����ʵ�����ݣ����򷵻�null��
	 */
	@Test
	public void testLoad(){
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Teacher t = (Teacher) session.load(Teacher.class, 1);//�־û�״̬���������У����ݿ���Ҳ��
		
		System.out.println(t.getClass());
		
		session.getTransaction().commit();
		
		//System.out.println(t.getName());
	}
	@Test
	public void testGet(){
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Teacher t = (Teacher) session.get(Teacher.class, 1);
		
		session.getTransaction().commit();
		
		System.out.println(t.getClass());
		System.out.println(t.getName());
	}
}
