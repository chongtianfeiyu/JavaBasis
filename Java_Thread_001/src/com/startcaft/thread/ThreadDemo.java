package com.startcaft.thread;


/**
 * 进程：是一个正在执行的程序。
 * ------每一个进程都有一个执行顺序，该顺序就是一个执行路径，或者叫做一个控制单元。
 * 
 * 
 * 线程：就是进程中一个独立的控制单元
 * ------线程在控制着进程的执行。
 * 
 * 
 * 一个进程中至少有一个线程(控制单元)
 * 
 * Java vm 启动的时候会有一个进程java.exe
 * 该进程中至少有一个线程负责java程序的执行，而且这个线程运行的代码存在于main方法中。
 * 该线程称之为【主线程】
 * 
 * 
 * 扩展：更细节说明jvm，jvm启动不止一个线程，还有负责垃圾回收机制的线程。
 *
 */
public class ThreadDemo {

	public static void main(String[] args) {//main函数，主线程，jvm开启的。
		
		/**
		 * 创建线程的第一种方法：继承Thread类，并重写run方法。
		 * 调用线程的start方法，该方法有两个作用：1，启动线程。2，调用run方法。
		 * 
		 * 为什么要有run方法？
		 * ------Thread类是用来描述线程的，线程要执行的代码就需要保存到某处，这个run方法就是用来保存线程要执行的代码的。
		 * ------主线程的代码保存在main函数中(jvm规定的)。
		 * 
		 * 注意run方法和start方法的区别：
		 * start方法：开启线程并执行该线程的run方法。
		 * run方法：仅仅是对象调用方法，线程虽创建了但并没有开启和执行。结果还是一个单线程的程序。
		 */
		MyThread myThread = new MyThread();//建立一个继承了Thread类的对象==创建一个线程。
		//myThread.start();//开启线程并执行该线程的run方法
		myThread.run();//仅仅是对象调用方法，而线程创建了并没有运行。
		
		for (int i = 0; i < 60; i++) {
			System.out.println("Hello World!--" + i);
		}
	}

}
