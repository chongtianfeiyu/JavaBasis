package com.startcaft.thread;

/*
 * �ڶ���߳�֮�䣬Ĭ������¶��ǽ���ִ�еġ�
 * Thread���join()���������ǵ�ǰ�߳�����ִ��Ȩ.
 * 
 * ��A�߳� ִ�е��� B�ֳɵ� join()����ʱ ��A�߳̾ͻ�ȴ�����B�߳�ִ���꣬A�̲߳Ż�ִ�С�
 * 
 * join����������ʱ�����߳�ִ�С�
 * 
 * 
 * �߳����ȼ����߳��飺
 * ----�߳�Ĭ�ϼ���Ϊ5����1-10��
 * ----Thread.MIN_PRIORITYΪ1
 * ----Thread.NORM_PRIORITYΪ5
 * ----Thread.MAX_PRIORITYΪ10
 * --------�̵߳����ȼ�ֻ�ǻ���΢����߳����ᵽִ��Ȩ�ļ��ʡ� 
 * 
 * ----�߳��飺���ĸ��߳��п��ٵ��̣߳���Щ�߳̾������ĸ���
 * 
 * Thread.yield()������
 * ----��ͣ��ǰ����ִ�е��̶߳��󣬲�ִ�������߳�
 * ----��Ŀ�ľ���Ϊ�˷�ֹĳһ���̡߳���ռִ��Ȩ��,���һ���̯CPU��Դ
 */
public class JoinThreadDemo {

	public static void main(String[] args) throws InterruptedException {
		
		JoinThread joinThread = new JoinThread();
		
		Thread t1 = new Thread(joinThread);
		Thread t2 = new Thread(joinThread);
		
		t1.start();
		
		//t1.join();
		
		t2.start();
		
		for (int i = 0; i < 80; i++) {
			
			System.out.println("main......" + i);
		}
	}
}

class JoinThread implements Runnable{

	@Override
	public void run() {
		
		for (int i = 0; i < 70; i++) {
			
			System.out.println(Thread.currentThread().getName() + "......" + i);
			Thread.yield();
		}
	}
}
