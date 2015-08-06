package com.startcaft.spring.beans;

public class HelloWorld {
	private String name;
	
	public void setName(String name){
		this.name = name;
	}
	
	public void sayHello(){
		System.out.println("hello: " + name);
	}
	
	public HelloWorld(String str) {
		System.out.println("constarct with param");
	}
	
	public HelloWorld() {
		System.out.println("init....");
	}
}
