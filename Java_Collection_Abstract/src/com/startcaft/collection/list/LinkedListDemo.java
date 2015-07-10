package com.startcaft.collection.list;

import java.util.LinkedList;


/*
 * LinekList���з�����
 * ----addFirst();
 * ----addLast();
 * 
 * ----removeFirst();
 * ----removeLast();
 * 		remove�������ص��ǣ�Ҳ���Ի�ȡԪ�أ���ɾ��Ԫ�ء�
 * 
 * ----getFirsrt();
 * ----getLast();
 * 		get�������ص��ǣ���ȡԪ�أ�����ɾ��Ԫ��
 * 
 * ���ϵķ�����JDK1.6��ǰ�ĵķ�������Щ�����ڵ���ʱ��������б�Ϊnull�����׳�NoSuchElementException�쳣��
 * 
 * JDK1.6�Ժ󣬳������������������Щ�����ڵ���ʱ��������б�Ϊnull���򷵻�null����ʶ���ò��ɹ���
 * 
 * ----offerFirst();�����addFirst();��
 * ----offerLast();�����addLast();��
 * 
 * ----peekFirst();�����getFirst();��
 * ----peekLast();�����getLast);��
 * 
 * ----pollFirst();�����removeFirst();��
 * ----pollLast();�����removeLast();��
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
