package com.startcaft.spring.factorybean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-factorybean.xml");
		
		Car car = (Car) context.getBean("car");
		System.out.println(car);
	}
}
