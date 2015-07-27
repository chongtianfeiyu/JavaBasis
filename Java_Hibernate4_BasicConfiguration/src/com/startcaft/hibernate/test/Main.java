package com.startcaft.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.startcaft.hibernate.entity.User;
import com.startcaft.hibernate.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		User user = new User();
		user.setName("����");
		
		Transaction transaction = session.beginTransaction();// ��ʼ����
		session.save(user);
		transaction.commit();
		
		session.close();
		sf.close();
		
		System.out.println("done");
	}

}
