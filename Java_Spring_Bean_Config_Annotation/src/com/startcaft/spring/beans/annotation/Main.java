package com.startcaft.spring.beans.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.spring.beans.annotation.controller.UserController;
import com.startcaft.spring.beans.annotation.repository.UserRepository;
import com.startcaft.spring.beans.annotation.service.UserService;

public class Main {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-annotation.xml");
		
//		TestObject to = (TestObject) context.getBean("testObject");
//		System.out.println(to);
		
		UserController userController = (UserController) context.getBean("userController");
		System.out.println(userController);
		userController.execute();
		
//		UserService userService = (UserService) context.getBean("userService");
//		System.out.println(userService);
//		
//		UserRepository userRepository = (UserRepository) context.getBean("userRepository");
//		System.out.println(userRepository);
		
		context.close();
	}
}
