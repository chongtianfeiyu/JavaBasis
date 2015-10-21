package com.startcaft.ssm.service;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.startcaft.ssm.po.extension.CommitMessage;
import com.startcaft.ssm.po.extension.UserCustom;
import com.startcaft.ssm.po.extension.UserQueryVo;

public class UserServiceImplTest {
	
	private ApplicationContext context;
	UserService service;
	
	{
		context = new ClassPathXmlApplicationContext("classpath:spring/spring-mybatis.xml");
		service = context.getBean(UserService.class);
	}
	
	@Test
	public void testInsertUser() throws Exception {
		
		UserCustom user = new UserCustom();
		user.setCreateTime(new Date());
		user.setIsEnable(true);
		user.setLoginName("zhaoliu");
		user.setNickName("赵六");
		user.setUserPwd("222222");
		
		CommitMessage msg = this.service.insertUser(24, user);
		
		System.out.println(msg);
	}

	@Test
	public void testFindUserLisyByDepart() throws Exception {
		
		UserQueryVo query = new UserQueryVo();
		query.setPage(1);
		query.setRows(20);
		UserCustom user = new UserCustom();
		user.setLoginName("zhang");
		user.setDepartmentId(24);
		query.setUserCustom(user);
		
		
		List<UserCustom> users = this.service.findUserLisyByDepart(query);
		
		for (UserCustom u : users) {
			System.out.println(u.getNickName());
		}
	}

	@Test
	public void testFindUserLisyCount() throws Exception {
		UserQueryVo query = new UserQueryVo();
		UserCustom user = new UserCustom();
		user.setDepartmentId(24);
		query.setUserCustom(user);
		
		int count = this.service.findUserLisyCount(query);
		System.out.println(count);
	}
	
	@Test
	public void testDeleteUser() throws Exception{
		
		CommitMessage msg = service.deleteUser(5);
		
		System.out.println(msg);
	}

}
