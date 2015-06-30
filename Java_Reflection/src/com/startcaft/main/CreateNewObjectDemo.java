package com.startcaft.main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.startcaft.bean.Person;

public class CreateNewObjectDemo {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		
		createNewObject_1();
		createNewObject_2();
	}
	
	//使用空参数的构造函数初始化一个对象
	public static void createNewObject_1() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		
		//使用new来构造一个对象的实例。
		com.startcaft.bean.Person person = new com.startcaft.bean.Person();
		
		//使用反射来构造一个对象的实例。
		Class class1 = Class.forName("com.startcaft.bean.Person");
		
		//一定要确保该类有一个public的无参数的构造函数,不然会抛出异常。
		Object object = class1.newInstance();
		
		System.out.println(object instanceof Person);
	}
	
	//使用反射调用有参数的构造函数来初始化对象
	public static void createNewObject_2() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Person person = new Person(18, "xiaoming");
		
		String name = "com.startcaft.bean.Person";
		Class clazz = Class.forName(name);
		
		//获取指定的构造函数对象
		Constructor constructor = clazz.getConstructor(int.class,String.class);
		
		Object object = constructor.newInstance(23,"startcaft");
		
		System.out.println(object instanceof Person);
	}
}
