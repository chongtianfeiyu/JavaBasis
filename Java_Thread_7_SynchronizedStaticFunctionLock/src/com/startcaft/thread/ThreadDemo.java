package com.startcaft.thread;

/**
 * ���ͬ�����������γɾ�̬�أ���ô����������ʲô��
 * ���Բ���this����Ϊ��̬�����в�����ʹ��this
 * 
 * ��̬��������ʱ���ڴ��л�û�б�����󣬵���һ���и����Ӧ���ֽ����ļ�����
 * ����.class �ö����������Class��
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
	
	private static int total = 100;
	//private Object object = new Object();
	boolean flag = true;
	
	@Override
	public void run() {
		
		//��Ϊ��̬ͬ������ʹ�õ����Ǹ÷�����������ֽ����ļ����� ����.class(�ö������ڴ�����Ψһ�ģ�����һ�ξ�ok��)
		if (flag) {
			while(true){
				synchronized (Ticket.class) {
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
	
	private static synchronized void sell(){
		
		while(true){
			if(total > 0){
				try{Thread.sleep(200);}catch(Exception e){}
				System.out.println(Thread.currentThread().getName() + "...synchronized Function..." + total--);
			}
		}
	}
}