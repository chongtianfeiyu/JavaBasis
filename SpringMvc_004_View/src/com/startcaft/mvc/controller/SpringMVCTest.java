package com.startcaft.mvc.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	/*
	 * Handler中的目标方法执行完成后，最终返回一个ModelAndView对象。
	 * 对于那些返回String，View或者ModelMap等类型的目标方法，Spring MVC都会在内部将他们装配成一个ModelAndView对象，它包含了逻辑名和模型对象
	 * 
	 * Spring MVC解析视图解析器(ViewResolver)得到最终的视图对象(View)，最终的视图可以是jsp，也可能是Excel，JFreeChart等各种标线形式的视图
	 * 
	 * 对于最终究竟采取何种视图对象对模型数据进行渲染，处理器并不关系，处理工作重点聚焦在生产模型数据的工作上，从而实现MVC的充分解耦
	 */
	@RequestMapping("/testViewAndResovler")
	public String testViewAndResovler(){
		
		System.out.println("testViewAndResovler");
		return SUCCESS;
	}
	
	@RequestMapping("/myView")
	public String testMyView(){
		
		System.out.println("myView");
		return "helloView";
	}
	
	/*
	 * 如果目标方法返回的字符串中带有forward: 或者 redirect: 前缀时，
	 * Spring MVC会把它们进行特殊处理，将它们当成指示符，其后面的字符串作为URL来处理。
	 */
	@RequestMapping("/testRedirect")
	public String testRedirect(){
		
		System.out.println("testRedirect");
		return "redirect:/Index.jsp";
	}
}
