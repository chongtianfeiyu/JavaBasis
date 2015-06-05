/**
 * Java所有的对象(包括数组)，都直接或者间接的继承自java.lang.Object类
 * 换句话说，Object类就是Java中的上帝。
 * 在不涉及多线程的情况下，Object中有几个重要的方法
 * 1，boolean equals(Object object)方法------
 * 2，String toString()方法------返回一个对象的字符串表现形式
 * 3,int hashCode()方法------
 * @author wow
 *
 */
public class ObjectDemo {

	public static void main(String[] args) {
		
		DemoNotToString notToString = new DemoNotToString();
		System.out.println("没有重写toString方法的子类对象，会沿用父类的默认实现,默认的toString()方法用到了反射");
		System.out.println(notToString.toString());
		
		DemoToString toString = new DemoToString();
		System.out.println("重写toString方法的子类对象,调用自己的toString实现,Java推荐每个自定义类型都实现自己的toString()方法");
		System.out.println(toString.toString());
		
		System.out.println("=========================================");
		Dog dog = new Dog(10);
		Cat cat1 = new Cat(4);
		Cat cat2 = new Cat(4);
		boolean result = cat1.equals(cat2);
		System.out.println("cat1和cat2的比较，只比较成员变量num是否相等，因为Cat类重写了equals方法,所以不是默认的比较对象的引用地址了");
		System.out.println(result);
		
		boolean result1 = cat1.equals(dog);
		System.out.println("cat1和dog的比较,程序直接返回false，虽然Dog类是Object的子类，但是在程序中必须进行参数类型的判断，不然在强制转换时会报错");
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
		return "我是Demo类的对象";
	}
}
