package com.startcaft.exception.test;

import com.startcaft.exception.CustomExceptionDemo;
import com.startcaft.exception.LoggingException;

public class LoggingExceptionTest {

	public static void main(String[] args) {

		try {
			throw new LoggingException();
		} catch (LoggingException e) {
			System.err.println("Catched:" + e);
		}
		
		System.out.println("=========≤‚ ‘MyCustomeException2=========");
		
		try {
			new CustomExceptionDemo().f();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
		try {
			new CustomExceptionDemo().f21();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
		try {
			new CustomExceptionDemo().f211();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

}
