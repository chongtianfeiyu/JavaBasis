package com.startcaft.spring.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-autowire.xml");
		
		Person person = (Person) context.getBean("person");
		System.out.println(person);
	}
}
