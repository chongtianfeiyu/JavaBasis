package com.startcaft.junit;

public class HelloTest {
	
	public int add(int x,int y){
		return x + y;
	}
	
	//这个方法有可能出现算数异常，JUnit也可以断言一个异常
	public int divid(int x,int y){
		return x/y;
	}
	
	public int forOpr(){
		int x =0;
		for (int i = 0; i < 100; i++) {
			x += i;
		}
		return x;
	}
}
