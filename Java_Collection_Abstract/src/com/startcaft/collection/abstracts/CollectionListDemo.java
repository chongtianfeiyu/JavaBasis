package com.startcaft.collection.abstracts;

import java.util.ArrayList;
import java.util.ListIterator;

/*
 * Collection
 * 		|------List��Ԫ��������ģ�Ԫ�ؿ����ظ�����Ϊ�ü�����ϵ��������
 * 
 * List�������з�����
 * 		���ǿ��Բ����Ǳ�ķ���������List������ϵ���еķ���
 * 
 * ��ӣ�
 * 	add(index,element);
 * 	addAll(index  Collection);
 * 
 * ɾ��
 * 	remove(index);
 * 
 * �޸�
 * 	set(index,element);
 * 
 * ��ѯ
 * 	get(index);
 * 	subList(from,to);
 * 	listIterator();
 * 
 * List�������еĵ�������
 * 	----ListIterator��Iterator���ӽӿڡ�
 * 
 * 	----��ʹ��Iterator���е���ʱ��������ͨ�����ϵĲ��������е�Ԫ�ء���Ϊ�ᷢ��ConcurrentModificationException�쳣��
 * 
 * 	----���ԣ���ʹ��Iteratorʱ��ֻ��ͨ��Iterator�ӿڵķ�������Ԫ�أ�����Iterator�ӿ��еķ��������޵ģ�
 * 		ֻ�ܶ�Ԫ�ؽ����жϣ�ȡ����ɾ����
 * 		�����Ҫ�����Ĳ����磺��ӣ��޸ģ�����Ҫʹ�����ӽӿ� ListIterator
 * 
 */
public class CollectionListDemo {

	public static void main(String[] args) {

		List_Method();

		listIterator_Method();
	}

	/**
	 * List���ϵ����Է�����ʾ
	 */
	private static void List_Method() {
		ArrayList<String> al = new ArrayList<>();

		al.add("java01");
		al.add("java02");
		al.add("java03");

		sop("ԭ����:" + al);

		// 1����ָ��λ�����Ԫ��
		al.add(1, "java09");

		// 2��ɾ��ָ��λ�õ�Ԫ��
		// al.remove(2);

		// 3���޸�Ԫ��
		al.set(2, "java007");

		// 4��ͨ���Ǳ��ȡԪ��
		sop("get(1):" + al.get(1));

		// 5����ȡ����Ԫ��
		for (int i = 0; i < al.size(); i++) {

			sop("al[" + i + "]=" + al.get(i));
		}

		// 5��ʹ��listIterator
		for (ListIterator<String> iterator = al.listIterator(); iterator
				.hasNext();) {

			sop("next:" + iterator.next());
		}

		// 6��ͨ��indexOf��ȡԪ�صĽǱ�
		sop("index=" + al.indexOf("java01"));

		// 7��ͨ��subList��ȡһ���µ�List
		sop("subList=" + al.subList(1, 3));

		sop("���ĺ�ļ���:" + al);
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
		// �ڵ��������У�׼����ӻ���ɾ��Ԫ�ء�����������ȡ����������ӣ�java.util.ConcurrentModificationException�����쳣��
		Iterator<String> it = al.iterator();

		while (it.hasNext()) {

			Object object = it.next();

			if (object.equals("java02")) {

				//al.add("java007");//�����ڲ�����
				it.remove();//��java02�����ôӼ�����ɾ���ˡ�Iteratorֻ��ɾ��remove�����ԲŻ���listIterator��
			}

			sop("element=" + object);
		}
		*/
		
		
		ListIterator<String> listIterator = al.listIterator();
		
		sop("hasPrevious():" + listIterator.hasPrevious());//��û��ʼ�����أ�����û��ǰһ��Ԫ��
		
		while(listIterator.hasNext()){
			
			Object object = listIterator.next();
			
			if (object.equals("java02")) {
				
				//listIterator.add("java009");//���һ��Ԫ��
				listIterator.set("java002");
			}
		}
		
		sop("hashNext():" + listIterator.hasNext());//�������ˣ�����û����һ��Ԫ��
		sop("hasPrevious():" + listIterator.hasPrevious());//�������ˣ�������ǰһ��Ԫ��
		
		//��ǰ����
		while (listIterator.hasPrevious()) {
			
			sop("pre::" + listIterator.previous());
		}
		
		sop(al);
	}

	private static void sop(Object object) {
		System.out.println(object);
	}
}
