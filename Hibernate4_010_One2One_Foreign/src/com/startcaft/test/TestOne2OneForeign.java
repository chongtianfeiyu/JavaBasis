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

public class TestOne2OneForeign {

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
	
	//如果是先获取没有外键的表，那么回使用左外连接两个表
	//而且默认情况下，连接条件不对，需要在one-to-one设置property-ref="导航属性名"
	@Test
	public void testOne2OneGet2(){
		
		Manager manager = (Manager) session.get(Manager.class, 1);
		System.out.println(manager.getManagerName());
		System.out.println(manager.getDepartment().getDepartName());
	}
	
	//先获取有外键的表，然后再获取没有外键的表(延迟加载，因为有外键指向主表)
	@Test
	public void testOne2OneGet(){
		
		Department department = (Department) session.get(Department.class, 1);
		System.out.println(department.getDepartName());
		
		//这里也一样，注意延迟加载异常的问题。
/*		session.close();
		System.out.println(department.getManager().getClass());
		System.out.println(department.getManager().getManagerName());*/
	}
	
	@Test
	public void testOne2OneSave(){
		
		Manager manager = new Manager();
		manager.setManagerName("Manager_AA");
		
		Department department = new Department();
		department.setDepartName("Department_AA");
		
		//设定关联关系
		manager.setDepartment(department);
		department.setManager(manager);
		
		session.save(manager);
		session.save(department);
		
		//注意保存save的顺序，先save主表，再save从表，不然会有多余的update语句。
/*		session.save(department);
		session.save(manager);*/
		
	}
}
