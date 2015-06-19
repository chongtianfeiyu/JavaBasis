package com.startcaft.main;

/**
 * ��ν���̰߳�ȫ�������أ� ------)�����˼·�����߳���Ҫ�����Ĺ������ݵĴ�����Ϊһ�����壬
 * ͬһʱ��ֻ����һ���߳�ִ�У�ִ�й����������̲߳��ܲ���ִ�С� �߳�ͬ����������ʵ�������Ļ��ơ�
 * 
 * ------)Java���ṩ��synchronized�ؼ��֣������������̰߳�ȫ����Ĵ���
 * ������synchronized������У���ʾ��Щ������Ҫ�����߳�ͬ���� synchronized�������﷨��ʽΪ: synchronized
 * (expression){ //��Ҫͬ���Ĵ��� }
 * 
 * ------)���У�expression������һ���������͵ı������������ǿ������Ϊ�����һ��Java���� �����������
 * 
 * @author wow
 *
 */
public class ThreadDemo {

	public static void main(String[] args) {
		
		System.out.println("��ǰ�߳�:" + Thread.currentThread().getName());
		
		/*synchronizedʹ��ͬһ����(Ϊͬһ������������)
		TestThread tt = new TestThread();

		Thread t1 = new Thread(tt);
		Thread t2 = new Thread(tt);
		Thread t3 = new Thread(tt);
		Thread t4 = new Thread(tt);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		*/
		

		TestThreadTwo tt = new TestThreadTwo();

		Thread t1 = new Thread(tt);
		Thread t2 = new Thread(tt);
		Thread t3 = new Thread(tt);
		Thread t4 = new Thread(tt);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}

class TestThread implements Runnable {

	private int count = 100;

	private Object obj = new Object();//����Java������Ϊ���������߳�Ϊ�������������ͳ��С�

	/**
	 * �߳�ͬ���Ļ��ƺ�ͬ������ ---1)��Java������Ķ��󶼿�����Ϊһ����������(monitor)���������������Ա������ͽ�������
	 * �����߳�ͬ���г�Ϊͬ���������ҡ�ͬ������ͬһʱ��ֻ�ܱ�һ���߳������С���
	 * 
	 * ---2)��һ���߳�ִ�е�synchronized����飬���ȼ��obj�����objΪ�գ����׳�NullPointerExpression�쳣;
	 * ---3)�����obj��Ϊ�գ��̳߳��Ը���������������������������Ѿ����������̲߳��ܻ�ȡ���������߳̾ͱ�����;
	 * ---4)�����������û���������߳̽����������������ҳ��и�����Ȼ��ִ�д����;
	 * ---5)�����������ִ�н������߷����������������������Զ�����;
	 * 
	 * 
	 * һ���̳߳�����ͬ�����������߳̾Ͳ��ܻ�ȡ������Ҳ���ܽ�������ִ�У�ֻ�ܵȴ������ͷš�
	 * ��ô��synchronized����������ÿ�δ���Ķ���һ���µĶ����ܷ�ʵ��ͬ����Ч���أ�
	 * �߳�ͬ����ǰ�᣺
	 * ---1),����Ҫ�����������������ϵ��߳� 
	 * ---2),�����Ƕ���߳�ʹ��ͬһ������synchronized(object)�еĶ��������ͬһ����
	 * 
	 * 
	 * �ô�������˶��̵߳İ�ȫ���� 
	 * �׶ˣ�����߳���Ҫ�ж�������Ϊ������Դ 
	 * 
	 */
	@Override
	public void run() {

		while (true) {

			synchronized (obj) {

				if (count > 0) {
					try {
						Thread.sleep(500);
						System.out.println(Thread.currentThread().getName()
								+ " Run... " + count--);
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}
}


class TestThreadTwo implements Runnable {

	private int count = 100;
	
	@Override
	public void run() {

		while (true) {
			
			synchronized (new Object()) {//ÿ�δ���һ���µļ�����(Ϊ������)���޷�����
				if (count > 0) {
					try {
						Thread.sleep(500);
						// System.out.println("��ǰ�̣߳�" +
						// Thread.currentThread().getName() + "---" count--);
					} catch (InterruptedException e) {}
				}
			}
		}
	}
}
