/**
 * Java���ڲ���ķ��ʹ���
 * 1���ڲ������ֱ�ӷ����ⲿ��ĳ�Ա������˽�еġ�
 * 2���ⲿ����Ҫ�����ڲ��࣬�����Ƚ����ڲ���Ķ���
 * 
 * 
 * ���ʷ�ʽ��
 * 1���ڲ���֮���п��Է����ⲿ��ĳ�Ա������Ϊ�ڲ����г�����һ���ⲿ������ã���ʽ���ⲿ����.this
 * 
 *,2�����ڲ��ඨ�����ⲿ��ĳ�Աλ���ϣ����ҷ�˽�У��������ⲿ����������ֱ�ӽ�������ڲ���Ķ���
 * ��ʽ���ⲿ����.�ڲ����� ������ = new �ڲ�����().new �ⲿ����();
 * 
 * 3�����ڲ����ڳ�Աλ���ϣ��Ϳ��Ա���Ա���η������Ρ�
 * ���磺private �����ڲ������ⲿ���н��з�װ
 * 	   static���ڲ���;����˾�̬������------
 * 
 * ���ڲ��౻static����ʱ��ֻ�ܷ����ⲿ��ľ�̬��Ա�ˣ������˷��ʾ��ޣ�һ�й������static�ؼ��֡�
 * ע�⣺
 * 1�����ڲ����ж����˾�̬��Ա������ڲ�������Ǿ�̬�ġ�
 * 2�����ⲿ���еľ�̬���������ڲ���ʱ���ڲ���Ҳ�����Ǿ�̬�ġ�
 * 
 * @author wow
 *
 */
class Outer{
	
	private int x = 3;
	
	void method(){
		Inner in = new Inner();//������һ���ڲ���Ķ���
		in.function();
	}
	
	//��Outer�����ڶ���һ��Inner�࣬Inner����Outer����ڲ���(��Ƕ�࣬������)�����ڲ��ദ���ⲿ��ĳ�Աλ��ʱ������˽�л�
	class Inner{
		
		int x = 4;
		
		void function(){
			int x = 6;
			System.out.println("function :" + x);//6����ӡ���Ƿ����ڲ��ľֲ�������
			System.out.println("Inner :" + this.x);//4����ӡ�����ڲ���ĳ�Ա����x,��Ϊthis�ؼ���
			System.out.println("Outer : " + Outer.this.x);//3����ӡ�����ⲿ��Ա����x����ΪOuter.this
		}
	}
}


public class InnerClassDemo {

	public static void main(String[] args) {
		
		Outer out = new Outer();
		out.method();
		
		//ֱ�ӷ����ڲ���ĳ�Ա
		Outer.Inner in = new Outer().new Inner();//����д�������õ�������Ҫ���⣬��Ϊ�ڲ���������ⲿ���У����������ⲿ����󣬲��ܴ����ڲ������
		in.function();
	}

}