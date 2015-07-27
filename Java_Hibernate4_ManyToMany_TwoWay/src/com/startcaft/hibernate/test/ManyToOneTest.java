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
 * ������һ���� �ж�����룬ÿ����������붼ֻ����һ���ˡ�
 * 
 * ���ݿ�����ƣ�
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
 * 		1		|		׬�ܶ�Ǯ		|			
 * ----------------------------------
 * 		2		|		�Ժܶ���ʳ		|			
 * ----------------------------------
 * ���ϵ� ���ݿ���� �ڡ�һ��һ�������л�����������ࡣ���ԣ���ManyToOne�����ʱ�������Ӧ�ü��ڶ��һ����
 * 
 * ------------------------------------------------------
 * 		dId		|		dDesc		|			pId
 * ------------------------------------------------------
 * 		1		|		׬�ܶ�Ǯ		|			1
 * ------------------------------------------------------
 * 		2		|		�Ժܶ���ʳ		|			1
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
	public void getTest(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Group group = (Group) session.load(Group.class, 1);
		System.out.println("role.name=" + group.getName());
		for(Iterator<User> iter = group.getUsers().iterator();iter.hasNext();){
            User user = iter.next();
            System.out.println("user.name=" + user.getName());
        }
		
		tx.commit();
	}
}
