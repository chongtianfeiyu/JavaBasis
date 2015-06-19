package com.startcaft.thread;


/**
 * 同步函数用的锁是this
 * 
 * 如果同时使用同步函数与同步代码块，那么需要将同步代码块的锁也该为this才有可能线程安全。
 * 
 * 因为线程同步的两大前提：
 * 1，两个或以上的线程
 * 2，这些线程使用同一个锁
 * 
 * @author wow
 *
 */
public class ThreadDemo {

	public static void main(String[] args) throws InterruptedException {
		
		Ticket ticket = new Ticket();
		
		Thread t1 = new Thread(ticket);
		Thread t2 = new Thread(ticket);
		
		t1.start();
		
		Thread.sleep(200);
		ticket.flag = false;
		
		t2.start();
	}
}



class Ticket implements Runnable{
	
	private int total = 100;
	//private Object object = new Object();
	boolean flag = true;
	
	@Override
	public void run() {
		
		//执行同步代码块，它的锁是object,锁改成this就线程安全了
		if (flag) {
			while(true){
				synchronized (this) {
					if(total > 0){
						try{Thread.sleep(200);}catch(Exception e){}
						System.out.println(Thread.currentThread().getName() + "...synchronized Block..." + total--);
					}
				}
			}
		}
		//执行同步函数，它的锁是this，当前对象的引用。
		else {
			sell();
		}
	}
	
	private synchronized void sell(){
		
		while(true){
			if(total > 0){
				try{Thread.sleep(200);}catch(Exception e){}
				System.out.println(Thread.currentThread().getName() + "...synchronized Function..." + total--);
			}
		}
	}
}