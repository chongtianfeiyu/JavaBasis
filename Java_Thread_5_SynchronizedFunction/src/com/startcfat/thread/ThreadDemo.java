package com.startcfat.thread;




public class ThreadDemo {

	public static void main(String[] args) {
		
		Customer c = new Customer();
		
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		
		t1.start();
		t2.start();
	}
}

//模拟银行
class Bank{
	
	private int sum;
	
	//private Object object = new Object();
	
	//同步函数
	public synchronized void add(int money) throws InterruptedException{
		
		//同步代码块
		//synchronized (object) {
			sum += money;
			Thread.sleep(200);
			System.out.println("sum=" + sum);
		//}
	}
}

//模拟银行用户向银行存钱
class Customer implements Runnable{
	
	private Bank b = new Bank();
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			try {
				b.add(100);
			} catch (InterruptedException e) {}
		}
	}
	
}
