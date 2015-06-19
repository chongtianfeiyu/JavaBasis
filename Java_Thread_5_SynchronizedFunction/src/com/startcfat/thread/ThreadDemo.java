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

//ģ������
class Bank{
	
	private int sum;
	
	//private Object object = new Object();
	
	//ͬ������
	public synchronized void add(int money) throws InterruptedException{
		
		//ͬ�������
		//synchronized (object) {
			sum += money;
			Thread.sleep(200);
			System.out.println("sum=" + sum);
		//}
	}
}

//ģ�������û������д�Ǯ
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
