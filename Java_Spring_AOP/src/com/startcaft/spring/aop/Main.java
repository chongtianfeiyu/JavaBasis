package com.startcaft.spring.aop;

public class Main {

	public static void main(String[] args) {
		
		Calculate target = new CalculateImpl();
		
		Calculate proxy = new CalculateLoggingProxy(target).getLogginProxy();
		
		
		int result = proxy.add(1, 2);
		System.out.println("--->" + result);
		
		result = proxy.div(4, 2);
		System.out.println("--->" + result);;
	}
}
