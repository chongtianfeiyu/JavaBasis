package com.startcaft.thread;

/*
 * 守护线程：
 * ----线程分：前台线程【主线程就是前台线程】，后台线程
 * ----如果一个线程被设置为后台线程，它会随着前台线程的结束而结束。
 * ----Thread类的setDaemon(boolean flag)方法，可以设置一个线程是前台还是后台线程，该方法必须在start()方法之前设置才有效。
 * 
 */
public class BackgroundThreadDemo {

	public static void main(String[] args) {

		BackThread st = new BackThread();

		Thread t1 = new Thread(st);
		Thread t2 = new Thread(st);

		//设置守护线程
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

	private boolean flag = true;// 标记

	// 改变标记
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