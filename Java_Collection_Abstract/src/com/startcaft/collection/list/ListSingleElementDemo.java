package com.startcaft.collection.list;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * List��ϵ�� ����ģ��������ظ��ļ��ϣ���ΪԪ�ض��нǱꡣ
 * 
 * ��ʵ����ʾΪList����ȥ���ظ���Լ��ڵ���ʱ����Ҫ��ε���next()������
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
		
		sop("ԭʼ����:" + al);
		
		al = singleList(al);
		
		sop("ȥ���ظ�:" + al);
	}
	
	private static ArrayList<String> singleList(ArrayList<String> aList){
		
		ArrayList<String> newAl = new ArrayList<String>();
		
		Iterator<String> iterator = aList.iterator();
		
		while(iterator.hasNext()){
			String element = iterator.next();
			//String element1 = iterator.next();//����ʱ����Ҫ��ε���next()��������Ϊnext()һ�Σ����һ��hasNext()����Ȼ�ᱨNoSuchElementException�쳣
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
