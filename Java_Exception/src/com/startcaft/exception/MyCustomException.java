package com.startcaft.exception;

/**
 * Java中的错误处理基类：java.lang.Throwable
 * 						\\\Error------错误基类
 * 						\\\Exception-----异常基类
 * 							\\\......Exception------直接异常子类
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
