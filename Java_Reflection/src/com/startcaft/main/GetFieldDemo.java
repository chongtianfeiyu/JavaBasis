package com.startcaft.main;

import java.lang.reflect.Field;


public class GetFieldDemo {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException {

		getField();
	}
	
	
	public static void getField() throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		
		Class clazz = Class.forName("com.startcaft.bean.Person");
		
		Object object = clazz.newInstance();
		
		//Field ageField = clazz.getField("age");
		
		//�õ��ֶΣ��Ϳ�������/��ȡֵ�ˡ�
		Field ageField = clazz.getDeclaredField("age");
		
		//��˽���ֶεķ���ȡ��Ȩ�޼�飬�������ʡ���������ʹ�á�
		ageField.setAccessible(true);
		
		ageField.set(object, 23);
		
		System.out.println(ageField.getInt(object));
	}
}
