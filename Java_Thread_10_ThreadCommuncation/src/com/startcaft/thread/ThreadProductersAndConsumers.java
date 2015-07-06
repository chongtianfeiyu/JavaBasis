package com.startcaft.thread;

class Resource{
	
	private String name;
	private int count = 1;
	private boolean flag = false;//标识
	
	//生产者有两个t1,t2
	public synchronized void set(String name){
		
		while(flag) {//这里改成while循环，是为了让被唤醒的每一个线程都重新对标识进行判断
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.name = name + "---" + count++;
		System.out.println(Thread.currentThread().getName() + "...生产者.." + this.name);
		this.flag = true;
		this.notifyAll();//这里要改成唤醒线程池中所有的线程，而不是只唤醒第一个
	}
	
	public synchronized void get(){
		
		while(!flag) {//这里改成while循环，是为了让被唤醒的每一个线程都重新对标识进行判断
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + "...消费者.." + this.name);
		this.flag = false;
		this.notifyAll();//这里要改成唤醒线程池中所有的线程，而不是只唤醒第一个
	}
}

//生产者
class Producer implements Runnable{
	
	private Resource resource;
	
	Producer(Resource resource){
		this.resource = resource;
	}

	@Override
	public void run() {
		
		while(true){
			
			resource.set("+商品+");
		}
	}
}

//消费者
class Consumer implements Runnable{
	
	private Resource resource;
	
	Consumer(Resource resource){
		this.resource = resource;
	}

	@Override
	public void run() {
		
		while(true){
			
			resource.get();
		}
	}
}

/*
 * 生产者和消费者的例子:
 * 当同时有多个生产者消费者同时在一件事情的时候，必须使用while循环，重新判断标记和notifyAll()唤醒所有的。
 */
public class ThreadProductersAndConsumers {

	public static void main(String[] args) {
		
		Resource resource = new Resource();
		
		Thread t1 = new Thread(new Producer(resource));
		Thread t2 = new Thread(new Producer(resource));
		Thread t3 = new Thread(new Consumer(resource));
		Thread t4 = new Thread(new Consumer(resource));
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
