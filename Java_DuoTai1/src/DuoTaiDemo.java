
/**。
 * 1，在多态中，非静态成员函数的特点：
 * 在编译期间：参阅引用型变量所属的类中是否有调用的方法，如果有，则编译通过如果没有则编译失败。
 * 在运行期间：参阅对象所属的类中是否具有被调用的方法。
 * 简单总结成：成员函数在多态调用时，编译看左边，运行看右边。
 * 
 * 2，在多态中，成员变量的特点：
 * 无论在编译还是运行，都参阅左边(引用型变量所属的类)。
 * 
 * 3,在多态中，静态成员的特点：
 * 无论在编译还是运行，都参照左边(引用型变量所属的类)。
 * 
 * @author wow
 *
 */
public class DuoTaiDemo {

	public static void main(String[] args) {
		
		Fu f = new Zi();//这里就是多态了，父类实例的引用指向其子类的对象。
		//f.method1();//zi_method_1
		//f.method2();//fu_method_2
		//f.method3();这一句话，在编译器可以通过，运行时候会报错。
		
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

class Zi extends Fu{//method1()复写的父类的，method2继承自父类的，method3是子类特有的。
	
	int num = 8;
	@Override
	void method1() {//这是子类重写的父类的method1()方法
		System.out.println("zi_method_1");
	}
	void method3(){
		System.out.println("zi_method_3");
	}
	static void method4(){
		System.out.println("zi_method_4");
	}
}