
/**
 * Java�нӿڵ����ԣ�
 * 1���ӿ��еĳ������壺���������󷽷�
 * 2���ӿ��еĳ�Ա���й̶������η�
 * 		������pubilc static final
 * 		������public abstract
 * ��ס���ӿ��еĳ�Ա����public���ε�
 * 
 * 
 * 
 * �ӿڣ��ǲ����Դ�������ģ���Ϊ�г��󷽷���
 * ��Ҫ������ʵ�֣�����Խӿ��еĳ��󷽷�ȫ�����Ǻ�����ſ���ʵ�������������໹��һ�������ࡣ
 * 
 * 
 * �ӿڿ��Ա����ʵ�֣�Ҳ����һ�������ʵ�ֶ���ӿ�(Java��̳еĻ����������������,ֻ����̳�һ�����࣬����ʵ�ֶ���ӿ�)
 * 
 * @author wow
 *
 */
interface Inter{
	
	public static final int NUM = 3;
	
	public void show();
}

class Child implements Inter{

	@Override
	public void show() {
		// TODO Auto-generated method stub
		System.out.println("Child.show()");
	}
	
}

public class InterfaceDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Inter inter = new Child();
		System.out.println(inter.NUM);
		System.out.println(Child.NUM);
		inter.show();
	}

}
