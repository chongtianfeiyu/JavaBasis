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
		user.setName("张三");

		// openSession() 每次都是新的Session对象,需要手动close()
		Session session = HibernateUtil.getSessionFactory().openSession();// 开打一个新的Session，类似打开一个新的Connection
		Transaction transaction = session.beginTransaction();// 开始事务

		session.save(user);
		
		
		Session session2 = HibernateUtil.getSessionFactory().openSession();
		System.out.println(session == session2);// false 两个不同的数据库连接。

		transaction.commit();// 提交事务
		session.close();

		

	}

	@Test
	public void testSaveWithAnnotation() throws Exception {

		Teacher teacher = new Teacher();
		teacher.setName("t1");
		teacher.setTitle("中级");
		teacher.setZhiCheng(ZhiCheng.A);
		teacher.setCreateTime(new Date());

		/*
		 * getCurrentSession() 从上下文中找，如果有，用旧的，如果没有，则新建。事务提交自动close
		 * ----可以用来界定事务边界。
		 */
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();// 开打一个新的Session，类似打开一个新的Connection
		Transaction transaction = session.beginTransaction();// 开始事务

		session.save(teacher);
		
		Session session2 = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		System.out.println(session == session2);// true 在session提交之前，无论怎么获取 session都是一样的。一旦session提交，获取到的就是新的session。
		
		Session session3 = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		System.out.println(session == session3);// true 在session提交之前，无论怎么获取 session都是一样的。一旦session提交，获取到的就是新的session。

		transaction.commit();// 提交事务
		
		
		Session session4 = HibernateUtil.getSessionFactory()
				.getCurrentSession();
		System.out.println(session == session4);// false 事务提交，自动close掉了。一旦session提交，获取到的就是新的session。
	}
}
