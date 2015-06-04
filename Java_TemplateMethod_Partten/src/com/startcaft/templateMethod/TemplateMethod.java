package com.startcaft.templateMethod;


/**
 * 
 * 什么是模板方法设计模式：
 * 在定义一个类的功能时，功能的一部分是确定的，但是有一部分无法确定，而确定的部分在使用不确定的部分，
 * 那么这个时候，就需要把不确定的部分暴露出去，由该类的子类去完成。
 * 
 * 1，模板方法的出现提高了扩展性
 * 2，模板方法的出现提供了复用性
 * 
 * @author wow
 *
 */
abstract class GetTime{
	
	public final void getTime(){//不让子类复写该方法
		
		long startTime = System.currentTimeMillis();
		
		runCode();//不确定部分的代码，抽象出来，对外暴露
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("执行时间:" + (endTime - startTime) + "毫秒");
	}
	
	public abstract void runCode();
}


class Subtime extends GetTime{
	
	//重写父类的不确定的部分，
	@Override
	public void runCode() {
		
		for (int i = 0; i < 4000; i++) {
			System.out.println(i);
		}
	}
}


public class TemplateMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Subtime gt = new Subtime();
		gt.getTime();
	}

}
