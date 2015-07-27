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
 * ���ݿ��֮��Ĺ�ϵֻ�� �������ϵ��
 * 
 * ������˵�Ĺ�ϵ�� ���� �� ���� ֮��Ĺ�����ϵ�ǣ�������ϵ
 * 
 * ������ϵ��Ϊ���֣�1��һ��һ��2��һ�Զࣻ3��Զ�
 * 
 * 
 * һ��һ���������������
 * 1�������Ķ��󶼹�����ͬ��������
 * 2��һ������ͨ���������������һ�������������
 * 3��ͨ���м��������������������֮��Ĺ�ϵ(����Ҫ)��
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

		student.setStudentCard(sCard);// ���ù�����ϵ

		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(student);
		session.getTransaction().commit();

	}

	@Test
	public void queryTest() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		Student s = (Student) session.get(Student.class, 1);// ����һ����ѯStudent��SQL��䡣

		System.out.println(s.getName());

		StudentCard sCard = s.getStudentCard();

		System.out.println(sCard.getDate());// ��Ϊ�������õ�fetch=FecthType.LAZY��������������ʹ�õ����������ʱ�򣬲Żᷢ��SQL��䡣
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
		
		//����Ҫ����ģ���ɾ���ӱ���ɾ������
//		StudentCard sCard = student.getStudentCard();
//		session.delete(sCard);
		
		
		session.delete(student);//��Ϊ�����˼���������һ��ɾ��
		session.getTransaction().commit();
	}
}
