package com.startcaft.main;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class GetMethodDemo {

	public static void main(String[] args) throws Exception {
		
		getMethod_1();
		getMethod_2();
	}
	
	//获取所有方法
	public static void getMethod_1() throws ClassNotFoundException{
		
		Class clazz = Class.forName("com.startcaft.bean.Person");
		
		Method[] methods = clazz.getMethods();
		
		for(Method method : methods){
			System.out.println(method);
		}
	}
	
	//获取指定方法并调用
	public static void getMethod_2() throws Exception{
		
		Class clazz = Class.forName("com.startcaft.bean.Person");
		
		Constructor constructor = clazz.getConstructor(int.class,String.class);
		
		Object object = constructor.newInstance(22,"小明");
		
		Method showMethod = clazz.getMethod("show", null);
		
		showMethod.invoke(object, null);
	}
}
