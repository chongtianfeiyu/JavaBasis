package com.startcaft.spring.properties;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-properties.xml");
		
		DataSource datasource = (DataSource) context.getBean("datasource");
		System.out.println(datasource);
	}
}
