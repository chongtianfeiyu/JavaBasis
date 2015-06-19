package com.startcaft.thread;


/**
 * ͬ�������õ�����this
 * 
 * ���ͬʱʹ��ͬ��������ͬ������飬��ô��Ҫ��ͬ����������Ҳ��Ϊthis���п����̰߳�ȫ��
 * 
 * ��Ϊ�߳�ͬ��������ǰ�᣺
 * 1�����������ϵ��߳�
 * 2����Щ�߳�ʹ��ͬһ����
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
		
		//ִ��ͬ������飬��������object,���ĳ�this���̰߳�ȫ��
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
		//ִ��ͬ����������������this����ǰ��������á�
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