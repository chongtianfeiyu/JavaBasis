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
		
		//拿到字段，就可以设置/获取值了。
		Field ageField = clazz.getDeclaredField("age");
		
		//对私有字段的访问取消权限检查，暴力访问。【不建议使用】
		ageField.setAccessible(true);
		
		ageField.set(object, 23);
		
		System.out.println(ageField.getInt(object));
	}
}
