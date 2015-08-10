package com.startcaft.spring.aop.helloworld;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		//1.创建容器
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//2.从容器中获取bean的实例
		Calculate cal = context.getBean(Calculate.class);
		
		//3.使用bean
		int result = cal.add(3, 6);
		System.out.println("result : " + result);
		
		result = cal.div(4, 0);
		System.out.println("result : " + result);
		
		context.close();
	}
}
