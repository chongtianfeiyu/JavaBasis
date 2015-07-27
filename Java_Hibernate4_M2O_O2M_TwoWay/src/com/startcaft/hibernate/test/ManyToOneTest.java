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
 * һ�Զ� ���һ ˫�� �ܼ򵥣�
 * ע�⣺������˫���������ϵ��ά���ѷ�һ���Ƕ��һ����
 * ��Ϊ����������һ������Ԫ�׼�ס��������������ǲ����ܵģ�ֻ�������е������ס����Ԫ�׵�����
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
		group.setName("ϵͳ����Ա");
		session.save(group);//�ȳ־û� һ ��һ����
		
		User user = new User();
		user.setName("zhangsan");
		user.setGroup(group);
		
		User user2 = new User();
		user2.setName("lisi");
		user2.setGroup(group);
		
		
		/*
		 * ���session.save(group)��ִ�У���洢���ɹ����׳�TransientObjectException�쳣
		 * ��ΪGroupΪ Transient ״̬��û��OID��
		 * ���ۣ�����persistent״̬�Ķ���������Transient״̬�Ķ���
		 * 
		 * ����Cascade�����ǽ��TransientObjectExceptionһ����һ���ֶΡ�
		 * ע�⣺����Cascadeֻ�ǰ�����ʡȥ��Ӳ������鷳���ѣ���Ҫ���������ù��ȷŴ�
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
