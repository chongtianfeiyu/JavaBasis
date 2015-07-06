package com.startcaft.thread;


class MyResource{
	
	private String name;
	private String sex;
	private boolean flag = false;
	
	public synchronized void set(String name,String sex){
		
		if (flag) {//如果标识为 true，则其他线程等待。否则执行设值工作
			try{this.wait();}catch(InterruptedException e){e.printStackTrace();}
		}
		//设置成员变量
		this.name = name;
		this.sex = sex;
		//设置完毕之后，将表示改为true。
		this.flag = true;
		this.notify();
	}
	
	public synchronized void get(){
		
		if (!flag) {//如果标识为 false，则其他线程等待。否则执行读取工作
			try{this.wait();}catch(InterruptedException e){e.printStackTrace();}
		}
		//读取数据
		System.out.println(this.name+ "......"
				+ this.sex);
		//改变标记为 false，以便输入线程输入数据
		this.flag = false;
		//唤醒input,进行数据输入
		this.notify();
	}
}

class Input implements Runnable{

	private MyResource r;
	
	public Input(MyResource r) {
		this.r = r;
	}
	
	@Override
	public void run() {
		
		int count = 0;
		while (true) {
			if (count == 0) {
				r.set("zhagsan", "mele");  
			}
			else {
				r.set("李四", "女"); 
			}
			//在两个0/非0之间进行切换
			count = (count + 1) % 2;
		}
	}
}

class OutPut implements Runnable{

	private MyResource r;
	
	public OutPut(MyResource r) {
		this.r = r;
	}
	
	@Override
	public void run() {
		while (true) {
			r.get();
		}
	}
	
}


/*
 * 两个线程操作资源， 应该是A线程对资源进行写入，B线程等待， B线程读取资源，A线程等待。
 * 
 * 典型的 等待/唤醒机制。使用Object类继承的wait()，notify()方法。
 * 
 * notifyAll()方法唤醒线程池中的所有方法。
 * 
 * wait()，notify(),notifyAll()都用在同步方法里，因为他们都需要监视器【也就是必须明确标示出这些方法操作的线程所属的锁】,【
 * 因为同步可能会有嵌套】
 * 
 * 这也是wait()，notify(),notifyAll()方法定义在Objetc类中的原因，因为【线程锁可以是Java中的任意对象】。
 */
public class ThreadWaitNotifyDemo {

	public static void main(String[] args) {
		
		MyResource resource = new MyResource();
		
		Input input = new Input(resource);
		OutPut outPut = new OutPut(resource);
		
		Thread t1 = new Thread(input);
		Thread t2 = new Thread(outPut);
		
		t1.start();
		t2.start();
	}
}
