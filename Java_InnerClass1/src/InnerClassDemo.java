/**
 * Java中内部类的访问规则：
 * 1，内部类可以直接访问外部类的成员，包括私有的。
 * 2，外部类想要访问内部类，必须先建立内部类的对象。
 * 
 * 
 * 访问方式：
 * 1，内部类之所有可以访问外部类的成员，是因为内部类中持有了一个外部类的引用，格式：外部类名.this
 * 
 *,2，当内部类定义在外部类的成员位置上，而且非私有，可以在外部类其他类中直接建立这个内部类的对象，
 * 格式：外部类名.内部类名 变量名 = new 内部类名().new 外部类名();
 * 
 * 3，当内部类在成员位置上，就可以被成员修饰符所修饰。
 * 比如：private ：将内部类在外部类中进行封装
 * 	   static：内部类就具有了静态的特性------
 * 
 * 当内部类被static修饰时，只能访问外部类的静态成员了，出现了访问局限，一切规则参照static关键字。
 * 注意：
 * 1，当内部类中定义了静态成员，这个内部类必须是静态的。
 * 2，当外部类中的静态方法访问内部类时，内部类也必须是静态的。
 * 
 * @author wow
 *
 */
class Outer{
	
	private int x = 3;
	
	void method(){
		Inner in = new Inner();//创建了一个内部类的对象。
		in.function();
	}
	
	//在Outer类中在定义一个Inner类，Inner就是Outer类的内部类(内嵌类，内置类)，当内部类处于外部类的成员位置时，可以私有化
	class Inner{
		
		int x = 4;
		
		void function(){
			int x = 6;
			System.out.println("function :" + x);//6，打印的是方法内部的局部变量。
			System.out.println("Inner :" + this.x);//4，打印的是内部类的成员变量x,因为this关键字
			System.out.println("Outer : " + Outer.this.x);//3，打印的是外部成员变量x，因为Outer.this
		}
	}
}


public class InnerClassDemo {

	public static void main(String[] args) {
		
		Outer out = new Outer();
		out.method();
		
		//直接访问内部类的成员
		Outer.Inner in = new Outer().new Inner();//这种写法不会用到。但是要理解，因为内部类包含在外部类中，必须先有外部类对象，才能创建内部类对象。
		in.function();
	}

}
