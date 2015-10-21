package com.startcaft.ssm.service;



import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.ssm.po.Department;
import com.startcaft.ssm.po.extension.CommitMessage;
import com.startcaft.ssm.po.extension.DepartmentCustom;


public class DepartmentServiceImplTest {
	
	private ApplicationContext context;
	DepartmentService service;
	
	{
		context = new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
		service = context.getBean(DepartmentService.class);
	}
	
	@Test
	public void testInsertDepartment() throws Exception {
		
		Department depart = new Department();
		depart.setParentId(23);
		depart.setApplicationId(2);
		depart.setIsEnable(true);
		depart.setDepartName("二级部门010101");
		
		CommitMessage msg = service.insertDepartment(depart);
		
		System.out.println(msg);
	}
	
	@Test
	public void testGetAllDepartment() throws Exception{
		
		List<DepartmentCustom> depart = service.getAllDepartment(2);
		
		for (DepartmentCustom departmentCustom : depart) {
			System.out.println(departmentCustom.getDepartName());
		}
	}
	
	@Test
	public void testUpdate() throws Exception{
		
		Department depart = service.getDepartment(15);
		depart.setDepartDesc("AAA全国性民营企业");
		
		CommitMessage msg = service.update(depart);
		
		System.out.println(msg);
	}
	
	@Test
	public void testgetDepartment() throws Exception{
		
		Department department = service.getDepartment(15);
		
		System.out.println(department.getDepartName());
	}
}
