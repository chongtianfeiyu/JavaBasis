package com.startcaft.m2ssh.service;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.m2ssh.comm.CommitMessage;
import com.startcaft.m2ssh.entity.Department;

public class DepartmentServiceTest {
	
	
	private ApplicationContext context;
	private DepartmentService service;
	
	{
		context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		service = context.getBean(DepartmentService.class);
	}
	
	@Test
	public void testInsertDepart() throws Exception {
		
		Department depart = new Department();
		depart.setpId(0);
		depart.setName("XXX总公司");
		depart.setCreateTime(new Date());
		depart.setEnable(true);
		
		CommitMessage msg = this.service.insertDepart(depart);
		System.out.println(msg);
	}

	@Test
	public void testGetAllDepart() throws Exception {
		
		List<Department> list = this.service.getAllDepart();
		for (Department department : list) {
			System.out.println(department.getName());
		}
	}

}
