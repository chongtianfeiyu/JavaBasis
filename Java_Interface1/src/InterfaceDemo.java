
/**
 * Java中接口的特性：
 * 1，接口中的常见定义：常量，抽象方法
 * 2，接口中的成员都有固定的修饰符
 * 		常量：pubilc static final
 * 		方法：public abstract
 * 记住：接口中的成员都是public修饰的
 * 
 * 
 * 
 * 接口：是不可以创建对象的，因为有抽象方法。
 * 需要被子类实现，子类对接口中的抽象方法全部覆盖后，子类才可以实例化，否则子类还是一个抽象类。
 * 
 * 
 * 接口可以被类多实现，也就是一个类可以实现多个接口(Java多继承的机制在这里的体现了,只允许继承一个父类，但能实现多个接口)
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
