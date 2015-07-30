package com.startcaft.hibernate.test;

import java.util.HashSet;
import java.util.Set;

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
 * Cascade纯粹的是为了减少代码量，并没有太多的意义。
 * 
 * 规律总结：
 * 1，数据库中的双向关系，必须在程序中设置双向关联
 * 2，双向关联中，设定好mappedBy。
 */
public class CascadeTest {

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

	/**
	 * 把关联的两个实体对象，当作单独的两个对象save。可以保存但是没有外键关联。
	 */
	@Test
	public void saveAsTwoEntityObject() {
		User user = new User();
		user.setName("张三");

		Group group = new Group();
		group.setName("管理员");

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.save(group);
		session.save(user);

		tx.commit();
	}

	/**
	 * 把关联的两个实体对象，当作单独的两个对象save,并且设置两个对象之间的关联关系，OK没问题。
	 * 但是，既然是有关联关系的两个对象，为什么要save两次呢？
	 */
	@Test
	public void saveNoCascade() {
		User user = new User();
		user.setName("张三");

		Group group = new Group();
		group.setName("管理员");

		user.setGroup(group);// 设置关联管理

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		session.save(group);
		session.save(user);

		tx.commit();
	}

	/**
	 * 在ManyToOne添加Cascade属性;
	 * 只保存User【多的一方】，其相关联的Group【一的一方】也一并被保存，当然，也要设置它们的关联关系
	 */
	@Test
	public void saveCascadeByMany() {
		User user = new User();
		user.setName("张三");

		Group group = new Group();
		group.setName("管理员");

		user.setGroup(group);// 设置关联管理

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// User对象，其相关联的Group对象一并保存。
		session.save(user);

		tx.commit();
	}

	/**
	 * 在OneToMany添加Cascade属性;
	 * 只保存Group【一的一方】，其相关联的Users【多的一方】也一并被保存，当然，也要设置它们的关联关系。
	 * 
	 * 但是在 一的一方操作 比较麻烦。
	 */
	@Test
	public void saveCascadeByOne() {
		User user = new User();
		user.setName("张三");

		User user2 = new User();
		user2.setName("李四");

		User user3 = new User();
		user3.setName("王五");

		Group group = new Group();
		group.setName("管理员");

		// 从一的一方的角度设置关联关系
		Set<User> users = new HashSet<User>();
		users.add(user);
		users.add(user2);
		users.add(user3);
		group.setUsers(users);

		// 站在Hibernate的角度，在保存User的时候，必须知道Group的引用。
		// 所以，Hibernate的一个特点就是：从 多的一方 操作比较简单；从 一的一方 操作比较复杂。
		user.setGroup(group);
		user2.setGroup(group);
		user3.setGroup(group);

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// User对象，其相关联的Group对象一并保存。
		session.save(group);

		tx.commit();
	}
}
