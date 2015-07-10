package com.startcaft.collection.abstracts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
 * 为什么会出现集合？
 * 		在OOP编程中，所有的数据都以对象的形式体现。
 * 		为了方便对多个对象的操作，就要对对象进行存储，集合就是存储对象最常见的方式之一。
 * 
 * 数组和集合有什么同?
 * 		1，数组一旦初始化，长度就不可变，而集合的长度是可变的。
 * 		2，集合可以存储不同类型的对象，而数组中存储的对象类型必须一致。
 * 
 * 集合和数组中存储的都是对象的地址。
 * 
 * Java中的集合体系【常用接口，以及常用的实现类】：
 * java.util.Collection<T>接口：它继承自java.util.Iterable<T>迭代器接口【该接口只有一个方法iterator() 返回一个java.util.Iterator<T>接口对象】
 * 		|
 * 		--------Java.util.List<T>接口
 * 					|
 * 					--------ArrayList
 * 					|
 * 					--------LinkedList
 * 					|
 * 					--------Vector
 * 		|
 * 		--------Java.util.Set<T>接口
 * 					|
 * 					--------HashSet
 * 					|
 * 					--------TreeSet
 */
public class CollectionInterfaceDemo {

	public static void main(String[] args) {

		collectionBaseMethods();
		CollectionBaseMethods2();
		collection_Get();
	}

	/**
	 * Collection接口的基本操作。
	 */
	private static void collectionBaseMethods() {
		// 创建一个集合容器，使用Collection接口的子类，ArrayList
		Collection<String> collection = new ArrayList<>();

		// 1，添加元素
		collection.add("java01");
		collection.add("java02");
		collection.add("java03");
		collection.add("java04");

		// 打印原集合
		sop("原始集合:" + collection);

		// 2，获取元素，集合的长度
		sop("size: " + collection.size());

		// 3，删除元素
		collection.remove("java02");
		// 清空集合
		// collection.clear();

		// 4，判断元素
		sop("java03是否存在：" + collection.contains("java03"));
		sop("集合是否为空: " + collection.isEmpty());

		// 打印更改后的集合
		sop("更改后集合:" + collection);
	}

	/**
	 * 集合的交集操作
	 */
	public static void CollectionBaseMethods2() {

		Collection<String> c1 = new ArrayList<>();

		c1.add("java01");
		c1.add("java02");
		c1.add("java03");
		c1.add("java04");

		Collection<String> c2 = new ArrayList<>();

		c2.add("java01");
		c2.add("java02");
		c2.add("java05");
		c2.add("java06");
		
//		c2.add("java07");
//		c2.add("java08");
//		c2.add("java09");
//		c2.add("java010");

		//c1.retainAll(c2);//取出交集项。
		c1.removeAll(c2);//删除交集项
		
		sop("c1:" + c1);
		sop("c2:" + c2);
	}
	
	/**
	 * 集合的迭代，Iterator迭代器
	 * 因为每种集合类型之间在数据机构上的不同，所以在取出集合时，定义一个规则，该规则就是Iterator。
	 * 并在集合通过一个对外的方法iterator()获取到适合自己的迭代器。
	 * 内部类实现的，看源码
	 */
	public static void collection_Get(){
		
		Collection<String> c1 = new ArrayList<>();

		c1.add("java01");
		c1.add("java02");
		c1.add("java03");
		c1.add("java04");
		
		//获取迭代器，用于取出集合中的元素
//		Iterator<String> iterator = c1.iterator();
//		
//		while(iterator.hasNext()){
//			
//			sop(iterator.next());
//		}
		
		
		//老外的写法,iterator为局部变量。节约内存
		for(Iterator<String> iterator = c1.iterator(); iterator.hasNext();){
			
			sop(iterator.next());
		}
		
	}

	private static void sop(Object object) {
		System.out.println(object);
	}
}
