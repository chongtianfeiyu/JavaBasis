/**
 * Java��ʹ���̣߳������ַ�ʽ��
 * 
 * 1��ʵ��java.lang.Runnable�ӿ�
 * 
 * 2,��չjava.lang.Thread��------���̵߳�����
 * 
 * @author wow
 *
 */
public class TestRunnable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DoSometing ds1 = new DoSometing("����");
		DoSometing ds2 = new DoSometing("����");
		
		Thread t1 = new Thread(ds1);
		Thread t2 = new Thread(ds2);
		
		t1.start();
		t2.start();
	}

}

/**
 * ����һ���࣬�̳���Runnable��ʵ����void run()����
 * 
 * ���run()���������߳�Ҫִ�еĴ��롣
 * 
 * �̵߳�����ִ�У�����Ҫ����Thread�����start()������
 * 
 * Thread����Ƕ��̵߳�����
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
