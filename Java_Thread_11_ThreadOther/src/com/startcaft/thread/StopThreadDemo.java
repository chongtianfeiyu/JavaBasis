package com.startcaft.thread;


/*
 * 停止线程：
 * ----1，定义线程的循环结束标记
 * ----2，使用Interrupt(中断) 方法
 * ----3，注意：Thread类中的stop之类的方法已经过时，不要再使用。
 * 
 * 如何停止线程：
 * ----只有一种方法，run方法结束，就代表线程停止。
 * ----开启多线程运行，运行代码通常是循环结构，只要控制住循环，就可以让run方法结束，也就是结束线程。 
 * 
 * 特殊情况：
 * ----当线程处于冻结状态，就不会读取标记，线程就不会结束。
 * 
 * 当没有指定的方法让冻结的线程恢复到运行状态时，这时就需要对线程的冻结状态进行强制清除：
 * ----Thread类中的interrupt()方法的作用：将冻结状态的线程强制恢复到运行状态【就是为了获取运行资格，就可以获取标记来结束线程】，它会产生一个IntertuptException
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

	private boolean flag = true;//标记
	
	//改变标记
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
