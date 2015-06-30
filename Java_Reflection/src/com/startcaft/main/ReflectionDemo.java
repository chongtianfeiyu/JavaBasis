package com.startcaft.main;

import com.startcaft.bean.Person;

public class ReflectionDemo {

	public static void main(String[] args) throws ClassNotFoundException {
		
		getClassObject_1();
		getClassObject_2();
		getClassObject_3();
	}
	
	//方式1，使用具体对象的getClass()方法
	public static void getClassObject_1(){
		
		Person p = new Person();
		Class class1 = p.getClass();
		
		
		Person p1 = new Person();
		Class class2 = p.getClass();
		
		System.out.println(class1 == class2);
		System.out.println(class1.equals(class2));
	}
	
	//使用任意数据类型的静态class属性。
	public static void getClassObject_2(){
		
		Class class1 = Person.class;
		
		Class class2 = Person.class;
		
		System.out.println(class1 == class2);
		System.out.println(class1.equals(class2));
	}
	
	//使用Class类的forName方法获取
	public static void getClassObject_3() throws ClassNotFoundException{
		
		//String className = "Person";
		String className = "com.startcaft.bean.Person";//需要类的完全限定名
		
		Class class1 = Class.forName(className);
		
		System.out.println(class1);
	}
}
