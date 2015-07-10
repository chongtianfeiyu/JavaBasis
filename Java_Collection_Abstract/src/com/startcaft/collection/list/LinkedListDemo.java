package com.startcaft.collection.list;

import java.util.LinkedList;


/*
 * LinekList特有方法：
 * ----addFirst();
 * ----addLast();
 * 
 * ----removeFirst();
 * ----removeLast();
 * 		remove方法的特点是：也可以获取元素，还删除元素。
 * 
 * ----getFirsrt();
 * ----getLast();
 * 		get方法的特点是：获取元素，但不删除元素
 * 
 * 以上的方法是JDK1.6以前的的方法，这些方法在调用时【如果此列表为null，则抛出NoSuchElementException异常】
 * 
 * JDK1.6以后，出现了如替代方法：这些方法在调用时【如果此列表为null，则返回null，标识调用不成功】
 * 
 * ----offerFirst();【替代addFirst();】
 * ----offerLast();【替代addLast();】
 * 
 * ----peekFirst();【替代getFirst();】
 * ----peekLast();【替代getLast);】
 * 
 * ----pollFirst();【替代removeFirst();】
 * ----pollLast();【替代removeLast();】
 * 
 */
public class LinkedListDemo {

	public static void main(String[] args) {
		
		LinkedList<String> linkedList = new LinkedList<String>();
		
//		linkedList.addFirst("java01");
//		linkedList.addFirst("java02");
//		linkedList.addFirst("java03");
//		linkedList.addFirst("java04");
		
		linkedList.addLast("java01");
		linkedList.addLast("java02");
		linkedList.addLast("java03");
		linkedList.addLast("java04");
		
		//sop("first:" + linkedList.getFirst());
		//sop("last:" + linkedList.getLast());
		
		
		//sop(linkedList.removeFirst());
		
		//sop("size:" + linkedList.size());
		
		//sop(linkedList);
		
		while(!linkedList.isEmpty()){
			//sop(linkedList.removeFirst());
			sop(linkedList.removeLast());
		}
	}
	
	private static void sop(Object object){
		System.out.println(object);
	}
}
