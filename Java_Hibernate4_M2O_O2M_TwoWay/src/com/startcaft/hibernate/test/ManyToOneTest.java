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
 * 一对多 多对一 双向 很简单，
 * 注意：但凡是双向关联，关系的维护费方一定是多的一方。
 * 因为：：：你让一个国家元首记住所有人民的名字是不可能的，只有让所有的人民记住国家元首的名字
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
