package com.startcaft.collection.abstracts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
 * Ϊʲô����ּ��ϣ�
 * 		��OOP����У����е����ݶ��Զ������ʽ���֡�
 * 		Ϊ�˷���Զ������Ĳ�������Ҫ�Զ�����д洢�����Ͼ��Ǵ洢��������ķ�ʽ֮һ��
 * 
 * ����ͼ�����ʲôͬ?
 * 		1������һ����ʼ�������ȾͲ��ɱ䣬�����ϵĳ����ǿɱ�ġ�
 * 		2�����Ͽ��Դ洢��ͬ���͵Ķ��󣬶������д洢�Ķ������ͱ���һ�¡�
 * 
 * ���Ϻ������д洢�Ķ��Ƕ���ĵ�ַ��
 * 
 * Java�еļ�����ϵ�����ýӿڣ��Լ����õ�ʵ���ࡿ��
 * java.util.Collection<T>�ӿڣ����̳���java.util.Iterable<T>�������ӿڡ��ýӿ�ֻ��һ������iterator() ����һ��java.util.Iterator<T>�ӿڶ���
 * 		|
 * 		--------Java.util.List<T>�ӿ�
 * 					|
 * 					--------ArrayList
 * 					|
 * 					--------LinkedList
 * 					|
 * 					--------Vector
 * 		|
 * 		--------Java.util.Set<T>�ӿ�
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
	 * Collection�ӿڵĻ���������
	 */
	private static void collectionBaseMethods() {
		// ����һ������������ʹ��Collection�ӿڵ����࣬ArrayList
		Collection<String> collection = new ArrayList<>();

		// 1�����Ԫ��
		collection.add("java01");
		collection.add("java02");
		collection.add("java03");
		collection.add("java04");

		// ��ӡԭ����
		sop("ԭʼ����:" + collection);

		// 2����ȡԪ�أ����ϵĳ���
		sop("size: " + collection.size());

		// 3��ɾ��Ԫ��
		collection.remove("java02");
		// ��ռ���
		// collection.clear();

		// 4���ж�Ԫ��
		sop("java03�Ƿ���ڣ�" + collection.contains("java03"));
		sop("�����Ƿ�Ϊ��: " + collection.isEmpty());

		// ��ӡ���ĺ�ļ���
		sop("���ĺ󼯺�:" + collection);
	}

	/**
	 * ���ϵĽ�������
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

		//c1.retainAll(c2);//ȡ�������
		c1.removeAll(c2);//ɾ��������
		
		sop("c1:" + c1);
		sop("c2:" + c2);
	}
	
	/**
	 * ���ϵĵ�����Iterator������
	 * ��Ϊÿ�ּ�������֮�������ݻ����ϵĲ�ͬ��������ȡ������ʱ������һ�����򣬸ù������Iterator��
	 * ���ڼ���ͨ��һ������ķ���iterator()��ȡ���ʺ��Լ��ĵ�������
	 * �ڲ���ʵ�ֵģ���Դ��
	 */
	public static void collection_Get(){
		
		Collection<String> c1 = new ArrayList<>();

		c1.add("java01");
		c1.add("java02");
		c1.add("java03");
		c1.add("java04");
		
		//��ȡ������������ȡ�������е�Ԫ��
//		Iterator<String> iterator = c1.iterator();
//		
//		while(iterator.hasNext()){
//			
//			sop(iterator.next());
//		}
		
		
		//�����д��,iteratorΪ�ֲ���������Լ�ڴ�
		for(Iterator<String> iterator = c1.iterator(); iterator.hasNext();){
			
			sop(iterator.next());
		}
		
	}

	private static void sop(Object object) {
		System.out.println(object);
	}
}
