package com.startcaft.templateMethod;


/**
 * 
 * ʲô��ģ�巽�����ģʽ��
 * �ڶ���һ����Ĺ���ʱ�����ܵ�һ������ȷ���ģ�������һ�����޷�ȷ������ȷ���Ĳ�����ʹ�ò�ȷ���Ĳ��֣�
 * ��ô���ʱ�򣬾���Ҫ�Ѳ�ȷ���Ĳ��ֱ�¶��ȥ���ɸ��������ȥ��ɡ�
 * 
 * 1��ģ�巽���ĳ����������չ��
 * 2��ģ�巽���ĳ����ṩ�˸�����
 * 
 * @author wow
 *
 */
abstract class GetTime{
	
	public final void getTime(){//�������ิд�÷���
		
		long startTime = System.currentTimeMillis();
		
		runCode();//��ȷ�����ֵĴ��룬������������Ⱪ¶
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("ִ��ʱ��:" + (endTime - startTime) + "����");
	}
	
	public abstract void runCode();
}


class Subtime extends GetTime{
	
	//��д����Ĳ�ȷ���Ĳ��֣�
	@Override
	public void runCode() {
		
		for (int i = 0; i < 4000; i++) {
			System.out.println(i);
		}
	}
}


public class TemplateMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Subtime gt = new Subtime();
		gt.getTime();
	}

}
