package com.startcaft.exception;

/**
 * Java�еĴ�������ࣺjava.lang.Throwable
 * 						\\\Error------�������
 * 						\\\Exception-----�쳣����
 * 							\\\......Exception------ֱ���쳣����
 * 
 * @author wow
 *
 */
public class MyCustomException extends Exception {
	
	public MyCustomException(){
		
	}
	
	public MyCustomException(String exceptionMessage){
		super(exceptionMessage);
	}
}
