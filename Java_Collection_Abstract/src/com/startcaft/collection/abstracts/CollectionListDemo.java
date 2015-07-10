package com.startcaft.collection.abstracts;

import java.util.ArrayList;
import java.util.ListIterator;

/*
 * Collection
 * 		|------List：元素是有序的，元素可以重复，因为该集合体系有索引。
 * 
 * List集合特有方法：
 * 		凡是可以操作角标的方法，都是List集合体系特有的方法
 * 
 * 添加：
 * 	add(index,element);
 * 	addAll(index  Collection);
 * 
 * 删除
 * 	remove(index);
 * 
 * 修改
 * 	set(index,element);
 * 
 * 查询
 * 	get(index);
 * 	subList(from,to);
 * 	listIterator();
 * 
 * List集合特有的迭代器：
 * 	----ListIterator是Iterator的子接口。
 * 
 * 	----在使用Iterator进行迭代时，不可以通过集合的操作集合中的元素。因为会发生ConcurrentModificationException异常。
 * 
 * 	----所以，在使用Iterator时，只能通过Iterator接口的方法操作元素，可是Iterator接口中的方法是有限的，
 * 		只能对元素进行判断，取出，删除。
 * 		如果想要其他的操作如：添加，修改，就需要使用其子接口 ListIterator
 * 
 */
public class CollectionListDemo {

	public static void main(String[] args) {

		List_Method();

		listIterator_Method();
	}

	/**
	 * List集合的特性方法演示
	 */
	private static void List_Method() {
		ArrayList<String> al = new ArrayList<>();

		al.add("java01");
		al.add("java02");
		al.add("java03");

		sop("原集合:" + al);

		// 1，在指定位置添加元素
		al.add(1, "java09");

		// 2，删除指定位置的元素
		// al.remove(2);

		// 3，修改元素
		al.set(2, "java007");

		// 4，通过角标获取元素
		sop("get(1):" + al.get(1));

		// 5，获取所有元素
		for (int i = 0; i < al.size(); i++) {

			sop("al[" + i + "]=" + al.get(i));
		}

		// 5，使用listIterator
		for (ListIterator<String> iterator = al.listIterator(); iterator
				.hasNext();) {

			sop("next:" + iterator.next());
		}

		// 6，通过indexOf获取元素的角标
		sop("index=" + al.indexOf("java01"));

		// 7，通过subList获取一个新的List
		sop("subList=" + al.subList(1, 3));

		sop("更改后的集合:" + al);
	}

	/**
	 *	
	 */
	public static void listIterator_Method() {

		ArrayList<String> al = new ArrayList<>();

		al.add("java01");
		al.add("java02");
		al.add("java03");
		
		/*
		// 在迭代过程中，准备添加或者删除元素。【迭代器在取，集合在添加，java.util.ConcurrentModificationException并发异常】
		Iterator<String> it = al.iterator();

		while (it.hasNext()) {

			Object object = it.next();

			if (object.equals("java02")) {

				//al.add("java007");//集合在操作。
				it.remove();//将java02的引用从集合中删除了。Iterator只能删除remove，所以才会有listIterator。
			}

			sop("element=" + object);
		}
		*/
		
		
		ListIterator<String> listIterator = al.listIterator();
		
		sop("hasPrevious():" + listIterator.hasPrevious());//还没开始遍历呢，所以没有前一个元素
		
		while(listIterator.hasNext()){
			
			Object object = listIterator.next();
			
			if (object.equals("java02")) {
				
				//listIterator.add("java009");//添加一个元素
				listIterator.set("java002");
			}
		}
		
		sop("hashNext():" + listIterator.hasNext());//遍历完了，所以没有下一个元素
		sop("hasPrevious():" + listIterator.hasPrevious());//遍历完了，所以有前一个元素
		
		//往前迭代
		while (listIterator.hasPrevious()) {
			
			sop("pre::" + listIterator.previous());
		}
		
		sop(al);
	}

	private static void sop(Object object) {
		System.out.println(object);
	}
}
