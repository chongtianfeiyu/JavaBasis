package com.startcaft.spring.beans.annotation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.startcaft.spring.beans.annotation.TestObject;

@Repository(value="userRepository")
//@Repository
public class UserRepositoryImpl implements UserRepository {

	
	private TestObject testObject;
	
	//设置bean可以不被设置
	@Autowired(required=false)
	public void setTestObject(TestObject testObject) {
		this.testObject = testObject;
	}
	
	@Override
	public void save() {
		System.out.println("UserRepository save......");
		System.out.println(testObject);
	}
}
