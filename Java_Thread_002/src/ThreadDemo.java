


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
 * ÿ���̶߳��������Լ������ơ�
 * ����ڹ����̵߳�ʱ��û�г�ʼ���̵߳����ƣ�������Ĭ�ϵ����ơ�
 * Ĭ�����Ƹ�ʽ��Thread-���
 * ���̵߳�Ĭ��������:main
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
 * Thread���ṩһ����̬����currentThread()��ȡ��ǰ�̶߳���
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