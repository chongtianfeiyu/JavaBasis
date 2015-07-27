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
	 * load和get的区别:(find已经过时了)
	 * 1，不存在对应记录时的表现不一样。
	 * 2，load返回的是代理对象，等真正使用到对象的时候才发出sql语句。所谓的延迟加载。
	 * 3，get直接从数据库加载，不会延迟。
	 * 
	 * 总之对于get和load的根本区别，一句话，hibernate对于load方法认为该数据在数据库中一定存在，可以放心的使用代理来延迟加载，
	 * 如果在使用过程中发现了问题，只能抛异常；
	 * 而对于get方法，hibernate一定要获取到真实的数据，否则返回null。
	 */
	@Test
	public void testLoad(){
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Teacher t = (Teacher) session.load(Teacher.class, 1);//持久化状态，缓存中有，数据库中也有
		
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
