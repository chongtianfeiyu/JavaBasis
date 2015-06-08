package com.startcaft.exception;

public class CustomExceptionDemo {
	
	public void f() throws MyCustomException{
		System.out.println("throw MyCustomException from f()");
		throw new MyCustomException();
	}
	
	
	
	//µ÷ÓÃMyCustomException2
	public void f2() throws MyCustomException2{
		System.out.println("throw MyCustomException2 from f2()");
		throw new MyCustomException2();
	} 
	
	public void f21() throws MyCustomException2{
		System.out.println("throw MyCustomException2");
		throw new MyCustomException2("Orginated in f21()");
	}
	
	public void f211() throws MyCustomException2{
		System.out.println("throw MyCustomException2");
		throw new MyCustomException2("Orginated in f211()",47);
	}
}
