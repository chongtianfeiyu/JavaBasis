package com.startcaft.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Java JDK1.5���̵߳�ͬ��������һ��������
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
	private boolean flag = false;//��ʶ
	
	//ʹ��java.util.concurrent.locks.Lock�ӿ������� �߳���,��ʵ���ǰ���������ʾ����
	private Lock lock = new ReentrantLock();
	
	//ʹ��java.util.concurrent.locks.Condition�ӿ������ Object�� wait(),notify(),notifyAll()������
	//һ��Lock�Ͽ����ж����ص�Condition
	private Condition conditionOne = lock.newCondition();
	private Condition conditionTwo = lock.newCondition();
	

	public void set(String name){
		
		lock.lock();//��ʾ����
		try {
			while(flag) {
				conditionOne.await();
			}
			this.name = name + "---" + count++;
			System.out.println(Thread.currentThread().getName() + "...������.." + this.name);
			this.flag = true;
			
			//condition.signalAll();//���ڻ�������
			conditionTwo.signal();//����������
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//unlock()�������൱���ͷ���Դ������һ��Ҫִ��
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
			System.out.println(Thread.currentThread().getName() + "...������.." + this.name);
			this.flag = false;
			
			//condition.signalAll();//���ڻ�������
			conditionOne.signal();//����������
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//unlock()�������൱���ͷ���Դ������һ��Ҫִ��
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
			resource.set("+��Ʒ+");
		}
	}
}