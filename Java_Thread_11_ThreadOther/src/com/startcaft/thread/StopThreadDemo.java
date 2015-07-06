package com.startcaft.thread;


/*
 * ֹͣ�̣߳�
 * ----1�������̵߳�ѭ���������
 * ----2��ʹ��Interrupt(�ж�) ����
 * ----3��ע�⣺Thread���е�stop֮��ķ����Ѿ���ʱ����Ҫ��ʹ�á�
 * 
 * ���ֹͣ�̣߳�
 * ----ֻ��һ�ַ�����run�����������ʹ����߳�ֹͣ��
 * ----�������߳����У����д���ͨ����ѭ���ṹ��ֻҪ����סѭ�����Ϳ�����run����������Ҳ���ǽ����̡߳� 
 * 
 * ���������
 * ----���̴߳��ڶ���״̬���Ͳ����ȡ��ǣ��߳̾Ͳ��������
 * 
 * ��û��ָ���ķ����ö�����ָ̻߳�������״̬ʱ����ʱ����Ҫ���̵߳Ķ���״̬����ǿ�������
 * ----Thread���е�interrupt()���������ã�������״̬���߳�ǿ�ƻָ�������״̬������Ϊ�˻�ȡ�����ʸ񣬾Ϳ��Ի�ȡ����������̡߳����������һ��IntertuptException
 */
public class StopThreadDemo {

	public static void main(String[] args) {
		
		StopThread st = new StopThread();
		
		Thread t1 = new Thread(st);
		Thread t2 = new Thread(st);
		
		t1.start();
		t2.start();
		
		int num = 0;
		
		while(true){
			
			if (num++ == 60) {
				
				//st.changeFlag();
				t1.interrupt();
				t2.interrupt();
				break;
			}
			System.out.println(Thread.currentThread().getName() + "......" + num);
		}
		System.out.println("over");
	}
}

class StopThread implements Runnable{

	private boolean flag = true;//���
	
	//�ı���
	public void changeFlag(){
		this.flag = false;
	}
	
//	@Override
//	public void run() {
//		
//		while(flag){
//			
//			System.out.println(Thread.currentThread().getName() + "...run");
//		}
//	}
	
	@Override
	public synchronized void run() {
		
		while(flag){
			try {
				wait();
			} catch (InterruptedException e) {
				
				//System.out.println(Thread.currentThread().getName() + "...Exception");
				flag = false;
			}
			System.out.println(Thread.currentThread().getName() + "...run");
		}
	}
}
