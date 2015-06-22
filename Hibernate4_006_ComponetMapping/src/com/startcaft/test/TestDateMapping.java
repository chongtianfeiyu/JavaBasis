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

import com.startcaft.model.Address;
import com.startcaft.model.Person;


/**
 * Hibernate映射组合关系：
 * Hibernate把持久化类的属性分为两种：
 * 1，值类型：没有OID，不能被单独持久化，生命周期依赖与所属的持久化类的生命周期
 * 2，实体类：有OID，可以被单独持久化，有独立的生命周期
 * 
 * 就一个Person有一个对应的Address，(只有Person一张表)
 * 
 * Hibernate使用<component>元素来映射组成关系，Address属性是Person类的
 * 一个组成部分，在Hibernate中称之为组件。
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
	public void testAddComponet(){
		
		Person person = new Person();
		Address address = new Address();
		
		address.setCountry("中国");
		address.setState("湖北");
		address.setStree("武汉");
		
		person.setName("ABC");
		person.setAddress(address);
		
		session.save(person);
	}
}
