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

import com.startcaft.model.Customer;
import com.startcaft.model.Order;



/**
 * 使用many-to-one来映射多对一的关联关系：
 * name属性：多这一端关联的一的那一端的属性的名字
 * class：一的那一端的属性对应的类型
 * column：一那一端在多的一端对应的数据表中的外键的字段名字
 * 
 * @author startcaft
 *
 */
public class TesN21 {

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
	public void testManyToOne(){
		
		Customer customer = new Customer();
		customer.setCustomerName("AA");
		
		Order order1 = new Order();
		order1.setOrderName("ORDER-1");
		
		Order order2 = new Order();
		order2.setOrderName("ORDER-2");
		
		//设定关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		/*
		 * 先插入一的一端，再插入多的一端，3条insert语句。
		 */
		session.save(customer);
		session.save(order1);
		session.save(order2);
		
		
		/*
		 * 先插入多的一段，在插入多的一段，3条insert语句，2条update语句
		 * 
		 * 因为先插入多的一段，多的一段并不知道外键的值，所以插入完一的一端之后，会有两条update语句来更新外键值。
		 */
		session.save(order1);
		session.save(order2);
		session.save(customer);
		
	}

	
	@Test
	public void testManyToOneGet(){
		
		//1,若查询多的一端的一个对象，默认情况下，只查询了多的一端的对象
		//而没有查询一的那一端的对象!
		Order order = (Order) session.get(Order.class, 1);
		System.out.println(order.getOrderName());
		
		System.out.println(order.getCustomer().getClass().getName());//代理类型
		
		//session.close();//注意懒加载异常
		
		//2，在需要使用到关联的一的一端的对象时，才发送对应的SQL语句【懒加载】
		Customer customer = order.getCustomer();
		System.out.println(customer.getCustomerName());
		
		
		//3，获取Order 对象时，默认情况下，其关联的Customer 对象是一个代理对象。【跟session.load一样】
	}

	@Test
	public void testManyToOneUpdate(){
		
		Order order = (Order) session.get(Order.class, 1);
		order.getCustomer().setCustomerName("AAA");
	}

	@Test(expected=org.hibernate.exception.ConstraintViolationException.class)
	public void testManyToOneDelete(){
		
		//在不设定级联关系的情况下，并且一的一端的对象有N的对象在引用，不能直接删除一的对象。
		Customer customer = (Customer) session.get(Customer.class, 1);
		session.delete(customer);
	}
}
