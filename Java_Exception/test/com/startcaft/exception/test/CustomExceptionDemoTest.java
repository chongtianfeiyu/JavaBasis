package com.startcaft.exception.test;

import org.junit.Test;

import com.startcaft.exception.CustomExceptionDemo;
import com.startcaft.exception.MyCustomException;

public class CustomExceptionDemoTest {
	
	//����f()�������׳�һ���Զ����쳣MyCustomException
	@Test(expected=com.startcaft.exception.MyCustomException.class)
	public void fTest() throws MyCustomException{
		new CustomExceptionDemo().f();
	}
}
