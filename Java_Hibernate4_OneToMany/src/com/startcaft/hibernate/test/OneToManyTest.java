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
 * 多对一，一对多的区别：
 * 多对一关联映射：：：在多的一端加入一个外键指向一的一端，它的维护关系是 多指向一。
 * 一对多关联映射：：：在多的一端加入一个外键指向一的一端，它的维护关系是 一指向多。
 * 两者使用的策略师一样的，只是各自所占的角度不同。
 * 
 * 一对多 在多的一段只需要正常注解就行；
 * 需要在 一的一段进行一对多的关系注解；
 * 
 * 在一的一端的Set<>属性使用@OneToMany注解，并使用@JoinColumn来指定外键(必须使用该注解，不然产生中间表)
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
		
		//因为是在一的一端维护关系，这样会发出多余的更新语句，这样在批量数据时，效率不高。
	    //还有一个，当在多的一端的那个外键设置为非空时，则在添加多的一端数据时会发生错误，数据存储不成功。
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
		claz.setName("一号教室");
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
