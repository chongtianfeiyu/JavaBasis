package com.startcaft.spring.beans.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.startcaft.spring.beans.annotation.repository.UserRepository;

@Service
public class UserService {
	
	//@Autowired
	private UserRepository userRepository;
	
	@Autowired
	//装配指定名称的bean
	@Qualifier(value="userJdbcRepository")
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
//	@Autowired
//	//放在方法的参数前面也行
//	public void setUserRepository(@Qualifier(value="userJdbcRepository") UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
	
	public void add(){
		System.out.println("UserService add......");
		userRepository.save();
	}
}
