package com.startcaft.mvc.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.startcaft.mvc.entity.User;

/*
 * 1，Spring MVC通过分析处理方法的签名，将HTTP请求信息绑定到处理方法相应形参中。
 * 2，Spring MVC对控制器处理方法签名的限制是很宽松的，几乎可以按喜欢的方式对方法进行签名。
 * 3，必要时可以对方法及方法形参标注响应的注解(@PathVariable，@RequestParam，@RequestHeader等)，
 * 		Spring MVC框架会将HTTP请求的信息绑定到相应的方法形参中。
 */
@Controller
@RequestMapping("/hello")
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	/*
	 * 使用@RequestParam 来映射请求参数。
	 * value属性 				代表请求参数的参数名。
	 * required属性 			代表该参数是否必须。
	 * defaultValue属性		代表该请求参数的默认值。	
	 */
	@RequestMapping("/testRequestParam")
	public String testRequestParam(
				@RequestParam(value="username",required=false) String name,
				@RequestParam(value="age",required=false,defaultValue="0") int age){
		
		System.out.println("testRequestParam，username: " + name + "，age: " + age);
		return SUCCESS;
	}
	
	/*
	 * 使用@RequestHeader 来映射请求头信息，用法和@RequestParam类似
	 */
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept-Language") String al){
		
		System.out.println("testRequestHeader，Accept-Language=" + al);
		return SUCCESS;
	}
	
	/*
	 * 使用@CookieValue 来映射Cookie信息，用法和@RequestParam类似
	 */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(
				@CookieValue("JSESSIONID") String sessionId){
		
		System.out.println("testCookieValue，JSESSIONID=" + sessionId);
		return SUCCESS;
	}
	
	/*
	 * Spring MVC 会把请求参数名和POJO的属性名进行自动匹配，自动为该对象填充属性值。
	 * 支持级联属性，如address.city。
	 */
	@RequestMapping("/testPojo")
	public String testPojo(User user){
		
		System.out.println("testPojo: " + user);
		return SUCCESS;
	}
	
	/*
	 * Spring MVC 的handler中的方法可以接收以下这些ServletAPI类型的参数：
	 * 【HttpServletRequest】
	 * 【HttpServletResponse】
	 * 【HttpSession】
	 * java.security.Principal
	 * Local
	 * InputStream
	 * OutputStream
	 * Reader
	 * Writer
	 */
	@RequestMapping("/testServletApi")
	public void testServletApi(HttpServletRequest request,HttpServletResponse response,Writer out) throws IOException{
		
		out.write("hello Spring MVC!");
		System.out.println("testServletApi，" + request + "," + response);
	}
}
