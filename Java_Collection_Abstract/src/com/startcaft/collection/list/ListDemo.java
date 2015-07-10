package com.startcaft.collection.list;

import java.util.Enumeration;
import java.util.Vector;

/*
 * java.util.List�ĳ������ࣺ��Ԫ��������ģ�Ԫ�ؿ����ظ�����Ϊ�ü�����ϵ��������
 * 		----ArrayList:
 * 			�ײ�����ݽṹʹ�õ�������ṹ���Ǳ��
 * 			���ص��ǣ�
 * 			----1)����ѯ�ٶȿ죬��ֱ�Ӹ��ݽǱ���
 * 			----2)�����ӣ�ɾ��������(���ݶ��ʱ�����������)����Ϊ���޸�����Ԫ�صĽǱꡣ
 * 
 * 		----LinkedList:
 * 			�ײ�����ݽṹʹ���������ݽṹ��һ��Ԫ�ؼ�ס���ڵ�Ԫ�ء�
 * 			���ص㣺
 * 			----1)�����ӣ�ɾ�����ٶȺܿ졾ֻ�޸�Ԫ�صĹ�ϵ���ɡ�
 * 			----2)����ѯ���������Ŷ�һ����һ����һ����֪����
 * 
 * 		----Vector:��Ԫ�ϼ����ͣ�1.0���֣����Ͽ��1.2�ų��֡�
 * 			�ײ�����ݽṹʹ�õ������ݽṹ���Ǳ꡿
 * 			���ص㣺
 * 			----1)���߳�ͬ���ģ�ArrayList���̲߳�ͬ���ģ����Ա�ArrayList��������߳���ArrayList�����Լ������
 */
public class ListDemo {

	public static void main(String[] args) {
		
		vector_Method();
	}
	
	/**
	 * Enumeration<E> ö�� ��Vector���е�ȡ����ʽ��JDK1.0����
	 * 
	 * ö�ٺ͵�����������API�ĵ������½���Enumeration<E>
	 * 
	 * Enumeration<E>�ӿڵĹ��ܺ� Iterator�ӿڵĹ������ظ��ģ�Iterator�ӿ������һ����ѡ���Ƴ���������ʹ�ý϶̵ķ�����
	 * �µ�ʵ��Ӧ�����ȿ���Iterator�ӿڣ�������Enumeration<E>�ӿڡ�
	 * 
	 * ΪʲôҪ��Enumeration�ӿڣ���ΪIO������һ������ʹ����Enumeration�ӿڡ������Ķ���ͳһʹ��Iterator�ӿ��ˡ�
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
