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
 * Cascade ��CUD
 * Fetch ��R
 * ----@OneToMany ��FetchĬ��ΪLazy
 * ----@ManyToOne ��FetchĬ����EAGER
 * ----��˫�������ϵʱ������fetch��ʱ��Ҫ������һ����Lazy������eager��
 * 		����˫��������Lazy������Ҫ˫��������eager����Ȼ���ж���Ĳ�ѯ��䡿��
 * 
 * �κ�һ�� ���� ע�� ����Cascade��Fetch���ԡ�
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
	 * ManyToOne �����費����Cascade ��ȡ���һ�˵� ����ʱ��һ��һ��Ҳ��Ĭ�ϱ�ȡ����
	 */
	@Test
	public void getUserTest() {

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		User user = (User) session.get(User.class, 1);

		tx.commit();
	}
	
	/**
	 * OneToMany �����費����Cascade ��ȡһ��һ�� ����ʱ�򣬶��һ��������Ĭ�ϲ��ᱻȡ������ΪЧ�ʡ���
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
		
		System.out.println(user.getGroup().getName());//�����ȡ��������һ��Ҫ��һ��session�С�

		tx.commit();

		//System.out.println(user.getGroup().getName());//LazyInitializationException�쳣
	}
	
	@Test
	public void loadUserLazyTest(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		User user = (User) session.load(User.class, 1);
		
		System.out.println(user.getGroup().getName());//���﷢��2��SQL��䡣һ��Ҫ��һ��session�С�

		tx.commit();

		//System.out.println(user.getGroup().getName());//LazyInitializationException�쳣
	}
	
	@Test
	public void updateUserTest(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		User user = (User) session.get(User.class, 1);

		tx.commit();
		
		
		//�й�״̬�Ķ���
		user.setName("zhangsan");
		user.getGroup().setName("admin");
		
		
		//�ڶ���session
		Session session2 = sessionFactory.getCurrentSession();
		Transaction tx2 = session2.beginTransaction();
		
		session2.update(user);//�����йܶ���

		tx2.commit();
	}
}
