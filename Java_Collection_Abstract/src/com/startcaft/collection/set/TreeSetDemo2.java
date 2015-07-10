package com.startcaft.collection.set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/*
 * 当元素自身不具备元素比较性时，或者具备的比较性不是所需要的。
 * 这时就需要让集合自身具备比较性：在集合初始化构造时，就要确定比较性。
 * 
 * 1，定义一个比较器，将比较器对象作为参数传递给TreeSet集合的构造函数。
 * 	----定义一个类实现Comparator接口，并覆盖compare方法，return 0 相等。
 * 
 * 如果元素也具备比较性，集合也具备比较性，以元素的比较性为主
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
