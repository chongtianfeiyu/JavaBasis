
/**
 * ����ʽ�ĵ���ģʽ:
 * 
 * �̰߳�ȫ��
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
