package com.startcaft.main;

/**
 * 如何解决线程安全的问题呢？ ------)解决的思路：将线程需要操作的共享数据的代码作为一个整体，
 * 同一时间只允许一个线程执行，执行过程中其他线程不能参与执行。 线程同步就是用来实现这样的机制。
 * 
 * ------)Java中提供了synchronized关键字，将可能引发线程安全问题的代码
 * 包括在synchronized代码块中，表示这些代码需要进行线程同步。 synchronized代码块的语法格式为: synchronized
 * (expression){ //需要同步的代码 }
 * 
 * ------)其中，expression必须是一个引用类型的变量，这里我们可以理解为任意的一个Java对象， 否则会编译出错。
 * 
 * @author wow
 *
 */
public class ThreadDemo {

	public static void main(String[] args) {
		
		System.out.println("当前线程:" + Thread.currentThread().getName());
		
		/*synchronized使用同一个锁(为同一个监听器上锁)
		TestThread tt = new TestThread();

		Thread t1 = new Thread(tt);
		Thread t2 = new Thread(tt);
		Thread t3 = new Thread(tt);
		Thread t4 = new Thread(tt);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		*/
		

		TestThreadTwo tt = new TestThreadTwo();

		Thread t1 = new Thread(tt);
		Thread t2 = new Thread(tt);
		Thread t3 = new Thread(tt);
		Thread t4 = new Thread(tt);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}

class TestThread implements Runnable {

	private int count = 100;

	private Object obj = new Object();//任意Java对象作为监听器，线程为其上锁，解锁和持有。

	/**
	 * 线程同步的机制和同步锁： ---1)，Java中任意的对象都可以作为一个【监听器(monitor)】，【监听器可以被上锁和解锁】，
	 * 【在线程同步中称为同步锁】，且【同步锁在同一时间只能被一个线程所持有】。
	 * 
	 * ---2)，一个线程执行到synchronized代码块，首先检查obj，如果obj为空，则抛出NullPointerExpression异常;
	 * ---3)，如果obj不为空，线程尝试给【监听器上锁】，如果监听器已经被锁，则线程不能获取到锁，该线程就被阻塞;
	 * ---4)，如果监听器没被锁，则线程将监听器上锁，并且持有该所，然后执行代码块;
	 * ---5)，代码块正常执行结束或者非正常结束，监听器都将自动解锁;
	 * 
	 * 
	 * 一个线程持有了同步锁，其他线程就不能获取到锁，也不能进入代码块执行，只能等待锁被释放。
	 * 那么在synchronized代码块中如果每次传入的都是一个新的对象，能否实现同步的效果呢？
	 * 线程同步的前提：
	 * ---1),必须要有两个或者两个以上的线程 
	 * ---2),必须是多个线程使用同一个锁【synchronized(object)中的对象必须是同一个】
	 * 
	 * 
	 * 好处：解决了多线程的安全问题 
	 * 弊端：多个线程需要判断锁，较为消耗资源 
	 * 
	 */
	@Override
	public void run() {

		while (true) {

			synchronized (obj) {

				if (count > 0) {
					try {
						Thread.sleep(500);
						System.out.println(Thread.currentThread().getName()
								+ " Run... " + count--);
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}
}


class TestThreadTwo implements Runnable {

	private int count = 100;
	
	@Override
	public void run() {

		while (true) {
			
			synchronized (new Object()) {//每次传递一个新的监听器(为其上锁)，无法运行
				if (count > 0) {
					try {
						Thread.sleep(500);
						// System.out.println("当前线程：" +
						// Thread.currentThread().getName() + "---" count--);
					} catch (InterruptedException e) {}
				}
			}
		}
	}
}
