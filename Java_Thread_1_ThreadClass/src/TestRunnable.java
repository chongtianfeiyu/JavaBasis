/**
 * Java中使用线程，有两种方式：
 * 
 * 1，实现java.lang.Runnable接口
 * 
 * 2,扩展java.lang.Thread类------对线程的描述
 * 
 * @author wow
 *
 */
public class TestRunnable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DoSometing ds1 = new DoSometing("张三");
		DoSometing ds2 = new DoSometing("李四");
		
		Thread t1 = new Thread(ds1);
		Thread t2 = new Thread(ds2);
		
		t1.start();
		t2.start();
	}

}

/**
 * 定义一个类，继承自Runnable并实现其void run()方法
 * 
 * 这个run()方法，是线程要执行的代码。
 * 
 * 线程的启动执行，则需要调用Thread对象的start()方法。
 * 
 * Thread类就是对线程的描述
 * 
 * @author wow
 *
 */
class DoSometing implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) { 
            for (long k = 0; k < 100000000; k++) ; 
            System.out.println(name + ": " + i); 
        } 
	}
	
	private String name;
	public DoSometing(String name){
		this.name = name;
	}
}
