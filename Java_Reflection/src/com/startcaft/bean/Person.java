package com.startcaft.bean;


public class Person {

	private int age;
	private String name;
	
	public Person() {
		super();
		System.out.println("Person init.....");
	}

	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
		System.out.println(name + " Person init..... " + age);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void show(){
		
		System.out.println(name + "......" + age);
	}
	
	public void method(){
		
		System.out.println("method run......");
	}
	
	public void paramMethod(String str){
		
		System.out.println("paramMethod run......" + str);
	}
}
