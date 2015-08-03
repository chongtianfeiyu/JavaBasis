package com.startcaft.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

	public static void main(String[] args) {
		
		/* 在没有Spring之前，我们如下构建对象
		HelloWorld hello = new HelloWorld();
		hello.setName("spring");
		hello.hello();
		*/
		
		//1，创建Spring 的 IOC 容器对象
		//----在创建IOC 容器时候，Spring会通过反射机制调用Bean 的默认构造函数。
		//----如果有为setter设置值，还会调用setXXXX方法。
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//2，从IOC 容器中获取Bean 实例
		HelloWorld hello = (HelloWorld) context.getBean("hello");
		
		//3，调用hello 方法
		hello.hello();
	}
}
