package com.startcaft.collection.set;

import java.util.Iterator;
import java.util.TreeSet;

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
 * 				可以对Set集合中的元素进行排序。
 * 				底层数据结构是 二叉树。
 * 				保证元素唯一性的依据：compareTo方法return 0；
 * 				
 * 				TreeSet排序的第一种方式：让元素自身具备比较性，元素需要实现Comparable接口，覆盖compareTo方法。【自然顺序，默认顺序】
 * 
 * 				TreeSet排序的第二种方式：当元素自身不具备元素比较性时，或者具备的比较性不是所需要的。
 * 				这时就需要让集合自身具备比较性：在集合初始化构造时，就要确定比较性。
 */
public class TreeSetDemo {

	public static void main(String[] args) {
		
		TreeSet<Entity> ts = new TreeSet<>();
		
		ts.add(new Entity("lisi", 11));
		ts.add(new Entity("lisi01", 12));
		ts.add(new Entity("lisi02", 11));
		ts.add(new Entity("lisi01", 12));
		
		Iterator<Entity> it = ts.iterator();
		
		while(it.hasNext()){
			Entity entity = it.next();
			System.out.println(entity.getName() + "::" + entity.getAge());
		}
	}
}

/*
 * 如果不实现 Comparable 接口，则无法保存在TreeSet容器中，除非只保存一个该对象。
 */
class Entity implements Comparable<Entity>{
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
	public Entity(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public int compareTo(Entity o) {
		
		System.out.println(this.name + ".........compareTo..........." + o.name);
		
		if (this.age > o.age) {
			return 1;
		}
		//主要属性如果相等，则比较次要属性。
		if(this.age == o.age) {
			return this.name.compareTo(o.name);
		}
		return -1;
		
	}
}
