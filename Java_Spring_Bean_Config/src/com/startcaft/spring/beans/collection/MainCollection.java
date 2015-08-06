package com.startcaft.spring.beans.collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainCollection {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
		Person person = (Person) context.getBean("person3");
		System.out.println(person);
		
		NewPerson np = (NewPerson) context.getBean("newPerson");
		System.out.println(np);
		
		DataSource ds = (DataSource) context.getBean("datasource");
		System.out.println(ds.getProperties());
		
		person = (Person) context.getBean("person4");
		System.out.println(person);
		
		person = (Person) context.getBean("person5");
		System.out.println(person);
	}
}
