package com.startcaft.collection.list;

import java.util.ArrayList;
import java.util.Iterator;

public class ListContainerAndRemoveUseEquals {

	public static void main(String[] args) {

		/*
		 * 该实例说明List集合的Contain和Remove方法 底层都是依赖的元素的 equals 方法。
		 * 
		 * 所以在集合中比较自定义对象，需要重写自定义对象的比较逻辑。
		 */

		ArrayList<Person> persons = new ArrayList<Person>();
		persons.add(new Person("lisi01", 30));
		persons.add(new Person("lisi02", 31));
		persons.add(new Person("lisi03", 32));
		persons.add(new Person("lisi02", 31));

		// 打印原始集合
		for (Iterator<Person> iterator = persons.iterator(); iterator.hasNext();) {
			Person person = (Person) iterator.next();
			sop(person.getName() + "::" + person.getAge());
		}

		sop("--------------------------------------");
		
		sop("remove:01===" + persons.remove(new Person("lisi01", 30)));//如果Person对象没有重写equals,那就是false，因为集合里每个元素的引用都不一样，都是new出来的。

		persons = singelPerson(persons);

		// 打印更改后的集合
		for (Iterator<Person> iterator = persons.iterator(); iterator.hasNext();) {
			Person person = (Person) iterator.next();
			sop(person.getName() + "::" + person.getAge());
		}

	}

	private static ArrayList<Person> singelPerson(ArrayList<Person> pList) {

		ArrayList<Person> newPersons = new ArrayList<>();

		Iterator<Person> iterator = pList.iterator();

		while (iterator.hasNext()) {
			Person person = iterator.next();
			if (!newPersons.contains(person)) {//这里的contains方法依赖于Person对象的equals方法。
				newPersons.add(person);
			}
		}
		return newPersons;
	}

	private static void sop(Object object) {
		System.out.println(object);
	}
}

class Person {

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

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Person)) {
			return false;
		}
		Person person = (Person) obj;
		System.out.println("当前对象:" + this.name + "---比较对象:" + person.name);
		return this.name.equals(person.name) && this.age == person.age;
	}
}