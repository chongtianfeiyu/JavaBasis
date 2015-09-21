package com.startcaft.java.annotations;

import java.util.ArrayList;
import java.util.List;

/*
 * ��jdk5.0��ʼ��java�����˶�Ԫ����(MetaData)��֧�֣�Ҳ����Annotationע�⣻
 * Annotation��ʵ���Ǵ�����������ǣ���Щ��ǿ����ڱ��룬����أ�����ʱ����ȡ����ִ����Ӧ���߼���ͨ��ʹ��Annotation������Ա�����ڲ��ı�ԭ���߼�������£���Դ�ļ���Ƕ��һЩ������Ϣ��
 * Annotation���������η�һ����ʹ�ã��������ڡ����ΰ�������������������Ա�������������ֲ�����������������Щ��Ϣ��������Annotation��name=value�У�
 * Annotation�ܹ�������Ϊ����Ԫ��(�࣬��������Ա������)����Ԫ���ݣ�
 * ʹ��AnnotationʱҪ����ǰ������@���ţ����Ѹ�Annotation����һ�����η�ʹ�ã�����������֧�ֵĳ���Ԫ��
 * 
 * ����������Annotation��
 * 1��@Override---�޶���д���෽������ע��ֻ�����ڷ���
 * 2��@Deprecated---���ڱ�ʾĳ������Ԫ��(�࣬������)�ѹ�ʱ
 * 3��@SuppressWarnings---���Ʊ���������
 * 
 * һ���������һ��ע��
 * ����Ԫע��
 * 		JDK��Ԫע����������������Annotation���壬JDK5.0�ṩ��ר����ע���ϵ�ע�����ͣ��ֱ��ǣ�
 * 		1��Retention---��������һ��Annotation���壬ָ����Annotation���Ա����೤ʱ�䣬������һ��RetentionPolicy���͵ĳ�Ա����
 * 					RetentionPolicy.SOURCE---������ֱ�Ӷ������ֲ��Ե�ע�ͣ�
 * 					RetentionPolicy.CLASS---����������ע�ͼ�¼��class�ļ��У�����Ĭ��ֵ
 * 					RetentionPolicy.RUNTIME---����������ע�ͼ�¼��class�ļ��У������г���ʱ��JVM����ע�ͣ��������ͨ�������ȡ��ע�͡�
 * 		2��Target---��������Annotation���壬ָ����Annotation������������Щ����Ԫ�ء�
 * 		3��Documented---����ָ����ԪAnnotation���ε��ཫ��javadoc�ṩ��ȡ���ļ�
 * 					����ΪDocumented��ע���������RetentionֵΪRUNTIME
 * 		4��Inherited---�������ε�Annotation�����м̳��ԣ����ĳ����ʹ���˱�@Inherited���ε�Annotation�������ཫ�Զ����и�ע�⡣
 */
public class TestAnnotation {
	
	public static void main(String[] args) {
		
		Person p = new Student();
		p.walk();
		p.eat();
		
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		
		System.out.println(list);
	}
	
}

@MyAnnotation(value="startcaft")
class Student extends Person{
	
	@Override
	public void walk() {
		System.out.println("ѧ����·");
	}
	
	@Override
	public void eat() {
		System.out.println("ѧ���Է�");
	}
}

@Deprecated
class Person{
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
	
	public Person() {
		super();
	}
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public void walk(){
		System.out.println("��·");
	}
	@Deprecated
	public void eat(){
		System.out.println("�Է�");
	}
}
