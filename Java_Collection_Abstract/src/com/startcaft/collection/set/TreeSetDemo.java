package com.startcaft.collection.set;

import java.util.Iterator;
import java.util.TreeSet;

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
 * 				���Զ�Set�����е�Ԫ�ؽ�������
 * 				�ײ����ݽṹ�� ��������
 * 				��֤Ԫ��Ψһ�Ե����ݣ�compareTo����return 0��
 * 				
 * 				TreeSet����ĵ�һ�ַ�ʽ����Ԫ������߱��Ƚ��ԣ�Ԫ����Ҫʵ��Comparable�ӿڣ�����compareTo����������Ȼ˳��Ĭ��˳��
 * 
 * 				TreeSet����ĵڶ��ַ�ʽ����Ԫ�������߱�Ԫ�رȽ���ʱ�����߾߱��ıȽ��Բ�������Ҫ�ġ�
 * 				��ʱ����Ҫ�ü�������߱��Ƚ��ԣ��ڼ��ϳ�ʼ������ʱ����Ҫȷ���Ƚ��ԡ�
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
 * �����ʵ�� Comparable �ӿڣ����޷�������TreeSet�����У�����ֻ����һ���ö���
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
		//��Ҫ���������ȣ���Ƚϴ�Ҫ���ԡ�
		if(this.age == o.age) {
			return this.name.compareTo(o.name);
		}
		return -1;
		
	}
}
