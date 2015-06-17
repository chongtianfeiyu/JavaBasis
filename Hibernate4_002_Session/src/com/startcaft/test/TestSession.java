package com.startcaft.test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.startcaft.model.News;


/**
 * 在Session接口的实现中包含了一系列的Java集合，这些Java集合构成了Session缓存，
 * 只要Session实例没有结束生命周期，【且没有清理缓存】。则存放在缓存中的对象也不会
 * 结束生命周期，这个位于Session的缓存成为【一级缓存】
 * 
 * 
 * Hibernate提供了三种方法来对Session缓存进行操作：
 * 1,flush()------会强制同步数据库中的数据与Session缓存中的对象状态一致。
 * 2,
 * 
 * @author startcaft
 *
 */
public class TestSession {

	private SessionFactory sFactory;
	private Session session;
	private Transaction transaction;
	
	
	@Before
	public void init(){
		
		Configuration config = new Configuration().configure();
		ServiceRegistry service = new ServiceRegistryBuilder()
								.applySettings(config.getProperties())
								.buildServiceRegistry();
		sFactory = config.buildSessionFactory(service);
		
		session = sFactory.openSession();
		
		transaction = session.beginTransaction();
	}

	@After
	public void destory(){
		
		transaction.commit();
		session.close();
		sFactory.close();
	}
	
	@Test
	public void testSessionCache(){
		
		//这里会发送一条SQL语句
		News news1 = (News)session.get(News.class, 1);
		System.out.println(news1);
		
		
		//这里不会发送SQL语句，是从一级缓存中获取的。
		News news2 = (News)session.get(News.class, 1);
		System.out.println(news2);
	}
	
	
	/**
	 * clear()：清理Session缓存。
	 */
	@Test
	public void testSessionClear(){
		News news1 = (News) session.get(News.class, 1);
		
		session.clear();//清理缓存
		
		//这里又会发送一条select语句。
		News news2 = (News) session.get(News.class, 1);
	}
	
	
	/**
	 * refresh():会强制发送select语句，以使Session缓存中对象的状态
	 * 和数据表中对应的记录保持一致!
	 * 
	 * 如果没有更新缓存中的对象状态，请在hibernate.cfg.xml配置文件中设置事物隔离级别
	 * <property name="hibernate.connection.isolation">2</property>
	 */
	@Test
	public void testSessionRefresh(){
		News news = (News) session.get(News.class, 1);
		System.out.println(news);
		
		session.refresh(news);
		System.out.println(news);
	}
	
	
	/**
	 * flush:使数据库表中的记录和Session缓存中的对象的状态保持一致，
	 * 为了保持一致，则可能会发送对应的update语句。
	 * 
	 * 1，调用Transaction的commit()方法中：先调用session的flush方法
	 * 								在进行提交事务。
	 * 2，flush()方法可能会发送update语句，但不会提交事务。
	 * 
	 * 注意：
	 * 1），在未提交事物或显示的调用session.flush()方法之前，也有可能进行flush()操作。
	 * -----1),执行HQL或者QBC查询，会先进行flush操作，以得到数据表的最新的记录
	 * -----2),若记录的ID 是由底层数据库使用自增的方式生成的，则在调用以后save()
	 * 		方法以后，就会立即发送insert语句。因为save方法后，必须保证对象的ID是存在的!
	 */
	@Test
	public void testSessionFlush(){
		
		//发送一条查询语句
		News news = (News)session.get(News.class, 1);
		
		//这里的操作包含在事物中的，在事物提交时候，会调用flush方法，强制同步数据。所以会发送update语句。
		news.setAuthor("SUN");
		
		//显示调用session.flush(),仅会发送sql语句，但不更改数据，因为没有提交事务。
		//session.flush();
		//System.out.println("flush");
		
		
		//这里执行一个查询，必须保证我查到的数据是最新的，所以也会发送update语句，但是没有提交事务。
		News news2 = (News) session.createCriteria(News.class).uniqueResult();
		System.out.println(news2);
	}
}
