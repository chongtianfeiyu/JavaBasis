
/**��
 * 1���ڶ�̬�У��Ǿ�̬��Ա�������ص㣺
 * �ڱ����ڼ䣺���������ͱ��������������Ƿ��е��õķ���������У������ͨ�����û�������ʧ�ܡ�
 * �������ڼ䣺���Ķ��������������Ƿ���б����õķ�����
 * ���ܽ�ɣ���Ա�����ڶ�̬����ʱ�����뿴��ߣ����п��ұߡ�
 * 
 * 2���ڶ�̬�У���Ա�������ص㣺
 * �����ڱ��뻹�����У����������(�����ͱ�����������)��
 * 
 * 3,�ڶ�̬�У���̬��Ա���ص㣺
 * �����ڱ��뻹�����У����������(�����ͱ�����������)��
 * 
 * @author wow
 *
 */
public class DuoTaiDemo {

	public static void main(String[] args) {
		
		Fu f = new Zi();//������Ƕ�̬�ˣ�����ʵ��������ָ��������Ķ���
		//f.method1();//zi_method_1
		//f.method2();//fu_method_2
		//f.method3();��һ�仰���ڱ���������ͨ��������ʱ��ᱨ��
		
		/*
		System.out.println(f.num);//5
		Zi zi = new Zi();
		System.out.println(zi.num);//8
		*/
		
		
		f.method4();
		
		
	}
	
}
class Fu{
	
	int num = 5;
	void method1(){
		System.out.println("fu_method_1");
	}
	void method2(){
		System.out.println("fu_method_2");
	}
	static void method4(){
		System.out.println("fu_method_4");
	}
}

class Zi extends Fu{//method1()��д�ĸ���ģ�method2�̳��Ը���ģ�method3���������еġ�
	
	int num = 8;
	@Override
	void method1() {//����������д�ĸ����method1()����
		System.out.println("zi_method_1");
	}
	void method3(){
		System.out.println("zi_method_3");
	}
	static void method4(){
		System.out.println("zi_method_4");
	}
}