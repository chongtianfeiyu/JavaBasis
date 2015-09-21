package com.startcaft.java.annotations;

import java.util.ArrayList;
import java.util.List;

/*
 * 从jdk5.0开始，java增加了对元数据(MetaData)的支持，也就是Annotation注解；
 * Annotation其实就是代码里的特殊标记，这些标记可以在编译，类加载，运行时被读取，并执行相应的逻辑，通过使用Annotation，程序员可以在不改变原有逻辑的情况下，在源文件中嵌入一些补充信息；
 * Annotation可以像修饰符一样被使用，可以用于【修饰包，构造器，方法，成员变量，参数，局部变量的声明】，这些信息被保存在Annotation的name=value中；
 * Annotation能够被用来为程序元素(类，方法，成员变量等)设置元数据；
 * 使用Annotation时要在其前面增加@符号，并把该Annotation当成一个修饰符使用，用于修饰它支持的程序元素
 * 
 * 三个基本的Annotation：
 * 1，@Override---限定重写父类方法，该注解只能用于方法
 * 2，@Deprecated---用于表示某个程序元素(类，方法等)已过时
 * 3，@SuppressWarnings---抑制编译器警告
 * 
 * 一，如果定义一个注解
 * 二，元注解
 * 		JDK的元注解用于修饰其他的Annotation定义，JDK5.0提供了专门在注解上的注解类型，分别是：
 * 		1，Retention---用于修饰一个Annotation定义，指定该Annotation可以保留多长时间，它包含一个RetentionPolicy类型的成员变量
 * 					RetentionPolicy.SOURCE---编译器直接丢弃这种策略的注释；
 * 					RetentionPolicy.CLASS---编译器将把注释记录在class文件中，这是默认值
 * 					RetentionPolicy.RUNTIME---编译器将把注释记录在class文件中，当运行程序时，JVM保留注释，程序可以通过反射获取该注释。
 * 		2，Target---用于修饰Annotation定义，指定该Annotation能用于修饰哪些程序元素。
 * 		3，Documented---用于指定该元Annotation修饰的类将被javadoc提供提取成文件
 * 					定义为Documented的注解必须设置Retention值为RUNTIME
 * 		4，Inherited---被它修饰的Annotation将具有继承性，如果某个类使用了被@Inherited修饰的Annotation，则子类将自动具有该注解。
 */
public class TestAnnotation {
	
	public static void main(String[] args) {
		
		Person p = new Student();
		p.walk();
		p.eat();
		
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		
		System.out.println(list);
	}
	
}

@MyAnnotation(value="startcaft")
class Student extends Person{
	
	@Override
	public void walk() {
		System.out.println("学生走路");
	}
	
	@Override
	public void eat() {
		System.out.println("学生吃饭");
	}
}

@Deprecated
class Person{
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Person() {
		super();
	}
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public void walk(){
		System.out.println("走路");
	}
	@Deprecated
	public void eat(){
		System.out.println("吃饭");
	}
}
