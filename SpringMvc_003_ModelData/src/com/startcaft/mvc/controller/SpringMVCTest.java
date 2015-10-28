package com.startcaft.mvc.controller;


import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.startcaft.mvc.entity.User;


/*
 * Spring MVC处理模型属性---handler中的方法调用业务方法，将返回的值，转到到指定的逻辑视图，并进行展示。
 * Spring MVC提供了以下几种途径输出模型数据：
 * 
 * 1，ModelAndView			handler方法返回值类型为ModelAndView时，方法体即可通过该对象添加模型数据；
 * 2，Map 及 Model			handler的方法形参为org.springframework.ui.Model，org.springframework.ui.ModelMap
 * 							或者java.uitl.Map时，处理方法返回时，Map中的数据会自动添加到模型中；
 * 3，@SessionAttribute		将模型中的某个属性暂时存储到HttpSession中，以及在多个请求之间可以共享这个属性；
 * 4，@ModelAttribute		handler的方法形参使用该注解标记后，形参就会放到数据模型中；
 */
@Controller
@RequestMapping("/model")
@SessionAttributes(value="user",types=String.class)
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	/**
	 * 当目标方法的返回值为org.springframework.web.servlet.ModelAndView类型时，
	 * 其中可以包含视图和模型信息
	 * 
	 * SpringMVC 会把ModelAndView中的 model 中数据放入 request 域对象中
	 */
	@RequestMapping(value="/testModelAndView")
	public ModelAndView testModelAndView(){
		
		String viewName = SUCCESS;
		ModelAndView mv = new ModelAndView(viewName);
		
		//添加模型属性到ModelAndView中，
		mv.addObject("time", new Date());
		
		return mv;
	}
	
	/**
	 * 目标方法可以添加 Map 类型(实际上可以添加 Model类型或 ModelMap类型)的参数
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String,Object> map){
		
		System.out.println(map.getClass().getName());
		map.put("names", Arrays.asList("Tom","Jerry","Mike"));
		return SUCCESS;
	}
	
	/**
	 * 使用@SessionAttributes 注解可以通过 属性名 指定需要放到HttpSession中的属性外(该注解的value属性)，
	 * 还可以通过模型属性的 对象类型 指定哪些属性需要放到会话中(该注解的type属性)。
	 * 
	 * 注意：@SessionAttributes 只能用标记类，而不能标记方法。
	 */
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String,Object> map){
		
		User user = new User("Tom", "123456", "Tom@163.com", 22);
		map.put("user", user);
		map.put("school", "atguigu");
		return SUCCESS;
	}
	
	@RequestMapping(value="testModelAttributes",method=RequestMethod.POST)
	public String testModelAttributes(@ModelAttribute("user") User user){	//该User类型参数值，部分是从表单提交上来的，还有一部分是从数据库中获取的。
		
		System.out.println("修改：" + user);
		return SUCCESS;
	}
	
	/*
	 * 由 @ModelAttribute 标记的方法，会在每个目标方法执行之前被Spring MVC调用！！!
	 * 其运行流程是：
	 * 1，执行 @ModelAttribute 注解修饰的方法，从数据库中去除对象，把对象放入到Map中，键为 user；
	 * 2，Spring MVC 从Map中取出User对象，并把表单的请求参数赋给该User对象的对应属性；
	 * 3，Spring MVC 把上述对象传入到目标方法的参数；
	 * 
	 * 【注意：在 @ModelAttribute 修饰的方法中，放大到Map 时的键 需要和目标方法形参类型的第一个字母小写的字符串一致；】
	 * 
	 * 源码分析的流程：
	 * 1，调用 @ModelAttribute 修饰的方式，实际上把  @ModelAttribute 修饰的方法中的Map的数据放在了 implicitModel 中。
	 * 2，解析请求处理器的目标参数，实际上该目标参数来自于WebDataBinder 对象的target 属性。
	 * 		2-1)，创建WebDataBinder 对象：
	 * 			①，确定objectName属性，若穿入的attrName属性为""，则objectName 为类名第一个字母小写
	 * 
	 * 			②，确定target属性
	 * 				1，在 implicitModel 中查询 attrName对应的属性值，若存在，ok
	 * 				*2，若不存在：则验证当前Handler是否使用了@SessionAttributes 注解进行修饰，若使用了，则尝试从Session中获取 attrName所对应的属性值，若Session中没有对应的属性值，则抛出了异常。
	 * 				3，若Handler没有使用@SessionAttributes 或者该注解的value属性不包含  attrName 的值，则使用反射来创建POJO 对象
	 * 
	 * 			***注意：attrName，若目标方法的POJO属性使用了 @ModelAttribute 来修饰，则 attrName 属性值即为@ModelAttribute的value属性值
	 * 		2-2)，Spring MVC 把表单中的请求参数赋给了WebDataBinder 的target对应的属性。
	 * 		2-3)，***Spring MVC 会把WebDataBinder 的attrName 和 target 给到implicitModel,进而传到request 域对象中
	 * 		2-4)，把WebDataBinder 的target 作为目标方法的参数传入给其入参
	 * 
	 * 
	 * 总结：Spring MVC确定目标方法 POJO类型入参的过程：
	 * 1，确定一个key
	 * 		1-1)，若目标方法的POJO类型的参数没有使用@ModelAttribute 作为修饰，则key 为 POJO类名的第一个字母的小写
	 * 		1-2)，若使用了@ModelAttribute 作为修饰，则使用该注解的value属性的值
	 * 		1-2)，若使用了@ModelAttribute 修饰的方法中在Map中保存过，且key 和 步骤1-1 的key 已知，则会获得到
	 * 2，在implicitModel中查找key 对应的对象，若存在，则作为入参传入
	 * 3，若implicitModel不存在key 对应的对象，则检查当前的Handler 是否使用@SessionAttributes 注解修饰，
	 * 		若使用了该注解，且@SessionAttributes 注解的value属性值中包含了key，则会从HttpSession中获取 key对应的value 值，若存在则传入到目标方法的入参中，
	 * 		若不存在，则抛出异常
	 * 4，若Handler没有使用@SessionAttributes 注解修饰，或者该注解的value属性中不包含 key，则会通过反射来创建POJO类型，传入为入参
	 * 5，Spring MVC 会把key 和 value 传入到implicitModel，进而会保存到request 域对象中
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value="id",required=false) Integer id,
					Map<String,Object> map){
		
		System.out.println("modelAttribute method");
		if(id != null){
			//模拟从数据库中获取对象
			User user = new User(1, "Tome", "123456", "tom@163.com", 22);
			System.out.println("模拟从数据库中获取对象: " + user);
			
			map.put("user", user);
		}
	}
}
