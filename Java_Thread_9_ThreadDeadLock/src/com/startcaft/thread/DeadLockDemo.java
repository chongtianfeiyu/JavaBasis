package com.startcaft.thread;

/**
 * 
 * 线程的死锁：
 * 死锁发生在：同步中嵌套同步：【两个锁之间互相不放权，会有几率有和谐的机会】
 * 
 */
public class DeadLockDemo {

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
	
	private static int total = 100;
	private Object object = new Object();
	boolean flag = true;
	
	@Override
	public void run() {
		
		if (flag) {
			while(true){
				synchronized (object) {
					sell();
				}
			}
		}
		else {
			while(true){
				sell();
			}
		}
	}
	
	private synchronized void sell(){//this 锁
		
		//又嵌套了一个同步 锁 object
		synchronized (object) {
			if(total > 0){
				try{Thread.sleep(200);}catch(Exception e){}
				System.out.println(Thread.currentThread().getName() + "...synchronized Function..." + total--);
			}
		}
	}
}