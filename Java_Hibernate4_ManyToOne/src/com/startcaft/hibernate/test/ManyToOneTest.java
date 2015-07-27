package com.startcaft.hibernate.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Group;
import com.startcaft.hibernate.entity.User;
import com.startcaft.hibernate.util.HibernateUtil;


/*
 * 场景：一个人 有多个梦想，每个具体的梦想都只属于一个人。
 * 
 * 数据库表的设计：
 * ------------------------------------------------------
 * 		pId		|		pName		|		dreamId
 * ------------------------------------------------------
 * 		1		|		zhangsan	|			1
 * ------------------------------------------------------
 * 		2		|		zhangsan	|			2
 * ------------------------------------------------------
 * ----------------------------------
 * 		dId		|		dDesc		|
 * ----------------------------------
 * 		1		|		赚很多钱		|			
 * ----------------------------------
 * 		2		|		吃很多美食		|			
 * ----------------------------------
 * 以上的 数据库设计 在【一的一方】表中会产生数据冗余。所以，在ManyToOne的设计时，【外键应该加在多的一方】
 * 
 * ------------------------------------------------------
 * 		dId		|		dDesc		|			pId
 * ------------------------------------------------------
 * 		1		|		赚很多钱		|			1
 * ------------------------------------------------------
 * 		2		|		吃很多美食		|			1
 * ------------------------------------------------------
 * ----------------------------------
 * 		pId		|		pName		|
 * ----------------------------------
 * 		1		|		zhangsan	|			
 * ----------------------------------
 * 		2		|		lisi		|			
 * ----------------------------------
 */
public class ManyToOneTest {

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
	public void createTable() {
		new SchemaExport(new Configuration().configure()).create(true, true);
	}
	
	
	@Test
	public void insertTest(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Group group = new Group();
		group.setName("系统管理员");
		session.save(group);//先持久化 一 的一方。
		
		User user = new User();
		user.setName("zhangsan");
		user.setGroup(group);
		
		User user2 = new User();
		user2.setName("lisi");
		user2.setGroup(group);
		
		
		/*
		 * 如果session.save(group)不执行，则存储不成功，抛出TransientObjectException异常
		 * 因为Group为 Transient 状态，没有OID。
		 * 结论：：：persistent状态的对象不能引用Transient状态的对象。
		 * 
		 * 利用Cascade属性是解决TransientObjectException一场的一种手段。
		 * 注意：：：Cascade只是帮我们省去了硬编码的麻烦而已，不要把它的作用过度放大。
		 */
		session.save(user2);
		session.save(user);
		
		tx.commit();
	}
	
	@Test
	public void getTest(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		User user = (User) session.load(User.class, 1);
		System.out.println("user.name=" + user.getName());
		System.out.println("user.group=" + user.getGroup().getName());
		
		tx.commit();
	}
}
