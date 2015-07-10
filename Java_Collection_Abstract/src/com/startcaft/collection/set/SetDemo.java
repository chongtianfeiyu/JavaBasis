package com.startcaft.collection.set;

import java.util.HashSet;
import java.util.Iterator;


/*
 * java.util.List�ĳ������ࣺ��Ԫ���������(�����ȥ��˳��һ��һ��)��Ԫ�ز����ظ���
 * 
 * Set���ϵĹ��ܺ�Collection�ӿڵĹ���һ�������ص��עSet�ĳ����������
 * 		|----HashSet
 * 				�ײ����ݽṹ�ǹ�ϣ��
 * 				HashSet����α�֤Ԫ��Ψһ���أ�
 * 					----ͨ��Ԫ�ص�����������hashCode �� equals ��������ɵģ�
 * 					----���Ԫ�ص�hashCode��ͬ���Ż��ж� equals �Ƿ�Ϊtrue��
 * 					----���Ԫ�ص�hashCode��ͬ���򲻻���� equals
 * 				
 * 				ע�⣺����HashSet���ж�Ԫ���Ƿ���ڣ����Ԫ�أ�ɾ�������������ķ�����Ԫ�ص� hashCode �� equals ������
 * 		|----TreeSet
 * 
 */
public class SetDemo {

	public static void main(String[] agrs){
		
		HashSet<Student> hashSet = new HashSet<>();
		
		hashSet.add(new Student("a1", 11));
		hashSet.add(new Student("a2", 12));
		hashSet.add(new Student("a3", 13));
		//hashSet.add(new Student("a2", 12));
		
		
		//sop(hashSet.contains(new Student("a1", 11)));//����Ҳһ�������ж�hashCode�����ظ��ˣ�Ȼ���ж� equals��
		
		hashSet.remove(new Student("a3", 13));
		
		Iterator<Student> it = hashSet.iterator();
		
		while(it.hasNext()){
			
			Student student = it.next();
			sop(student.getName() + "::" + student.getAge());
		}
	}
	
	private static void sop(Object object){
		System.out.println(object);
	}
}


class Student{
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
	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	@Override
	public int hashCode() {
		System.out.println(this.name + "...hashcode");
		return this.name.hashCode() + this.age * 27;//Ҫ��������hashCode��Ψһ�ԡ�
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Student)) {
			return false;
		}
		Student student = (Student)obj;
		System.out.println(this.name + "...equals..." +  student.name);
		
		return this.name.equals(student.name) && this.age == student.age;
	}
}