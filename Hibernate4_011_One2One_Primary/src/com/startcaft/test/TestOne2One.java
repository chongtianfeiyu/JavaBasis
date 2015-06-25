package com.startcaft.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.startcaft.model.Department;
import com.startcaft.model.Manager;

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
		
		//transaction.commit();
		//session.close();
		sFactory.close();
	}
	
	@Test
	public void testOne2OneGet(){
		
		Department department = (Department) session.get(Department.class, 1);
		System.out.println(department.getDepartName());
		
		//这里也一样，注意延迟加载异常的问题。
//		session.close();
//		System.out.println(department.getManager().getClass());
//		System.out.println(department.getManager().getManagerName());
	}
	
	
	@Test
	public void testSave(){
		
		Manager manager = new Manager();
		manager.setManagerName("Mgr-AA");
		
		Department department = new Department();
		department.setDepartName("Depart-AA");
		
		//设置关联关系
		department.setManager(manager);
		manager.setDepartment(department);
		
		
		//这里先保存department和先保存manager的顺序都ok
		//因为department的主键是根据manager实体的主键产生的
		//无论先后顺序是什么，Hibernate都会保证manager对象先被保存，因为主键不像外键一样可以修改。
		session.save(manager);
		session.save(department);
	}
}
