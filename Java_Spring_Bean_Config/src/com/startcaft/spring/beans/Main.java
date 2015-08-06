package com.startcaft.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//使用id 来定位到Bean。
		HelloWorld hello = (HelloWorld) context.getBean("myBean2");
		
		//使用类型来获取Bean，要求IOC 容器中必须只能有一个该类型的Bean
//		HelloWorld hello = context.getBean(HelloWorld.class);
//		hello.sayHello();
		System.out.println(hello);
		
		
		Car car = (Car) context.getBean("car");
		System.out.println(car);
		
		car = (Car) context.getBean("car2");
		System.out.println(car);
		
		
		Person p1 = (Person) context.getBean("person");
		System.out.println(p1);
		
		p1 = (Person) context.getBean("person2");
		System.out.println(p1);
		
	}
}
