import java.util.Properties;
import java.util.Set;


/**
 * java.lang.System
 * System类不能被实例化，不能被继承。
 * 它提供了标准输入，输出和错误输出流；
 * 它可以获取jvm运行时候加载Properties，以及动态添加属于自己程序独特的Property(Tomcat类似)。
 * 它还可以进行gc操作，进行垃圾回收。
 * 
 * PrintStream err字段表示"标准"错误输出流
 * InputStream in字段表示"标准"输入流
 * PrintStream out字段表示"标准"输出流
 * 
 * @author wow
 *
 */
public class SystemClassDemo {

	public static void main(String[] args) {
		
		//查看程序运行耗时
		long start = System.currentTimeMillis();
		showJvmProperies();
		System.out.println("==========================================================================");
		setCustomProperty();
		long end = System.currentTimeMillis();
		System.out.println("程序运行耗时:" + (end - start) + "毫秒");
	}
	
	/**
	 * 输出jvm运行时所加载的Properties
	 */
	private static void showJvmProperies(){
		Properties properties = System.getProperties();
		
		Set<String> keys = properties.stringPropertyNames();
		for (String string : keys) {
			String value = properties.getProperty(string);
			System.out.println(string + ":" + value);
		}
	}
	
	/**
	 * 设置自定义的Property
	 */
	private static void setCustomProperty(){
		System.setProperty("startcaft","5904395");
		showJvmProperies();
	}
}
