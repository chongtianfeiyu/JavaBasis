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
		user.setName("张三");
		
		Transaction transaction = session.beginTransaction();// 开始事务
		session.save(user);
		transaction.commit();
		
		session.close();
		sf.close();
		
		System.out.println("done");
	}

}
