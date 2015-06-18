


public class ThreadDemo {

	public static void main(String[] args) {
		
		/*
		Test t1 = new Test("one");
		Test t2 = new Test("two");
		
		t1.start();
		t2.start();
		
		for (int i = 0; i < 60; i++) {
			System.out.println("main run...");
		}
		*/
		
		TestThread t1 = new TestThread("one");
		TestThread t2 = new TestThread("two");
		
		t1.start();
		t2.start();
		
		for (int i = 0; i < 60; i++) {
			System.out.println(Thread.currentThread().getName() + " run......");
		}
	}
}


/**
 * 每个线程都有属于自己的名称。
 * 如果在构造线程的时候没有初始化线程的名称，它会有默认的名称。
 * 默认名称格式：Thread-编号
 * 主线程的默认名称是:main
 * 
 * @author wow
 *
 */
class Test extends Thread{
	
	private String name;
	public Test(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 60; i++) {
			System.out.println(this.getName() + " run......");
		}
	}
}

/**
 * Thread类提供一个静态方法currentThread()获取当前线程对象。
 * 
 * @author wow
 *
 */
class TestThread extends Thread{

	public TestThread(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 60; i++) {
			System.out.println(Thread.currentThread().getName() + " run......");
		}
	}
}