package com.startcaft.exception;

/**
 * 进一步自定义异常，加入特有的成员变量和额外的构造函数
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
	
	//重写父类getMessage()方法
	@Override
	public String getMessage() {
		return "Detail Message:" + x + " " + super.getMessage();
	}
}
