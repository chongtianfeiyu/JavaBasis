package com.startcaft.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.startcaft.model.News;

public class TestObjectState {
	
	private SessionFactory sFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init(){
		
		Configuration configuration = new Configuration().configure();
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
										.applySettings(configuration.getProperties())
										.buildServiceRegistry();
		
		sFactory = configuration.buildSessionFactory(serviceRegistry);
		
		session = sFactory.openSession();
		
		transaction = session.beginTransaction();
	}
	
	@After
	public void destory(){
		
		transaction.commit();
		session.close();
		sFactory.close();
	}

	
	/**
	 * update:
	 * 1,若更新一个持久化对象，不需要显示的调用update方法，
	 * 因为在调用Transaction的commit方法时，会先执行session的flush方法(同步)。
	 * 
	 * 2,更新一个游离对象(数据库有，缓存没有)，需要显示的调用session的update方法,
	 * 可以把一个游离对象变成持久化对象。
	 * 
	 * 需要注意的：
	 * 1，无论更新的游离对象和数据表的记录是否一致，都会发送update语句。
	 * 		如何能让update方法不再盲目的发送update语句呢？
	 * 		在.hbm.xml文件的class节点设置select-before-update="true"(默认为false)，
	 * 		通常不需要设置该属性。(除非触发器跟update有协作)
	 * 
	 * 2,若数据库表中没有对应的记录，但还调用了update方法，会抛出异常。
	 * 
	 * 3,当update()方法关联一个游离对象时，
	 * 如果在Session的缓存中已经存在相同OID的持久化对象，会抛出异常，
	 * 因为在Session缓存中不能有两个OID相同的对象。
	 */
	@Test
	public void testUpdate(){
		
		News news = (News) session.get(News.class, 1);
		
		transaction.commit();
		session.close();
		
		//news.setId(1000);
		
		session = sFactory.openSession();
		transaction = session.beginTransaction();
		
		//news.setAuthor("Sun");
		
		News news2 = (News) session.get(News.class, 1);
		session.update(news);
	}

	
	/**
	 * Session的saveOrUpdate方法同时包含了save()和update()的功能，
	 * 临时对象则save
	 * 游离对象则update
	 * 
	 * 判定对象为临时对象的标准：
	 * 1，Java对象的OID为null
	 * 2，映射文件中<id>设置了unsaved-value属性，并且Java对象的OID与unsaved-value
	 * 的属性值相同时
	 * 
	 * 
	 * 注意：
	 * 1，若OID不为null，但数据表中还没有和其对应的记录，则会抛出一个异常。
	 * 2，【了解】，OID值等于id的unsaved-value属性的对象，也被认为是一个游离对象。
	 */
	@Test
	public void testSaveOrUpdate(){
		
		News news = new News();
		news.setAuthor("FF");
		news.setTitle("ff");
		news.setCreateTime(new Date());
		
		//news.setId(1);
	
		session.save(news);
	}

	
	/**
	 * delete:执行删除操作。
	 * 主要OID和数据表中的的一条记录对象，就会准备执行delete操作
	 * 若OID在数据表中没有对应的记录，则抛出异常。
	 * 
	 * 
	 * 可以通过设置hibernate配置文件hibernate.user_identfier_rollback
	 * 为true，使删除对象后，把其OID设置为null。
	 */
	@Test
	public void testDelete(){
		
		/*游离对象
		News news = new News();
		news.setId(4);
		*/
		
		/*持久化对象
		News news = (News) session.get(News.class, 3);
		*/
		
		//OID与数据表中没有对应一条记录
		News news = (News) session.get(News.class, 1000); 
		session.delete(news);
		
		System.out.println(news);
	}

	
	/**
	 * evict:从session缓存中把指定的持久化对象移除。
	 */
	@Test
	public void testEvict(){
		
		News news1 = (News) session.get(News.class, 1);
		News news2 = (News) session.get(News.class, 2);
		
		news1.setTitle("NM");
		news2.setTitle("NM");
		
		session.evict(news1);
	}

	
	/**
	 * Session的doWork(Work)方法用于执行Work对象
	 * 指定的操作，即调用Work对象的excute()方法，
	 * Session会把使用的数据库连接传递给execute()方法。
	 */
	@Test
	public void testDoWork(){
		
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				
				System.out.println(connection);
				
				//调用存储过程，跟JDBC一样。
			}
		});
	}
}
