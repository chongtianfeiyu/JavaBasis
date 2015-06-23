
/**
 * ����ʽ����ģʽ��
 * 
 * ˫���жϣ���һ���ж�֮��Ӹ���ȷ���̰߳�ȫ��
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
