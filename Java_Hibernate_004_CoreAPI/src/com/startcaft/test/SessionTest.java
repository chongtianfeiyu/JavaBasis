package com.startcaft.test;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.model.Student;


/**
 * Hibernate中的SessionFactory是一个上下文对象，
 * 这个上下文对象管理者一个数据库连接池。
 * SessionFactory的初始化依赖于Configuration对象，这个对象管理着Hibernate.cfg.xml配置文件
 * 
 * Hibernate中的Session对象，可以简单粗暴的认为就相当于一个数据库连接。
 * 获取Session的方式有两种：
 * 1，OpenSession()---每次都创建一个新的数据库连接，必须手动close()掉。
 * 2，getCurrentSession()---从上下文中获取一个数据库连接，如果没有，则创建一个新的。
 * 并且在事物提交之后自动close()掉。
 * 
 * @author startcaft
 */
public class SessionTest {
	
	private static SessionFactory factory;
	
	//初始化SessionFactory
	@BeforeClass
	public static void initSessionFactory(){
		try {
			factory = new AnnotationConfiguration().configure().buildSessionFactory();
			System.out.println("SessionFactory Init Done!");
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void OpenSessionTest(){
		
		Session session1 = factory.openSession();
		
		Session session2 = factory.openSession();
		
		//断言session1和session2不相同
		assertThat(false, Matchers.is(session1 == session2));
	}
	
	@Test
	public void getCurrentSessionNoCommit(){
		
		Session session1 = factory.getCurrentSession();
		Session session2 = factory.getCurrentSession();
		
		//断言session1和session2相同，在没有commit的情况下
		assertThat(true, Matchers.is(session1 == session2));
	}
	
	@Test
	public void getCurrentSessionWithCommit(){
		
		Session session1 = factory.getCurrentSession();
		Session session2 = factory.getCurrentSession();
		Session session3 = factory.getCurrentSession();
		
		assertThat(true, Matchers.is(session1 == session2));
		assertThat(true, Matchers.is(session2 == session3));
		assertThat(true, Matchers.is(session3 == session1));
		
		session1.beginTransaction();
		Student s = new Student();
		s.setName("startcaft");
		session1.save(s);
		session1.getTransaction().commit();
		
		//session1提交之后，sesison1就close()掉了，在从上下文中拿数据库连接必然是一个新的。
		Session session4 = factory.getCurrentSession();
		
		assertThat(false, Matchers.is(session1 == session4));
	}
}
