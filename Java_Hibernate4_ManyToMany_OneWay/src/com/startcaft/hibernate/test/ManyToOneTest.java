package com.startcaft.hibernate.test;


import java.util.HashSet;
import java.util.Iterator;
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
		group.setName("数据录入员");
		session.save(group);
		
		Group group1 = new Group();
		group1.setName("商务主管");
		session.save(group1);
		
		Group group2 = new Group();
		group2.setName("区域经理");
		session.save(group2);
		
		User u1 = new User();
		u1.setName("zhangsan");
		Set<Group> groups = new HashSet<Group>();
		groups.add(group);
		groups.add(group1);
		groups.add(group2);
		u1.setGroups(groups);
		
		User u2 = new User();
		u1.setName("lisi");
		Set<Group> groups2 = new HashSet<Group>();
		groups2.add(group1);
		groups2.add(group2);
		u2.setGroups(groups2);
		
		session.save(u1);
		session.save(u2);
		
		tx.commit();
	}
	
	@Test
	public void getTest(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		User user =  (User) session.load(User.class, 1);
		System.out.println("user.name=" + user.getName());
		for(Iterator<Group> it = user.getGroups().iterator();it.hasNext();){
			Group group = it.next();
			System.out.println(group.getName());
		}
		
		tx.commit();
	}
}
