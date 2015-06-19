package com.startcaft.main;


public class ThreadDemo {

	public static void main(String[] args) {
		
		System.out.println("��ǰ�̣߳�" + Thread.currentThread().getName());
		
		TestThread tt = new TestThread();
		
		Thread t1 = new Thread(tt);
		Thread t2 = new Thread(tt);
		
		t1.start();
		t2.start();
	}

}


class TestThread implements Runnable{

	//count���̹߳�������
	private int count = 100;
	
	/**
	 * �ֱ���T1,T2��ʾ�����¿������̡߳�
	 * 
	 * �������⣺count=1ʱ������if������T1����if�����ִ�������䣻
	 * ͻȻ��ʱCPUʱ��Ƭ�պ����л���ִ��T2�̣߳�T2�ж�count=1,Ҳ������������������飬ִ��������;
	 * ��ʱCPUʱ��Ƭ���лص�T1�̣߳�T1��ӡcount����1������count--(count=0);
	 * ��ʱCPUʱ��Ƭ���л���T2�̣߳�T2��ӡcount����0������count--(count=-1);
	 * 
	 * ��ʱ��ͳ��������ˣ�T2����count=1(count>0)�������½�������ģ�ȷʵ��count=0
	 * ��������ִ���������䣬��ȻΥ���˳�����Ƶ���Ը��
	 * 
	 * Ϊʲô�������������أ�
	 * ------1)��������̹߳������ݡ���ΪT1,T2�̹߳���������count��
	 * 			�������̲߳��漰���ݹ�������ִ�и��ԵĴ��룬�Ͳ������������⡣
	 * 			���̲߳���ȫ�ĵ���ģʽ�У�����Ƶ�������⡣
	 * 
	 * ------2)�����߳���������ƶԹ������ݵĲ���(����Ĳ�������1�ж�count>0;2ִ��count--)��
	 * 			һ���߳��ڲ����������ݵ�ʱ���������߳�Ҳ�����˹������ݡ�
	 * 
	 * �ܵ���˵������߳���ִ��ͬһ�δ����ʱ��ÿ�ε�ִ�н���͵��߳�ִ�еĽ������һ���ģ�
	 * ������ִ�н���Ķ����ԣ��Ϳ��Գ������̰߳�ȫ�ġ�
	 * �̰߳�ȫ���������ȫ�ֱ����;�̬��������ģ�������̶߳Թ�������ִ�ж���������ִ��д����ʱ��һ�㶼���̰߳�ȫ��;
	 * ������̶߳�ִ��д����ʱ����Ҫ�����߳�ͬ��������̰߳�ȫ���⡣
	 */
	@Override
	public void run() {
		while(true){
			if (count > 0) {
				try {
					Thread.sleep(500);//Ϊ��ģ����������ķ������ڴ�ӡ���ִ��ǰ�����߳�˯��0.5�롣
					System.out.println(Thread.currentThread().getName() + " run... " + count--);
				} catch (InterruptedException e) {
				}
				
			}
		}
	}
}
