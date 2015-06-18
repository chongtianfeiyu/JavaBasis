
public class SellTickets {

	public static void main(String[] args) {
		
		/*
		Sell s1 = new Sell("window1");
		//Sell s2 = new Sell("window2");
		//Sell s3 = new Sell("window3");
		//Sell s4 = new Sell("window4");
		
		//������׳�java.lang.IllegalThreadStateException�쳣���߳�״̬�쳣��
		s1.start();
		s1.start();
		s1.start();
		s1.start();
		*/
		
		Tick tick = new Tick();//һ��Runnable�ӿڵ��������
		
		Thread t1 = new Thread(tick);//�����ĸ��̶߳���
		Thread t2 = new Thread(tick);
		Thread t3 = new Thread(tick);
		Thread t4 = new Thread(tick);
		
		
		t1.start();//�ĸ��̶߳�����Runnable�ӿڵ���������е�run�����еĴ��롣
		t2.start();
		t3.start();
		t4.start();
	}
}


/**
 * �����̵߳ĵڶ��ַ�ʽ��ʵ��Runnable�ӿ�
 * 
 * ���裺
 * 1��������ʵ��Runnable�ӿ�
 * 2������Runnable�ӿ��е�run����
 * 		|---��Ҫ���еĴ�������run������
 * 
 * 3��ͨ��Thread�ཨ���̶߳���
 * 4����Runnable�ӿڵ����������Ϊʵ�ʲ������ݸ�Thread��Ĺ��캯��
 * 		|---ΪʲôҪ��Runnable�ӿڵ�������󴫵ݸ�Thread��Ĺ��캯����
 * 		|---��Ϊ���Զ����run���������Ķ�����Runnable�ӿڵ��������
 * 		|---����Ҫ���߳�ȥָ����Ϸ�Ǹ���run�������ͱ�����ȷ��run���������Ķ���
 * 
 * 5������Thread���start����(�����̲߳����Զ�����Runnable�ӿ������run����)
 * 
 * 
 * ʵ��Runnable�ӿںͼ̳�Thread����ʲô����
 * 		|---Runnable�ӿڵĳ��ֱ����൥�̳�Thread��ľ����ԡ���ʵThread��Ҳ��ʵ����Runnable�ӿڡ�
 * 		|---�ڶ����߳�ʱ��ǿ�ҽ���ʹ��ʵ��Runnable�ӿڵķ���
 * 		|---�̳�Thread���̴߳������������run������
 * 		|---ʵ��Runnable���̴߳������ڽӿڵ������run������
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
 * ģ��һ����վ������Ʊ������
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

