package com.startcaft.spring.aop;

public class CalculateLoggingImpl implements Calculate {

	@Override
	public int add(int i, int j) {
		System.out.println("The method add begin with " + i + "   and   " + j);
		int result = i + j;
		System.out.println("The method add result : " + result);
		return result;
	}

	@Override
	public int sub(int i, int j) {
		System.out.println("The method sub begin with " + i + "   and   " + j);
		int result = i - j;
		System.out.println("The method add result : " + result);
		return result;
	}

	@Override
	public int mul(int i, int j) {
		System.out.println("The method mul begin with " + i + "   and   " + j);
		int result = i * j;
		System.out.println("The method add result : " + result);
		return result;
	}

	@Override
	public int div(int i, int j) {
		System.out.println("The method div begin with " + i + "   and   " + j);
		int result = i / j;
		System.out.println("The method add result : " + result);
		return result;
	}
}
