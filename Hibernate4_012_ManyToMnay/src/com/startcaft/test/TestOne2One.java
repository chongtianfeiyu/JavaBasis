package com.startcaft.test;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.startcaft.model.Category;
import com.startcaft.model.Item;


public class TestOne2One {

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
	public void testGet(){
		
		Category category = (Category) session.get(Category.class, 1);
		
		System.out.println(category.getName());
		
		//需要连接中间表，注意懒加载异常。
		Set<Item> items = category.getItems();
		System.out.println(items.size());
	}
	
	@Test
	public void testSave(){
		
		Category category1 = new Category();
		Category category2 = new Category();
		
		category1.setName("Cate_AA");
		category2.setName("Cate_BB");
		
		Item item1 = new Item();
		Item item2 = new Item();
		
		item1.setName("Item_AA");
		item2.setName("Item_BB");
		
		
		//设置关联关系
		category1.getItems().add(item1);
		category1.getItems().add(item2);
		
		category2.getItems().add(item1);
		category2.getItems().add(item2);
		
		item1.getCategories().add(category1);
		item1.getCategories().add(category2);
		
		item2.getCategories().add(category1);
		item2.getCategories().add(category2);
		
		session.save(category1);
		session.save(category2);
		session.save(item1);
		session.save(item2);
		
	}
}
