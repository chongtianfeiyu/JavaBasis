
/**
 * 懒汉式的单例模式:
 * 
 * 线程安全的
 * 
 * @author wow
 *
 */
public class SingleOne {
	
	private static final SingleOne instance = new SingleOne();
	
	private SingleOne(){}
	
	public static SingleOne getInstance(){
		
		return instance;
	}
}
