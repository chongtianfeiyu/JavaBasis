package com.startcaft.main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.startcaft.bean.Person;

public class CreateNewObjectDemo {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		
		createNewObject_1();
		createNewObject_2();
	}
	
	//ʹ�ÿղ����Ĺ��캯����ʼ��һ������
	public static void createNewObject_1() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		
		//ʹ��new������һ�������ʵ����
		com.startcaft.bean.Person person = new com.startcaft.bean.Person();
		
		//ʹ�÷���������һ�������ʵ����
		Class class1 = Class.forName("com.startcaft.bean.Person");
		
		//һ��Ҫȷ��������һ��public���޲����Ĺ��캯��,��Ȼ���׳��쳣��
		Object object = class1.newInstance();
		
		System.out.println(object instanceof Person);
	}
	
	//ʹ�÷�������в����Ĺ��캯������ʼ������
	public static void createNewObject_2() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Person person = new Person(18, "xiaoming");
		
		String name = "com.startcaft.bean.Person";
		Class clazz = Class.forName(name);
		
		//��ȡָ���Ĺ��캯������
		Constructor constructor = clazz.getConstructor(int.class,String.class);
		
		Object object = constructor.newInstance(23,"startcaft");
		
		System.out.println(object instanceof Person);
	}
}
