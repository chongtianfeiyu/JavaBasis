package com.startcaft.main;


public class ThreadDemo {

	public static void main(String[] args) {
		
		System.out.println("当前线程：" + Thread.currentThread().getName());
		
		TestThread tt = new TestThread();
		
		Thread t1 = new Thread(tt);
		Thread t2 = new Thread(tt);
		
		t1.start();
		t2.start();
	}

}


class TestThread implements Runnable{

	//count是线程共享数据
	private int count = 100;
	
	/**
	 * 分别用T1,T2表示两个新开启的线程。
	 * 
	 * 分析问题：count=1时候，满足if条件，T1进入if代码块执行输出语句；
	 * 突然这时CPU时间片刚好又切换到执行T2线程，T2判断count=1,也满足进入条件进入代码块，执行输出语句;
	 * 这时CPU时间片又切回到T1线程，T1打印count等于1，并将count--(count=0);
	 * 这时CPU时间片又切换到T2线程，T2打印count等于0，并将count--(count=-1);
	 * 
	 * 这时候就出现问题了：T2是在count=1(count>0)的条件下进入代码块的，确实在count=0
	 * 的条件下执行了输出语句，显然违背了程序设计的意愿。
	 * 
	 * 为什么会出现这个问题呢？
	 * ------1)，【多个线程共享数据】因为T1,T2线程共享了数据count。
	 * 			如果多个线程不涉及数据共享，各自执行各自的代码，就不会出现这个问题。
	 * 			在线程不安全的单例模式中，就设计到这个问题。
	 * 
	 * ------2)，在线程任务中设计对共享数据的操作(这里的操作包括1判断count>0;2执行count--)，
	 * 			一个线程在操作共享数据的时候，其他的线程也操作了共享数据。
	 * 
	 * 总的来说，多个线程在执行同一段代码的时候，每次的执行结果和单线程执行的结果都是一样的，
	 * 不存在执行结果的二义性，就可以称作是线程安全的。
	 * 线程安全问题多是由全局变量和静态变量引起的，当多个线程对共享数据执行读操作，不执行写操作时候，一般都是线程安全的;
	 * 当多个线程都执行写操作时，需要考虑线程同步来解决线程安全问题。
	 */
	@Override
	public void run() {
		while(true){
			if (count > 0) {
				try {
					Thread.sleep(500);//为了模拟上述问题的发生，在打印语句执行前，让线程睡眠0.5秒。
					System.out.println(Thread.currentThread().getName() + " run... " + count--);
				} catch (InterruptedException e) {
				}
				
			}
		}
	}
}
