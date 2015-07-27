package com.startcaft.hibernate.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Teacher;
import com.startcaft.hibernate.entity.ZhiCheng;
import com.startcaft.hibernate.util.HibernateUtil;

public class SaveAndDeleteTest {

	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void initSessionFactory() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	/**
	 * Hibernate�ж��������״̬������
	 * 1����û��id��
	 * 2��
	 * 
	 * Session���ڴ��е�һ������
	 * �����������һ��Map����
	 * Map��key���ǳ־û������OID(����ID)
	 * Map��value���ǳ־û���������á�
	 * 
	 * ����Session��һ�����档
	 */
	@Test
	public void testSaveTeacher() {

		Teacher t = new Teacher();//���� t Ϊ transient ��ʱ״̬������session�������ݿ���û�ж�Ӧ��¼
		
		t.setName("t1");
		t.setCreateTime(new Date());
		t.setZhiCheng(ZhiCheng.A);
		
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(t);
		System.out.println(t.getId());//���� t Ϊ persistent �־û�״̬�����ݿ����ж�Ӧ�ļ�¼(Session�Ļ������������)
		session.getTransaction().commit();
		
		
		System.out.println(t.getId());//���� t Ϊ detached �й�״̬�����ݿ����ж�Ӧ��¼,������������session����
		
	}
	
	/**
	 * Deleteһ������������������OID(Ҳ��������ID)
	 */
	@Test
	public void testDelete(){
		
		Teacher t = new Teacher();
		
		t.setName("t1");
		t.setCreateTime(new Date());
		t.setZhiCheng(ZhiCheng.A);
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(t);
		System.out.println(t.getId());
		session.getTransaction().commit();
		
		Session session2 = sessionFactory.getCurrentSession();
		session2.beginTransaction();
		session2.delete(t);
		session2.getTransaction().commit();
	}
	
	@Test
	public void testDelete2(){
		
		Teacher t = new Teacher();
		t.setId(2);//ֻҪ��id�ţ��Ϳ���ɾ����
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(t);
		session.getTransaction().commit();
	}

	@AfterClass
	public static void free() {
		sessionFactory.close();
	}
}
