package com.startcaft.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

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

public class TestC3P0 {

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
	public void testC3P0Connection(){
		
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
			
				//打印的结果是com.mchange.v2.c3p0.impl.xxxxxxxxxx
				System.out.println(connection);
			}
		});
	}
	
	/**
	 * hbm.xml文件的class节点配置dynamic-update="true"动态更新
	 * 它会动态的为持久化对象的更新动态update，
	 * 也就是说修改几个字段就更新几个字段，而不是默认的全部都更新。
	 * 
	 * dynamic-insert="true"动态插入跟动态更新是一样的，
	 * 只查询实体对象中有值的属性。
	 */
	@Test
	public void testDynamicUpdate(){
		
		News news = (News) session.get(News.class, 1);
		news.setTitle("SUN");
		
	}
}
