package com.startcaft.hibernate.test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Teacher;
import com.startcaft.hibernate.util.HibernateUtil;

public class UpdateTest {

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
	 * update 更新：
	 * 1，用来更新 detached 对象，更新完成后，状态为persistent。
	 * 2，更新 transient 对象会报错，
	 * 3，如果更新自己设定的oid的transient对象可以(前提是数据库中有对应的记录)。
	 * 4，persitent 状态下的对象，只要修改对象的属性，事务提交就会自动发送update语句。
	 * 5，更新部分更改的字段：
	 * ----@Column(updatable=false),可以设置属性，可以设置字段不参与update。【不灵活，不推荐使用】
	 * ----在实体配置文件.hbm.xml的class节点下设置dynamic-update="true";
	 * 	       类级注解@DynamicUpdate(true);不是jpa的定义，是Hibernate的注解的扩展。
	 * 
	 * ----【推荐使用HQL语句。】
	 * 
	 * 6，跨session更新的情况，主要看缓存中有没有对象可比。可以使用merge()方法。【了解即可】
	 */
	@Test
	public void testUpdate1(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Teacher t = (Teacher) session.get(Teacher.class, 1);
		
		session.getTransaction().commit();
		
		t.setName("teacher_name");//托管状态了。
		
		
		Session session2 = sessionFactory.getCurrentSession();
		session2.beginTransaction();
		session2.update(t);
		
		session2.getTransaction().commit();
	}
	
	@Test
	public void testUpdate2(){
		
		Teacher t = new Teacher();
		t.setName("zhangsan");//transient 状态 却没有设置id，无法更新。
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(t);
		session.getTransaction().commit();
	}
	
	@Test
	public void testUpdate3(){
		
		Teacher t = new Teacher();
		t.setId(1);
		t.setName("startcaft");//transient 状态  手动设置id，数据库有对应数据，可以更新。
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(t);
		session.getTransaction().commit();
	}
	
	@Test
	public void testUpdate4(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Teacher t = (Teacher) session.get(Teacher.class, 1);//持久化 状态,缓存与数据库要同步。
		
		t.setName("zz4");
		
		session.getTransaction().commit();
	}
	
	@Test
	public void testUpdate5(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Teacher t = (Teacher) session.get(Teacher.class, 1);//持久化 状态,缓存与数据库要同步。
		t.setName("z4");//部分更新，跟缓存中的对象进行比较。改谁 更新谁。
		session.getTransaction().commit();
		
		t.setName("bwm");//detached 对象
		
		Session session2 = sessionFactory.getCurrentSession();
		session2.beginTransaction();
		session2.update(t);//全部修改，因为缓存中没有对象，没得比较，所以全部更新。
		session2.getTransaction().commit();
	}
	
	@Test
	public void testUpdate6(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Teacher t = (Teacher) session.get(Teacher.class, 1);//持久化 状态,缓存与数据库要同步。
		t.setName("z4");//部分更新，跟缓存中的对象进行比较。改谁 更新谁。
		session.getTransaction().commit();
		
		t.setName("bwm");//detached 对象
		
		Session session2 = sessionFactory.getCurrentSession();
		session2.beginTransaction();
		session2.merge(t);//只更新一项了。但在update之前，有一条select语句【作用是用于比较，因为缓存中没有对应的对象。】
		session2.getTransaction().commit();
	}
	
	@Test
	public void testUpdate7(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query =session.createQuery("update Teacher t set t.name='z5' where id=1");
		query.executeUpdate();
	}
}
