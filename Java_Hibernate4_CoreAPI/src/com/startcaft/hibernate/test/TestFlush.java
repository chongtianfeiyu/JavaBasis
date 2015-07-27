package com.startcaft.hibernate.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.hibernate.entity.Teacher;
import com.startcaft.hibernate.util.HibernateUtil;

public class TestFlush {

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
	public void testFlush(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Teacher t = (Teacher) session.load(Teacher.class, 1);//�־û�״̬���������У����ݿ���Ҳ��
		
		t.setName("ttt");
		
		session.flush();//ǿ���û��������ݿ�����ͬ����
		
		t.setName("tttt");
		
		session.getTransaction().commit();//commitĬ�Ͼͻ�flush������ʹ��Session.setFlushMod()����flush��ʱ�������˽⼴�ɡ���
	}

}
