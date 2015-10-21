package com.startcaft.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/springmvc")
public class SpringMVCTest {

	private static final String SUCCESS = "success";
	
	/**
	 * 1.@RequestMapping注解 除了可以修饰方法，还可以用来修饰类。
	 * 		类定义处：提供初步的请求映射信息，相对于WEB应用的根目录；
	 * 		方法定义处：将提供进一步的细分映射信息，相对于类定义处的URL。若类没有标注@RequestMapping，则方法处的URL，相对于WEB应用根目录；
	 * @return
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping(){
		
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
	
	/**
	 * 1.使用@RequestMapping 注解的method属性，来指定HTTP的请求方式。
	 * 		method属性比较常用 
	 * @return
	 */
	@RequestMapping(value="testMethod",method=RequestMethod.POST)
	public String testMethod(){
		
		System.out.println("testMethod");
		return SUCCESS;
	}
	
	/**
	 * 了解即可：
	 * 可以使用@RequestMapping 注解的params和headers属性来更加精确的映射请求，params和headers 支持简单的表达式
	 * @return
	 */
	@RequestMapping(value="/testParamsAndHeaders",params={"username","age!=10"},headers={"Accept-Language=zh-CN,zh;q=0.8"})
	public String testParamsAndHeaders(){
		
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
	
	/**
	 * 了解即可：
	 * 可以在@RequestMapping 注解的 value 属性中使用 Ant 风格通配符的形式来映射请求URL：
	 * 【?	匹配一个字符；】
	 * 【*	匹配任意个字符；】
	 * 【**	匹配多层路径；】
	 * @return
	 */
	@RequestMapping("/testAntPath/*/abc")
	public String testAntPath(){
		
		System.out.println("testAntPath");
		return SUCCESS;
	}
	
	/**
	 * 使用@PathVariable 来标注方法形参，可以映射URL中的占位符到目标方法的参数中。
	 * @param id
	 * @return
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id){
		
		System.out.println("testPathVariable:" + id);
		return SUCCESS;
	}
	
	
	/*
	 * REST 风格的 URL：
	 * 以CRUD为例子：
	 * 新增：	/order		POST	
	 * 修改:	/order/1	PUT			update?id=1
	 * 获取:	/order/1	GET			get?id=1
	 * 删除:	/order/2	DELETE 		delete?id=1
	 * 
	 * 如何发送PUT,DELETE请求呢？
	 * 1，需要配置一个Filter------org.springframework.web.filter.HiddenHttpMethodFilter
	 * 2，需要发送POST请求
	 * 3，需要在发送POST请求时，携带一个name="_method" value="DELETE/PUT" 的隐藏域
	 */
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.GET)
	public String testRest(@PathVariable("id") Integer id){
		
		System.out.println("testRest GET:" + id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest",method=RequestMethod.POST)
	public String testRest(){
		
		System.out.println("testRest POST");
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.DELETE)
	public String testRestDelete(@PathVariable("id") Integer id){
		
		System.out.println("testRest DELETE:" + id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.PUT)
	public String testRestPut(@PathVariable("id") Integer id){
		
		System.out.println("testRest PUT:" + id);
		return SUCCESS;
	}
}
