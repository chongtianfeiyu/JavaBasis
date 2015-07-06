package com.startcaft.thread;

/*
 * �ػ��̣߳�
 * ----�̷֣߳�ǰ̨�̡߳����߳̾���ǰ̨�̡߳�����̨�߳�
 * ----���һ���̱߳�����Ϊ��̨�̣߳���������ǰ̨�̵߳Ľ�����������
 * ----Thread���setDaemon(boolean flag)��������������һ���߳���ǰ̨���Ǻ�̨�̣߳��÷���������start()����֮ǰ���ò���Ч��
 * 
 */
public class BackgroundThreadDemo {

	public static void main(String[] args) {

		BackThread st = new BackThread();

		Thread t1 = new Thread(st);
		Thread t2 = new Thread(st);

		//�����ػ��߳�
		t1.setDaemon(true);
		t2.setDaemon(true);
		
		t1.start();
		t2.start();

		int num = 0;

		while (true) {

			if (num++ == 60) {
				break;
			}
			System.out.println(Thread.currentThread().getName() + "......"
					+ num);
		}
		System.out.println("over");
	}
}

class BackThread implements Runnable {

	private boolean flag = true;// ���

	// �ı���
	public void changeFlag() {
		this.flag = false;
	}

	@Override
	public synchronized void run() {

		while (flag) {
			try {
				wait();
			} catch (InterruptedException e) {

				flag = false;
			}
			System.out.println(Thread.currentThread().getName() + "...run");
		}
	}
}