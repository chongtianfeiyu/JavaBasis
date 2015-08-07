package com.startcaft.spring.bean.generic;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-generic.xml");
		
		UserService userService = (UserService) context.getBean("userService");
		userService.add();
	}
}
