package com.startcaft.thread;

/*
 * 在多个线程之间，默认情况下都是交替执行的。
 * Thread类的join()方法，就是当前线程抢夺执行权.
 * 
 * 当A线程 执行到了 B现成的 join()方法时 ，A线程就会等待，等B线程执行完，A线程才会执行。
 * 
 * join可以用来临时加入线程执行。
 * 
 * 
 * 线程优先级，线程组：
 * ----线程默认级别为5，【1-10】
 * ----Thread.MIN_PRIORITY为1
 * ----Thread.NORM_PRIORITY为5
 * ----Thread.MAX_PRIORITY为10
 * --------线程的优先级只是会稍微提高线程抢夺到执行权的几率。 
 * 
 * ----线程组：在哪个线程中开辟的线程，这些线程就属于哪个组
 * 
 * Thread.yield()方法：
 * ----暂停当前正在执行的线程对象，并执行其他线程
 * ----其目的就是为了防止某一个线程【独占执行权】,大家一起均摊CPU资源
 */
public class JoinThreadDemo {

	public static void main(String[] args) throws InterruptedException {
		
		JoinThread joinThread = new JoinThread();
		
		Thread t1 = new Thread(joinThread);
		Thread t2 = new Thread(joinThread);
		
		t1.start();
		
		//t1.join();
		
		t2.start();
		
		for (int i = 0; i < 80; i++) {
			
			System.out.println("main......" + i);
		}
	}
}

class JoinThread implements Runnable{

	@Override
	public void run() {
		
		for (int i = 0; i < 70; i++) {
			
			System.out.println(Thread.currentThread().getName() + "......" + i);
			Thread.yield();
		}
	}
}
