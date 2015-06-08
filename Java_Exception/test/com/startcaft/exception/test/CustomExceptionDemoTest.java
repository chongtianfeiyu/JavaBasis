package com.startcaft.exception.test;

import org.junit.Test;

import com.startcaft.exception.CustomExceptionDemo;
import com.startcaft.exception.MyCustomException;

public class CustomExceptionDemoTest {
	
	//断言f()方法将抛出一个自定义异常MyCustomException
	@Test(expected=com.startcaft.exception.MyCustomException.class)
	public void fTest() throws MyCustomException{
		new CustomExceptionDemo().f();
	}
}
