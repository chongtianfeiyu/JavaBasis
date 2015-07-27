package com.startcaft.hibernate.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.startcaft.hibernate.entity.Teacher;
import com.startcaft.hibernate.entity.User;
import com.startcaft.hibernate.entity.ZhiCheng;
import com.startcaft.hibernate.util.HibernateUtil;

public class UserTest {

	@Test
	public void testSave() throws Exception {

		User user = new User();
		user.setName("����");

		// openSession() ÿ�ζ����µ�Session����,��Ҫ�ֶ�close()
		Session session = HibernateUtil.getSessionFactory().openSession();// ����һ���µ�Session�����ƴ�һ���µ�Connection
		Transaction transaction = session.beginTransaction();// ��ʼ����

		session.save(user);
		
		
		Session session2 = HibernateUtil.getSessionFactory().openSession();
		System.out.println(session == session2);// false ������ͬ�����ݿ����ӡ�

		transaction.commit();// �ύ����
		session.close();

		

	}

	@Test
	public void testSaveWithAnnotation() throws Exception {

		Teacher teacher = new Teacher();
		teacher.setName("t1");
		teacher.setTitle("�м�");
		teacher.setZhiCheng(ZhiCheng.A);
		teacher.setCreateTime(new Date());

		/*
		 * getCurrentSession() �����������ң�����У��þɵģ����û�У����½��������ύ�Զ�close
		 * ----���������綨����߽硣
		 */
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();// ����һ���µ�Session�����ƴ�һ���µ�Connection
		Transaction transaction = session.beginTransaction();// ��ʼ����

		session.save(teacher);
		
		Session session2 = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		System.out.println(session == session2);// true ��session�ύ֮ǰ��������ô��ȡ session����һ���ġ�һ��session�ύ����ȡ���ľ����µ�session��
		
		Session session3 = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		System.out.println(session == session3);// true ��session�ύ֮ǰ��������ô��ȡ session����һ���ġ�һ��session�ύ����ȡ���ľ����µ�session��

		transaction.commit();// �ύ����
		
		
		Session session4 = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		System.out.println(session == session4);// false �����ύ���Զ�close���ˡ�һ��session�ύ����ȡ���ľ����µ�session��
	}
}
