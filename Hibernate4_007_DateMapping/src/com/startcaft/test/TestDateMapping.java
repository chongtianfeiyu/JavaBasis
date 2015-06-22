package com.startcaft.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.startcaft.model.News;


/**
 * 在Java中，代表时间和日期的类型包括：java.util.Date和java.util.Calender。
 * 在JDBC API中还通过了三个扩展自java.util.Date的子类：java.sql.Date,
 * java.sql.Time和java.sql.Timestamp。这三个类分别和标准SQL中的DATE,
 * TIME和TIMESTAMP类型对应。
 * 
 * 在标准SQL中，DATE类型表示日期，TIME类型表示时间，TIMESTAMP表示时间戳，同时包含日期和时间信息
 * 
 * 因为java.util.Date是父类，所以在Hibernate映射时间类型时使用java.util.Date。
 * 
 * 通过在.hbm.xml文件的<property type="date|time|timestamp"【Hibernate类型】来映射。
 * 
 * @author startcaft
 *
 */
public class TestDateMapping {

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
	
	@Test
	public void testInsertAsTime(){
		
		News news = new News();
		news.setCreateTime(new java.sql.Time(new Date().getTime()));
		news.setAuthor("AA");
		news.setTitle("aa");
		
		session.save(news);
	}
	
	@Test
	public void testGetDesc(){
		
		News news = (News) session.get(News.class, 1);
		
		System.out.println(news.getDesc());
	}
	
	@Test
	public void testTime(){
		
		News news = (News) session.get(News.class, 1);
		Class cls = news.getCreateTime().getClass();
		
		System.out.println(cls);//java.sql.Time
	}
	
	@Test
	public void testBlob() throws Exception{
		
		News news = (News) session.get(News.class, 1);
		
		InputStream stream = new FileInputStream("D:\\startcaft的云盘\\网页开发素材\\HTML素材\\图片\\Lumia830.png");
		
		Blob image = Hibernate.getLobCreator(session)
					.createBlob(stream, stream.available());
		
		news.setImage(image);
	}
}
