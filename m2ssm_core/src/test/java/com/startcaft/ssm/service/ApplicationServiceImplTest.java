package com.startcaft.ssm.service;


import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.ssm.po.Application;
import com.startcaft.ssm.po.extension.CommitMessage;

public class ApplicationServiceImplTest {
	
	private ApplicationContext context;
	ApplicationService service;
	
	{
		context = new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
		service = context.getBean(ApplicationService.class);
	}
	
	@Test
	public void testInsertApplication() throws Exception {
		
		Application app = new Application();
		app.setAppCode(java.util.UUID.randomUUID().toString());
		app.setAppName("测试应用2");
		
		CommitMessage msg = service.insertApplication(app);
		
		System.out.println(msg);
	}
	
	
	@Test
	public void testCheckAppNameEquals() throws Exception{
		
		String name = "测试应用";
		
		boolean result = service.checkAppNameEquals(name);
		
		Assert.assertTrue(result);
	}

}
