package com.startcaft.spring.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.spring.autowire.Address;
import com.startcaft.spring.autowire.Car;
import com.startcaft.spring.autowire.Person;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-scope.xml");
		
		Car car = (Car) context.getBean("car");
		System.out.println(car);
		
		Car car2 = (Car) context.getBean("car");
		System.out.println(car);
		
		System.out.println(car == car2);
	}
}
