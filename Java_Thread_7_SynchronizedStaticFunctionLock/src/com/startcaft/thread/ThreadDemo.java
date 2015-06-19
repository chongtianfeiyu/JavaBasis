package com.startcaft.thread;

/**
 * 如果同步函数被修饰成静态呢，那么它的锁又是什么？
 * 绝对不是this，因为静态方法中不可以使用this
 * 
 * 静态方法运行时：内存中还没有本类对象，但是一定有该类对应的字节码文件对象。
 * 类名.class 该对象的类型是Class。
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
		
		//因为静态同步方法使用的锁是该方法所在类的字节码文件对象 类名.class(该对象在内存中是唯一的，加载一次就ok了)
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
		//执行同步函数，它的锁是this，当前对象的引用。
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