
/**
 * 懒汉式单例模式：
 * 
 * 双重判断，第一次判断之后加个锁确保线程安全。
 * 
 * @author wow
 *
 */
public class SingleTwo {

	private static SingleTwo instance;
	
	private SingleTwo(){}
	
	public static SingleTwo getInstance(){
		
		if (instance == null) {
			
			synchronized (SingleTwo.class) {
				
				if (instance == null) {
					instance = new SingleTwo();
				}
			}
		}
		return instance;
	}
}
