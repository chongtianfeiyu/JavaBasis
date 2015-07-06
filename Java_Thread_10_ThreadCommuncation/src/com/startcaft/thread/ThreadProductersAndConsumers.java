package com.startcaft.thread;

class Resource{
	
	private String name;
	private int count = 1;
	private boolean flag = false;//��ʶ
	
	//������������t1,t2
	public synchronized void set(String name){
		
		while(flag) {//����ĳ�whileѭ������Ϊ���ñ����ѵ�ÿһ���̶߳����¶Ա�ʶ�����ж�
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.name = name + "---" + count++;
		System.out.println(Thread.currentThread().getName() + "...������.." + this.name);
		this.flag = true;
		this.notifyAll();//����Ҫ�ĳɻ����̳߳������е��̣߳�������ֻ���ѵ�һ��
	}
	
	public synchronized void get(){
		
		while(!flag) {//����ĳ�whileѭ������Ϊ���ñ����ѵ�ÿһ���̶߳����¶Ա�ʶ�����ж�
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + "...������.." + this.name);
		this.flag = false;
		this.notifyAll();//����Ҫ�ĳɻ����̳߳������е��̣߳�������ֻ���ѵ�һ��
	}
}

//������
class Producer implements Runnable{
	
	private Resource resource;
	
	Producer(Resource resource){
		this.resource = resource;
	}

	@Override
	public void run() {
		
		while(true){
			
			resource.set("+��Ʒ+");
		}
	}
}

//������
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
 * �����ߺ������ߵ�����:
 * ��ͬʱ�ж��������������ͬʱ��һ�������ʱ�򣬱���ʹ��whileѭ���������жϱ�Ǻ�notifyAll()�������еġ�
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
