
public class SellTickets {

	public static void main(String[] args) {
		
		/*
		Sell s1 = new Sell("window1");
		//Sell s2 = new Sell("window2");
		//Sell s3 = new Sell("window3");
		//Sell s4 = new Sell("window4");
		
		//这里会抛出java.lang.IllegalThreadStateException异常。线程状态异常。
		s1.start();
		s1.start();
		s1.start();
		s1.start();
		*/
		
		Tick tick = new Tick();//一个Runnable接口的子类对象
		
		Thread t1 = new Thread(tick);//创建四个线程对象
		Thread t2 = new Thread(tick);
		Thread t3 = new Thread(tick);
		Thread t4 = new Thread(tick);
		
		
		t1.start();//四个线程对象共享Runnable接口的子类对象中的run方法中的代码。
		t2.start();
		t3.start();
		t4.start();
	}
}


/**
 * 创建线程的第二种方式：实现Runnable接口
 * 
 * 步骤：
 * 1，定义类实现Runnable接口
 * 2，覆盖Runnable接口中的run方法
 * 		|---将要运行的代码存放在run方法中
 * 
 * 3，通过Thread类建立线程对象
 * 4，将Runnable接口的子类对象作为实际参数传递给Thread类的构造函数
 * 		|---为什么要讲Runnable接口的子类对象传递给Thread类的构造函数？
 * 		|---因为，自定义的run方法所属的对象是Runnable接口的子类对象
 * 		|---所以要让线程去指定对戏那个的run方法，就必须明确该run方法所属的对象
 * 
 * 5，调用Thread类的start方法(开启线程并会自动调用Runnable接口子类的run方法)
 * 
 * 
 * 实现Runnable接口和继承Thread类有什么区别？
 * 		|---Runnable接口的出现避免类单继承Thread类的局限性【其实Thread类也是实现了Runnable接口】
 * 		|---在定义线程时，强烈建议使用实现Runnable接口的方法
 * 		|---继承Thread：线程代码存放在子类的run方法中
 * 		|---实现Runnable：线程代码存放在接口的子类的run方法中
 * 
 * @author wow
 *
 */
class Tick implements Runnable{

	private int count = 100;
	
	@Override
	public void run() {
		while(true){
			if (count > 0) {
				System.out.println(Thread.currentThread().getName() + "---sale : " + count--);
			}
		}
	}
	
}


/**
 * 模拟一个火车站窗口卖票的例子
 * @author wow
 */
class Sell extends Thread{
	
	private int ticketCount = 100;
	
	public Sell(String windowName) {
		super(windowName);
	}
	
	@Override
	public void run() {
		while(true){
			if (ticketCount > 0) {
				System.out.println(this.getName() + "---sale : " + ticketCount--);
			}
		}
	}
}

