package com.startcaft.exception;

/**
 * ��һ���Զ����쳣���������еĳ�Ա�����Ͷ���Ĺ��캯��
 * 
 * @author wow
 *
 */
public class MyCustomException2 extends Exception {

	private int x;
	public MyCustomException2(){}
	public MyCustomException2(String msg){super(msg);}
	public MyCustomException2(String msg,int x){
		super(msg);
		this.x = x;
	}
	
	public int getX(){
		return this.x;
	}
	
	//��д����getMessage()����
	@Override
	public String getMessage() {
		return "Detail Message:" + x + " " + super.getMessage();
	}
}
