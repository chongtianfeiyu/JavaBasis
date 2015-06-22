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
 * 
 * 使用<set>来映射一对多的关系：
 * table属性：是多的一方的数据库表明
 * ------<key>节点中的column属性：是多的一方的外键列名
 * ------<one-to-many>节点中的class属性：一的一端的类型
 * 
 * <set>节点的inverse属性：
 * 1，这个属性来决定由双向关联的哪一方来维护表和表之间的关系，
 * inverse=false的为主动方,inverse=true的为被动方，
 * 由主动方负责维护关系
 * 
 * 2，在没有设置inverse=true的情况下，父子两边都维护关联关系。
 * 
 * 3，在1-N双向关联关系时，将N放设为主控方有助于性能改善，
 * 就像让国家领导人记住全国人民的名字，是不太现实的，
 * 但是要让全国人民记住国家领导人，就容易的多。
 * 
 * 4，在1-N关系中，若将1放设为主控方：
 * ------会额外发出多条update语句
 * ------插入数据时无法同时插入外键列，因而无法为外键列添加非空约束
 * 
 * 5，<set>节点的cascade属性：cascade=delete表示可以级联删除，
 * 在删除1的一方的对象时会删除所有引用1这个对象的N的对象。【开发时候不建议使用cascade使用】
 * 
 * 6，<set>元素有一个order-by属性：
 * 如果设置了改属性，Hibernate通过select语句到数据库中检索集合对象时，
 * 利用order by子句进行排序。
 * 
 * order-by属性还可以加入SQL函数。
 * 
 * order-by中使用的是数据库表字段名，而不是持久化对象的属性名
 * 
 * @author startcaft
 *
 */
public class TesOne2Many_Double {

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
	public void testCascadeSave(){
		
		Customer customer = new Customer();
		customer.setCustomerName("startcaft");
		
		Order order1 = new Order();
		order1.setOrderName("ORDER-1");
		
		Order order2 = new Order();
		order2.setOrderName("ORDER-2");
		
		//设定关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		
		//在设置cascade="save-update"时候，可以这样做。
		session.save(customer);
	}
	
	@Test
	public void testCascadeDeleteOrphan(){
		
		//当cascade="delete-orphan"时候，可以删除所有和当前对象有关联关系的所有多的一方的对象
		Customer customer = (Customer) session.get(Customer.class, 3);
		customer.getOrders().clear();
	}
	
	@Test
	public void testDelete(){
		
		//在不设定级联关系的情况下，并且一的一端的对象有N的对象在引用，不能直接删除一的对象。
		//在<set>节点，设置cascade="delete"即可。
		Customer customer = (Customer) session.get(Customer.class, 3);
		session.delete(customer);
	}
	
	@Test
	public void testUpdate2(){
		
		Customer customer = (Customer) session.get(Customer.class, 3);
		customer.getOrders().iterator().next().setOrderName("GGG");
	}
	
	@Test
	public void testOne2ManyGet(){
		
		//1,对N的一端的集合使用了延迟加载
		Customer customer = (Customer) session.get(Customer.class, 3);
		System.out.println(customer.getCustomerName());
		
		//2,返回的N的一端的集合是 Hibernate 内置的集合类型。
		//该类型具有延迟加载和存放代理对象的功能。
		System.out.println(customer.getOrders().getClass());
		
		//session.close();//3,注意懒加载异常。
		
		//4,在需要使用集合中元素的时候进行初始化。
	}
	
	
	@Test
	public void testAddOne2Many(){
		
		Customer customer = new Customer();
		customer.setCustomerName("startcaft");
		
		Order order1 = new Order();
		order1.setOrderName("ORDER-1");
		
		Order order2 = new Order();
		order2.setOrderName("ORDER-2");
		
		//设定关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		
		//执行save操作：先插入主表，再插入从表，3条insert，2条update
		//因为customer.getOrders().add(order1)了，在保存customer的时候，相关联的order还没保存呢
		//可以在1的一端的set 节点指定inverse=true，来使1的一端放弃维护关联关系
		//强烈建议设置set 的inverse=true，不会产生多的update，提高性能。
		session.save(customer);
		session.save(order1);
		session.save(order2);
		
		//执行save操作：先插入从表，再插入主表，3条inert，4条update
		//跟上面原因一样，因为1的一端和N的一端都维护关联关系。
/*		session.save(order1);
		session.save(order2);
		session.save(customer);*/
	}
}
