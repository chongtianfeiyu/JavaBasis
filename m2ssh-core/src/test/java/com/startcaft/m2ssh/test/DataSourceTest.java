package com.startcaft.m2ssh.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataSourceTest {
	
	private ApplicationContext context;
	
	{
		context = new ClassPathXmlApplicationContext("application-context.xml");
	}
	
	@Test
	public void testDataSource() throws SQLException{
		
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		
		Assert.assertNotNull(dataSource);
		
		System.out.println(dataSource.getConnection());
	}
}
