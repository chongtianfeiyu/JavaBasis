package com.startcaft.thread;


public class MyThread extends Thread {

	@Override
	public void run() {
		
		for (int i = 0; i < 60; i++) {
			System.out.println("MyThread Run---" + i);
		}
	}
}
