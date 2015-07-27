package com.startcaft.hibernate.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Student;
import com.startcaft.hibernate.entity.StudentCard;
import com.startcaft.hibernate.util.HibernateUtil;

/*
 * 数据库表之间的关系只有 【外键关系】
 * 
 * 这里所说的关系是 对象 与 对象 之间的关联关系是：数量关系
 * 
 * 数量关系分为三种：1，一对一；2，一对多；3多对多
 * 
 * 
 * 一对一关联有三种情况：
 * 1，关联的对象都共享相同的主键；
 * 2，一个对象通过外键关联到另外一个对象的主键；
 * 3，通过中间关联表来保存两个对象之间的关系(不重要)；
 */
public class OneByOneTest {

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
	public void insertTest() {
		Student student = new Student();
		student.setName("fancy");

		StudentCard sCard = new StudentCard();
		sCard.setDate(new Date());

		student.setStudentCard(sCard);// 设置关联关系

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();

	}

	@Test
	public void queryTest() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Student s = (Student) session.get(Student.class, 1);// 发送一条查询Student的SQL语句。

		System.out.println(s.getName());

		StudentCard sCard = s.getStudentCard();

		System.out.println(sCard.getDate());// 因为我们设置的fetch=FecthType.LAZY，所以这里真正使用到关联对象的时候，才会发送SQL语句。
	}

	@Test
	public void updateTest() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Student s = (Student) session.get(Student.class, 1);
		s.setName("fancydeepin");

		StudentCard studentCard = s.getStudentCard();
		studentCard.setDate(new Date());
		
		s.setStudentCard(studentCard);
		
		session.update(s);
		session.getTransaction().commit();
	}
	
	@Test
	public void deleteTest(){
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Student student = (Student) session.get(Student.class, 1);
		
		//这是要报错的，先删除从表，再删除主表。
//		StudentCard sCard = student.getStudentCard();
//		session.delete(sCard);
		
		
		session.delete(student);//因为设置了级联，所以一并删除
		session.getTransaction().commit();
	}
}
