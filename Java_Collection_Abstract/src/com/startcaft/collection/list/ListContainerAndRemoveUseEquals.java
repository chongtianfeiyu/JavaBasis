package com.startcaft.collection.list;

import java.util.ArrayList;
import java.util.Iterator;

public class ListContainerAndRemoveUseEquals {

	public static void main(String[] args) {

		/*
		 * ��ʵ��˵��List���ϵ�Contain��Remove���� �ײ㶼��������Ԫ�ص� equals ������
		 * 
		 * �����ڼ����бȽ��Զ��������Ҫ��д�Զ������ıȽ��߼���
		 */

		ArrayList<Person> persons = new ArrayList<Person>();
		persons.add(new Person("lisi01", 30));
		persons.add(new Person("lisi02", 31));
		persons.add(new Person("lisi03", 32));
		persons.add(new Person("lisi02", 31));

		// ��ӡԭʼ����
		for (Iterator<Person> iterator = persons.iterator(); iterator.hasNext();) {
			Person person = (Person) iterator.next();
			sop(person.getName() + "::" + person.getAge());
		}

		sop("--------------------------------------");
		
		sop("remove:01===" + persons.remove(new Person("lisi01", 30)));//���Person����û����дequals,�Ǿ���false����Ϊ������ÿ��Ԫ�ص����ö���һ��������new�����ġ�

		persons = singelPerson(persons);

		// ��ӡ���ĺ�ļ���
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
			if (!newPersons.contains(person)) {//�����contains����������Person�����equals������
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
		System.out.println("��ǰ����:" + this.name + "---�Ƚ϶���:" + person.name);
		return this.name.equals(person.name) && this.age == person.age;
	}
}