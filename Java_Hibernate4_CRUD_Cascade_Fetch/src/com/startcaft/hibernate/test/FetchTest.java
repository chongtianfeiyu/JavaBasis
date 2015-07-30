package com.startcaft.hibernate.test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Group;
import com.startcaft.hibernate.entity.User;
import com.startcaft.hibernate.util.HibernateUtil;

/*
 * Cascade 管CUD
 * Fetch 管R
 * ----@OneToMany 的Fetch默认为Lazy
 * ----@ManyToOne 的Fetch默认是EAGER
 * ----在双向关联关系时候设置fetch的时候要谨慎，一般用Lazy而不用eager。
 * 		可以双方都设置Lazy，但不要双方都设置eager【不然会有多余的查询语句】。
 * 
 * 任何一个 关联 注解 都有Cascade和Fetch属性。
 */
public class FetchTest {
	
	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void initSessionFactory() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@AfterClass
	public static void free() {
		sessionFactory.close();
	}

	/**
	 * ManyToOne 不管设不设置Cascade 在取多的一端的 数据时，一的一方也会默认被取出。
	 */
	@Test
	public void getUserTest() {

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		User user = (User) session.get(User.class, 1);

		tx.commit();
	}
	
	/**
	 * OneToMany 不管设不设置Cascade 在取一的一端 数据时候，多的一方的数据默认不会被取出【因为效率】。
	 */
	@Test
	public void getGroupTest(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Group group = (Group) session.get(Group.class, 1);

		tx.commit();
	}
	
	@Test
	public void getGroupEagerTest(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Group group = (Group) session.get(Group.class, 1);

		tx.commit();
		
		for(User user : group.getUsers()){
			System.out.println(user.getName());
		}
	}
	
	@Test
	public void getUserLazyTest(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		User user = (User) session.get(User.class, 1);
		
		System.out.println(user.getGroup().getName());//这里才取关联对象。一定要在一个session中。

		tx.commit();

		//System.out.println(user.getGroup().getName());//LazyInitializationException异常
	}
	
	@Test
	public void loadUserLazyTest(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		User user = (User) session.load(User.class, 1);
		
		System.out.println(user.getGroup().getName());//这里发送2条SQL语句。一定要在一个session中。

		tx.commit();

		//System.out.println(user.getGroup().getName());//LazyInitializationException异常
	}
	
	@Test
	public void updateUserTest(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		User user = (User) session.get(User.class, 1);

		tx.commit();
		
		
		//托管状态的对象
		user.setName("zhangsan");
		user.getGroup().setName("admin");
		
		
		//第二个session
		Session session2 = sessionFactory.getCurrentSession();
		Transaction tx2 = session2.beginTransaction();
		
		session2.update(user);//更新托管对象。

		tx2.commit();
	}
}
