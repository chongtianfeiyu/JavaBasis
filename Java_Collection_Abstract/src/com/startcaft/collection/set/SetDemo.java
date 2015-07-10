package com.startcaft.collection.set;

import java.util.HashSet;
import java.util.Iterator;


/*
 * java.util.List的常用子类：【元素是无序的(存入和去除顺序不一定一致)，元素不可重复】
 * 
 * Set集合的功能和Collection接口的功能一样。【重点关注Set的常见子类对象】
 * 		|----HashSet
 * 				底层数据结构是哈希表。
 * 				HashSet是如何保证元素唯一性呢？
 * 					----通过元素的两个方法，hashCode 和 equals 方法来完成的；
 * 					----如果元素的hashCode相同，才会判断 equals 是否为true；
 * 					----如果元素的hashCode不同，则不会调用 equals
 * 				
 * 				注意：对于HashSet的判断元素是否存在，添加元素，删除方法，依赖的方法是元素的 hashCode 和 equals 方法。
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
		
		
		//sop(hashSet.contains(new Student("a1", 11)));//这里也一样，先判断hashCode，有重复了，然后判断 equals。
		
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
		return this.name.hashCode() + this.age * 27;//要尽量保持hashCode的唯一性。
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