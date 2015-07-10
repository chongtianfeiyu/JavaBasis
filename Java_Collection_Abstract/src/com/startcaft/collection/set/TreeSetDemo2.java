package com.startcaft.collection.set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/*
 * ��Ԫ�������߱�Ԫ�رȽ���ʱ�����߾߱��ıȽ��Բ�������Ҫ�ġ�
 * ��ʱ����Ҫ�ü�������߱��Ƚ��ԣ��ڼ��ϳ�ʼ������ʱ����Ҫȷ���Ƚ��ԡ�
 * 
 * 1������һ���Ƚ��������Ƚ���������Ϊ�������ݸ�TreeSet���ϵĹ��캯����
 * 	----����һ����ʵ��Comparator�ӿڣ�������compare������return 0 ��ȡ�
 * 
 * ���Ԫ��Ҳ�߱��Ƚ��ԣ�����Ҳ�߱��Ƚ��ԣ���Ԫ�صıȽ���Ϊ��
 */
public class TreeSetDemo2 {

	public static void main(String[] args) {

		TreeSet<Demo> ts = new TreeSet<Demo>(new MyCompare());

		ts.add(new Demo("lisi", 11));
		ts.add(new Demo("lisi05", 13));
		ts.add(new Demo("lisi01", 12));
		ts.add(new Demo("lisi02", 11));
		ts.add(new Demo("lisi06", 13));
		ts.add(new Demo("lisi02", 11));

		Iterator<Demo> it = ts.iterator();

		while (it.hasNext()) {
			Demo entity = it.next();
			System.out.println(entity.getName() + "::" + entity.getAge());
		}
	}
}

class MyCompare implements Comparator<Demo> {

	@Override
	public int compare(Demo o1, Demo o2) {

		int num = o1.getName().compareTo(o2.getName());
		if (num == 0) {
			
			return new Integer(o1.getAge()).compareTo(new Integer(o2.getAge()));
			
//			if (o1.getAge() > o2.getAge()) {
//				 return 1;
//			}
//			if (o1.getAge() < o2.getAge()) {
//				 return -1;
//			}
//			return 0;
		}
		return num;
	}
}

class Demo {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Demo(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
}
