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

import com.startcaft.hibernate.entity.Classess;
import com.startcaft.hibernate.entity.Student;
import com.startcaft.hibernate.util.HibernateUtil;



/*
 * ���һ��һ�Զ������
 * ���һ����ӳ�䣺�����ڶ��һ�˼���һ�����ָ��һ��һ�ˣ�����ά����ϵ�� ��ָ��һ��
 * һ�Զ����ӳ�䣺�����ڶ��һ�˼���һ�����ָ��һ��һ�ˣ�����ά����ϵ�� һָ��ࡣ
 * ����ʹ�õĲ���ʦһ���ģ�ֻ�Ǹ�����ռ�ĽǶȲ�ͬ��
 * 
 * һ�Զ� �ڶ��һ��ֻ��Ҫ����ע����У�
 * ��Ҫ�� һ��һ�ν���һ�Զ�Ĺ�ϵע�⣻
 * 
 * ��һ��һ�˵�Set<>����ʹ��@OneToManyע�⣬��ʹ��@JoinColumn��ָ�����(����ʹ�ø�ע�⣬��Ȼ�����м��)
 */
public class OneToManyTest {

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
	 * 
	 */
	@Test
	public void insertTest(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		//��Ϊ����һ��һ��ά����ϵ�������ᷢ������ĸ�����䣬��������������ʱ��Ч�ʲ��ߡ�
	    //����һ�������ڶ��һ�˵��Ǹ��������Ϊ�ǿ�ʱ��������Ӷ��һ������ʱ�ᷢ���������ݴ洢���ɹ���
		Student stu1 = new Student();
		stu1.setName("zhangsan");
		session.save(stu1);
		
		Student stu2 = new Student();
		stu2.setName("lisi");
		session.save(stu2);
		
		Set<Student> stus = new HashSet<Student>();
		stus.add(stu2);
		stus.add(stu1);
		
		Classess claz = new Classess();
		claz.setName("һ�Ž���");
		claz.setStudents(stus);
		
		session.save(claz);
		tx.commit();
	}
	
	@Test
	public void getTest(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Classess claz = (Classess) session.load(Classess.class, 2);
		System.out.println("classess.name=" + claz.getName());
		Set<Student> stus = claz.getStudents();
		for(Iterator<Student> it = stus.iterator();it.hasNext();){
			Student stu = it.next();
			System.out.println(stu.getName());
		}
		
		tx.commit();
	}
}
