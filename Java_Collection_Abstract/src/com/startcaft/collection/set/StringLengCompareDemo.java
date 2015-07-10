package com.startcaft.collection.set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/*
 * TreeSet中保存字符串，但是字符串类实现了Comparable接口，所以就需要自定义比较器。
 */
public class StringLengCompareDemo {

	public static void main(String[] args) {
		
		TreeSet<String> ts = new TreeSet<>(new StringLengthCompare());
		
		ts.add("startcaft");
		ts.add("compare");
		ts.add("java");
		ts.add("configuration");
		
		Iterator<String> it = ts.iterator();
		
		while(it.hasNext()){
			
			String s = it.next();
			System.out.println(s);
		}
	}
}

class StringLengthCompare implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		
		return new Integer(o1.length()).compareTo(new Integer(o2.length()));
	}
	
}