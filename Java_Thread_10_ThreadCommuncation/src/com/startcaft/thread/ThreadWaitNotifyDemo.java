package com.startcaft.thread;


class MyResource{
	
	private String name;
	private String sex;
	private boolean flag = false;
	
	public synchronized void set(String name,String sex){
		
		if (flag) {//�����ʶΪ true���������̵߳ȴ�������ִ����ֵ����
			try{this.wait();}catch(InterruptedException e){e.printStackTrace();}
		}
		//���ó�Ա����
		this.name = name;
		this.sex = sex;
		//�������֮�󣬽���ʾ��Ϊtrue��
		this.flag = true;
		this.notify();
	}
	
	public synchronized void get(){
		
		if (!flag) {//�����ʶΪ false���������̵߳ȴ�������ִ�ж�ȡ����
			try{this.wait();}catch(InterruptedException e){e.printStackTrace();}
		}
		//��ȡ����
		System.out.println(this.name+ "......"
				+ this.sex);
		//�ı���Ϊ false���Ա������߳���������
		this.flag = false;
		//����input,������������
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
				r.set("����", "Ů"); 
			}
			//������0/��0֮������л�
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
 * �����̲߳�����Դ�� Ӧ����A�̶߳���Դ����д�룬B�̵߳ȴ��� B�̶߳�ȡ��Դ��A�̵߳ȴ���
 * 
 * ���͵� �ȴ�/���ѻ��ơ�ʹ��Object��̳е�wait()��notify()������
 * 
 * notifyAll()���������̳߳��е����з�����
 * 
 * wait()��notify(),notifyAll()������ͬ���������Ϊ���Ƕ���Ҫ��������Ҳ���Ǳ�����ȷ��ʾ����Щ�����������߳�����������,��
 * ��Ϊͬ�����ܻ���Ƕ�ס�
 * 
 * ��Ҳ��wait()��notify(),notifyAll()����������Objetc���е�ԭ����Ϊ���߳���������Java�е�������󡿡�
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
