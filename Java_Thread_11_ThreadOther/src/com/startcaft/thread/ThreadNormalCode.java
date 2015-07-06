package com.startcaft.thread;

/*
 * 开发者中多线程的写法：
 * 独立运算，相互不相干时
 */
public class ThreadNormalCode {

	public static void main(String[] args) {
		
		new Thread()
		{
			@Override
			public void run() {
				for(int i=0;i<1000;i++){
					System.out.println(Thread.currentThread().getName() + "......" + i);
				}
			}
		}.start();
		
		
		for(int i=0;i<1000;i++){
			System.out.println(Thread.currentThread().getName() + "......" + i);
		}
		
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<1000;i++){
					System.out.println(Thread.currentThread().getName() + "......" + i);
				}
			}
		};
		
		new Thread(r).start();
	}
}
