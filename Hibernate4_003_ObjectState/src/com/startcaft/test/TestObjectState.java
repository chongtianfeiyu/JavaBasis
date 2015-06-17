package com.startcaft.test;

import java.util.Date;

import org.hamcrest.Matchers;
import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.startcaft.model.News;

public class TestObjectState {

	private SessionFactory sFactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void init() {

		Configuration config = new Configuration().configure();
		ServiceRegistry service = new ServiceRegistryBuilder().applySettings(
				config.getProperties()).buildServiceRegistry();
		sFactory = config.buildSessionFactory(service);

		session = sFactory.openSession();

		transaction = session.beginTransaction();
	}

	@After
	public void destory() {

		transaction.commit();
		session.close();
		sFactory.close();
	}
	
	
	/**
	 * get VS load:
	 * 
	 * 1.执行get方法：会立即加载对象，
	 *   执行load方法，若不实用该对象，则不会立即执行查询操作，而返回一个代理对象。
	 *   
	 *   get 是立即检索，而load是延迟加载。
	 *   
	 *   
	 * 2.若数据表中没有对应的记录.
	 * 	 get 返回null
	 *   load 抛出异常(因为代理对象的关系)，若不使用该对象的任何属性，则不会抛异常
	 *   
	 * 
	 * 3.load 方法可能会抛出LazyInitializationException异常:
	 *   在需要初始化代理对象之前如果已经关闭了Sesssion就会
	 * 
	 */
	@Test
	public void testGet(){
		News news = (News)session.get(News.class, 1);
		//Assert.assertThat(news, Matchers.notNullValue());
		
		session.close();
		
		System.out.println(news);
	}
	@Test
	public void testLoad(){
		News news = (News)session.load(News.class, 1);
		//System.out.println(news);不使用对象
		
		
		session.close();
		
		System.out.println(news);
	}
	
	
	/**
	 * persist():也会执行insert操作。
	 * 
	 * 和save()的区别：
	 * 在调用persist方法之前，若对象已经有id了，则不会执行insert，而会抛出异常。
	 */
	@Test
	public void testPersist(){
		News news = new News();
		news.setAuthor("DD");
		news.setTitle("dd");
		news.setCreateTime(new Date());
		//news.setId(200);这里会抛出异常
		
		session.persist(news);
	}
	
	/**
	 * 1.save()方法
	 * ---1).使一个临时对象变为持久化对象
	 * ---2).为对象分配OID
	 * ---3).在flush缓存时，会发送一条insert语句。
	 * ---4).在save方法之前设置的id是无效的。
	 * ---5).持久化对象的ID是不能被修改的。
	 */
	@Test
	public void testSave(){
		News news = new News();
		news.setAuthor("BB");
		news.setTitle("bb");
		news.setCreateTime(new Date());
		
		System.out.println(news);
		
		//save之前 ,主键ID是没有的
		Assert.assertThat(0, Matchers.is(news.getId()));
		
		session.save(news);
		
		
		Assert.assertThat(0,Matchers.not(news.getId()));
	}
}
