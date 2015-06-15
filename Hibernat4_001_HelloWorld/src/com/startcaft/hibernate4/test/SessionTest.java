package com.startcaft.hibernate4.test;

import java.sql.Date;

import org.hamcrest.Matchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.startcaft.hibernat4.model.News;

public class SessionTest {

	/**
	 * Hibernate4中进行Session操作的基本步骤
	 */
	@Test
	public void testInserNews() {
		
		/*
		 * SessionFactory是线程安全的，它是生成Session的工程，在构造SessionFactory时
		 * 会比较消耗系统资源，一般情况下一个应用程序之初始化一个SessionFactory即可。
		 */
		SessionFactory sFactory = null;

		// 1，获取Configuation对象：它表示Hibernate的基本配置信息和关系映射配置信息
		Configuration config = new Configuration().configure();

		// 2，创建ServiceRegistry对象：Hibernate4.0之后新加入的类
		// Hibernate任何的服务和配置都需要通过ServiceRegistry进行注册才能使用。
		ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(
				config.getProperties()).buildServiceRegistry();

		// ****Hibernate4.x之前是通过Configuration对象创建SessionFactoryde。
		// sFactory = config.buildSessionFactory();
		// 3，通过注册的Configuation对象创建SessionFactory
		sFactory = config.buildSessionFactory(registry);

		// 4，创建Session对象
		Session session = sFactory.openSession();

		// 5，开启一个事物
		Transaction transaction = session.beginTransaction();

		// 6.执行数据库操作
		session.save(new News("Java", "java nice", new Date(
				new java.util.Date().getTime())));
		
		//7，提交事物
		transaction.commit();
		
		//8，清理资源
		session.close();
		sFactory.close();
	}
	
	/**
	 * Hibernate底层是通过反射机制来获取一个指定类.clas和OID的实体对象。
	 * 一般情况下，需要为Hibernate提供一个无参数的构造函数。
	 * 
	 * 而且还需要实体类是一个非final类，否则Hibernate无法为其生成代理类(很重要)
	 * 
	 * 有时候需要重写eqauls和hashCode方法，主要是要把持久化类放到一个set中(关联对象时候用到)
	 */
	@Test
	public void testGetObject() {

		SessionFactory sFactory = null;

		// 1，获取Configuation对象：它表示Hibernate的基本配置信息和关系映射配置信息
		Configuration config = new Configuration().configure();

		// 2，创建ServiceRegistry对象：Hibernate4.0之后新加入的类
		// Hibernate任何的服务和配置都需要通过ServiceRegistry进行注册才能使用。
		ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(
				config.getProperties()).buildServiceRegistry();

		// ****Hibernate4.x之前是通过Configuration对象创建SessionFactoryde。
		// sFactory = config.buildSessionFactory();
		// 3，通过注册的Configuation对象创建SessionFactory
		sFactory = config.buildSessionFactory(registry);

		// 4，创建Session对象
		Session session = sFactory.openSession();

		// 5，开启一个事物
		Transaction transaction = session.beginTransaction();

		// 6.执行数据库操作
		session.save(new News("Java", "java nice", new Date(
				new java.util.Date().getTime())));
		
		News newObject = (News)session.get(News.class, 1);
		
		Assert.assertThat("Java", Matchers.is(newObject.getTitle()));
		
		//7，提交事物
		transaction.commit();
		
		//8，清理资源
		session.close();
		sFactory.close();
	}
}
