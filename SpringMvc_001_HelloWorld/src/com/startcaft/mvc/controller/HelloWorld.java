package com.startcaft.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller									//@Controller注解 表示
@RequestMapping("/hello")
public class HelloWorld {
	
	/**
	 * 方法的返回值，会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver 视图解析器，会做如下的解析：
	 * prefix + 返回值  + suffix
	 * 得到实际的物理视图后，然后做转发操作。
	 * @return
	 */
	@RequestMapping("/helloworld")			//@@RequestMapping注解 来映射请求的URL，该注解可以作用于类和方法。
	public String hello(){
		
		System.out.println("hello");
		return "success";
	}
}
