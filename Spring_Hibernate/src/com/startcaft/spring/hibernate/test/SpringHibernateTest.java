package com.startcaft.spring.hibernate.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import javax.sql.DataSource;

import org.aspectj.lang.annotation.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.spring.hibernate.service.BookShopService;
import com.startcaft.spring.hibernate.service.Cashier;

public class SpringHibernateTest {

	
	private ClassPathXmlApplicationContext context = null;
	private BookShopService bookShopService = null;
	private Cashier cashiner = null;
	
	{
		context = new ClassPathXmlApplicationContext("application.xml");
		bookShopService = context.getBean(BookShopService.class);
		cashiner = context.getBean(Cashier.class);
	}
	
	@org.junit.After
	public void free(){
		context.close();
	}
	
	@Test
	public void testCashiner(){
		cashiner.checkout("AA", Arrays.asList("1001","1002"));
	}
	
	@Test
	public void testBookShopService(){
		bookShopService.bayBook("AA", "1001");
	}
	
	@Test
	public void testDataSource() {
		DataSource  dataSource = (DataSource) context.getBean("dataSource");
		System.out.println(dataSource);
	}

}
