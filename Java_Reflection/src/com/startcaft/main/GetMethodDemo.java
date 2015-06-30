package com.startcaft.main;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class GetMethodDemo {

	public static void main(String[] args) throws Exception {
		
		getMethod_1();
		getMethod_2();
	}
	
	//��ȡ���з���
	public static void getMethod_1() throws ClassNotFoundException{
		
		Class clazz = Class.forName("com.startcaft.bean.Person");
		
		Method[] methods = clazz.getMethods();
		
		for(Method method : methods){
			System.out.println(method);
		}
	}
	
	//��ȡָ������������
	public static void getMethod_2() throws Exception{
		
		Class clazz = Class.forName("com.startcaft.bean.Person");
		
		Constructor constructor = clazz.getConstructor(int.class,String.class);
		
		Object object = constructor.newInstance(22,"С��");
		
		Method showMethod = clazz.getMethod("show", null);
		
		showMethod.invoke(object, null);
	}
}
