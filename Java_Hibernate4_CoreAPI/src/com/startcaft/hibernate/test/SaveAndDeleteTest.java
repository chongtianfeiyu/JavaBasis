package com.startcaft.hibernate.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Teacher;
import com.startcaft.hibernate.entity.ZhiCheng;
import com.startcaft.hibernate.util.HibernateUtil;

public class SaveAndDeleteTest {

	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void initSessionFactory() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	/**
	 * Hibernate中对象的三种状态的区别：
	 * 1，有没有id。
	 * 2，
	 * 
	 * Session是内存中的一个对象，
	 * 这个对象中有一个Map对象。
	 * Map的key就是持久化对象的OID(主键ID)
	 * Map的value就是持久化对象的引用。
	 * 
	 * 这是Session的一级缓存。
	 */
	@Test
	public void testSaveTeacher() {

		Teacher t = new Teacher();//对象 t 为 transient 临时状态，不受session管理。数据库中没有对应记录
		
		t.setName("t1");
		t.setCreateTime(new Date());
		t.setZhiCheng(ZhiCheng.A);
		
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(t);
		System.out.println(t.getId());//对象 t 为 persistent 持久化状态。数据库中有对应的记录(Session的缓存中与其关联)
		session.getTransaction().commit();
		
		
		System.out.println(t.getId());//对象 t 为 detached 托管状态。数据库中有对应记录,但对象自身不受session管理。
		
	}
	
	/**
	 * Delete一个对象，这个对象必须有OID(也就是主键ID)
	 */
	@Test
	public void testDelete(){
		
		Teacher t = new Teacher();
		
		t.setName("t1");
		t.setCreateTime(new Date());
		t.setZhiCheng(ZhiCheng.A);
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(t);
		System.out.println(t.getId());
		session.getTransaction().commit();
		
		Session session2 = sessionFactory.getCurrentSession();
		session2.beginTransaction();
		session2.delete(t);
		session2.getTransaction().commit();
	}
	
	@Test
	public void testDelete2(){
		
		Teacher t = new Teacher();
		t.setId(2);//只要有id号，就可以删除。
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(t);
		session.getTransaction().commit();
	}

	@AfterClass
	public static void free() {
		sessionFactory.close();
	}
}
