package com.startcaft.thread;

/**
 * 
 * �̵߳�������
 * ���������ڣ�ͬ����Ƕ��ͬ������������֮�以�಻��Ȩ�����м����к�г�Ļ��᡿
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
	
	private synchronized void sell(){//this ��
		
		//��Ƕ����һ��ͬ�� �� object
		synchronized (object) {
			if(total > 0){
				try{Thread.sleep(200);}catch(Exception e){}
				System.out.println(Thread.currentThread().getName() + "...synchronized Function..." + total--);
			}
		}
	}
}