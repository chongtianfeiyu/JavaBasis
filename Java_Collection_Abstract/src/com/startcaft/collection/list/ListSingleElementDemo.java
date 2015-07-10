package com.startcaft.collection.list;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * List体系是 有序的，可以有重复的集合，因为元素都有角标。
 * 
 * 该实例演示为List集合去除重复项，以及在迭代时，不要多次调用next()方法。
 */
public class ListSingleElementDemo {

	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<String>();
		al.add("java01");
		al.add("java02");
		al.add("java03");
		al.add("java01");
		al.add("java02");
		al.add("java03");
		
		sop("原始集合:" + al);
		
		al = singleList(al);
		
		sop("去除重复:" + al);
	}
	
	private static ArrayList<String> singleList(ArrayList<String> aList){
		
		ArrayList<String> newAl = new ArrayList<String>();
		
		Iterator<String> iterator = aList.iterator();
		
		while(iterator.hasNext()){
			String element = iterator.next();
			//String element1 = iterator.next();//迭代时，不要多次调用next()方法，因为next()一次，检测一次hasNext()。不然会报NoSuchElementException异常
			if (!newAl.contains(element)) {
				newAl.add(element);
			}
		}
		return newAl;
	}
	
	private static void sop(Object object){
		System.out.println(object);
	}
}
