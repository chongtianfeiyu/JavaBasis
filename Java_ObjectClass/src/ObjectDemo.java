/**
 * Java���еĶ���(��������)����ֱ�ӻ��߼�ӵļ̳���java.lang.Object��
 * ���仰˵��Object�����Java�е��ϵۡ�
 * �ڲ��漰���̵߳�����£�Object���м�����Ҫ�ķ���
 * 1��boolean equals(Object object)����------
 * 2��String toString()����------����һ��������ַ���������ʽ
 * 3,int hashCode()����------
 * @author wow
 *
 */
public class ObjectDemo {

	public static void main(String[] args) {
		
		DemoNotToString notToString = new DemoNotToString();
		System.out.println("û����дtoString������������󣬻����ø����Ĭ��ʵ��,Ĭ�ϵ�toString()�����õ��˷���");
		System.out.println(notToString.toString());
		
		DemoToString toString = new DemoToString();
		System.out.println("��дtoString�������������,�����Լ���toStringʵ��,Java�Ƽ�ÿ���Զ������Ͷ�ʵ���Լ���toString()����");
		System.out.println(toString.toString());
		
		System.out.println("=========================================");
		Dog dog = new Dog(10);
		Cat cat1 = new Cat(4);
		Cat cat2 = new Cat(4);
		boolean result = cat1.equals(cat2);
		System.out.println("cat1��cat2�ıȽϣ�ֻ�Ƚϳ�Ա����num�Ƿ���ȣ���ΪCat����д��equals����,���Բ���Ĭ�ϵıȽ϶�������õ�ַ��");
		System.out.println(result);
		
		boolean result1 = cat1.equals(dog);
		System.out.println("cat1��dog�ıȽ�,����ֱ�ӷ���false����ȻDog����Object�����࣬�����ڳ����б�����в������͵��жϣ���Ȼ��ǿ��ת��ʱ�ᱨ��");
		System.out.println(result1);
	}
}

/************equals(Object object)*************/
class Dog{
	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public Dog(int num){
		this.num = num;
	}
}

class Cat{
	private int num;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public Cat(int num){
		this.num = num;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Cat) {
			Cat cat = (Cat)obj;
			return this.num == cat.num;
		}
		return false;
	}
}

/********toString()***********/
class DemoNotToString{
	
}

class DemoToString{
	
	@Override
	public String toString() {
		return "����Demo��Ķ���";
	}
}
