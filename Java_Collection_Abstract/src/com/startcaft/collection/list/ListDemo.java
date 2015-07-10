package com.startcaft.collection.list;

import java.util.Enumeration;
import java.util.Vector;

/*
 * java.util.List的常用子类：【元素是有序的，元素可以重复，因为该集合体系有索引】
 * 		----ArrayList:
 * 			底层的数据结构使用的是数组结构【角标嘛】
 * 			其特点是：
 * 			----1)，查询速度快，【直接根据角标查嘛】
 * 			----2)，增加，删除，稍慢(数据多的时候会有所体现)，因为会修改其他元素的角标。
 * 
 * 		----LinkedList:
 * 			底层的数据结构使用链表数据结构【一个元素记住相邻的元素】
 * 			其特点：
 * 			----1)，增加，删除，速度很快【只修改元素的关系即可】
 * 			----2)，查询很慢【跟排队一样，一个问一个才知道】
 * 
 * 		----Vector:【元老级类型，1.0出现，集合框架1.2才出现】
 * 			底层的数据结构使用的是数据结构【角标】
 * 			其特点：
 * 			----1)，线程同步的，ArrayList是线程不同步的，所以被ArrayList替代【多线程中ArrayList可以自己加锁嘛】
 */
public class ListDemo {

	public static void main(String[] args) {
		
		vector_Method();
	}
	
	/**
	 * Enumeration<E> 枚举 是Vector特有的取出方式。JDK1.0就有
	 * 
	 * 枚举和迭代器很像：在API文档中如下介绍Enumeration<E>
	 * 
	 * Enumeration<E>接口的功能和 Iterator接口的功能是重复的，Iterator接口添加了一个可选的移除操作，并使用较短的方法，
	 * 新的实现应该优先考虑Iterator接口，而不是Enumeration<E>接口。
	 * 
	 * 为什么要用Enumeration接口，因为IO包的有一个对象使用了Enumeration接口。其他的对象都统一使用Iterator接口了。
	 */
	public static void vector_Method(){
		
		Vector<String> vector = new Vector<String>();
		
		vector.add("java01");
		vector.add("java02");
		vector.add("java03");
		vector.add("java04");
		
		Enumeration<String> enumeration = vector.elements();
		
		while (enumeration.hasMoreElements()) {
			
			String ele = (String) enumeration.nextElement();
			System.out.println(ele);
		}
	}
}
