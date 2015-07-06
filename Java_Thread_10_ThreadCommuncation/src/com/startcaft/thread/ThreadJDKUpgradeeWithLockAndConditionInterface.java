package com.startcaft.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Java JDK1.5对线程的同步进行了一次升级。
 * 
 */
public class ThreadJDKUpgradeeWithLockAndConditionInterface {

	public static void main(String[] args) {
		
		ResourceTwo resource = new ResourceTwo();
		
		Thread t1 = new Thread(new ThreadOne(resource));
		Thread t2 = new Thread(new ThreadOne(resource));
		Thread t3 = new Thread(new ThreadTwo(resource));
		Thread t4 = new Thread(new ThreadTwo(resource));
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}

class ResourceTwo{
	
	private String name;
	private int count = 1;
	private boolean flag = false;//标识
	
	//使用java.util.concurrent.locks.Lock接口来代替 线程锁,其实就是把锁进行显示处理
	private Lock lock = new ReentrantLock();
	
	//使用java.util.concurrent.locks.Condition接口来替代 Object的 wait(),notify(),notifyAll()方法。
	//一个Lock上可以有多个相关的Condition
	private Condition conditionOne = lock.newCondition();
	private Condition conditionTwo = lock.newCondition();
	

	public void set(String name){
		
		lock.lock();//显示加锁
		try {
			while(flag) {
				conditionOne.await();
			}
			this.name = name + "---" + count++;
			System.out.println(Thread.currentThread().getName() + "...生产者.." + this.name);
			this.flag = true;
			
			//condition.signalAll();//还在唤醒所有
			conditionTwo.signal();//唤醒消费者
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//unlock()方法，相当于释放资源，所以一定要执行
		finally{
			lock.unlock();
		}
	}
	
	public void get(){
		
		lock.lock();
		
		try {
			while(!flag) {
				conditionTwo.await();
			}
			System.out.println(Thread.currentThread().getName() + "...消费者.." + this.name);
			this.flag = false;
			
			//condition.signalAll();//还在唤醒所有
			conditionOne.signal();//唤醒生产者
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//unlock()方法，相当于释放资源，所以一定要执行
		finally{
			lock.unlock();
		}
	}
}

class ThreadOne implements Runnable{

	private  ResourceTwo resource;
	
	ThreadOne(ResourceTwo resource){
		this.resource = resource;
	}
	
	@Override
	public void run() {
		
		while(true){
			resource.get();
		}
	}
	
}
class ThreadTwo implements Runnable{

	private  ResourceTwo resource;
	
	ThreadTwo(ResourceTwo resource){
		this.resource = resource;
	}
	
	@Override
	public void run() {
		
		while(true){
			resource.set("+商品+");
		}
	}
}