import java.util.Properties;
import java.util.Set;


/**
 * java.lang.System
 * System�಻�ܱ�ʵ���������ܱ��̳С�
 * ���ṩ�˱�׼���룬����ʹ����������
 * �����Ի�ȡjvm����ʱ�����Properties���Լ���̬��������Լ�������ص�Property(Tomcat����)��
 * �������Խ���gc�����������������ա�
 * 
 * PrintStream err�ֶα�ʾ"��׼"���������
 * InputStream in�ֶα�ʾ"��׼"������
 * PrintStream out�ֶα�ʾ"��׼"�����
 * 
 * @author wow
 *
 */
public class SystemClassDemo {

	public static void main(String[] args) {
		
		//�鿴�������к�ʱ
		long start = System.currentTimeMillis();
		showJvmProperies();
		System.out.println("==========================================================================");
		setCustomProperty();
		long end = System.currentTimeMillis();
		System.out.println("�������к�ʱ:" + (end - start) + "����");
	}
	
	/**
	 * ���jvm����ʱ�����ص�Properties
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
	 * �����Զ����Property
	 */
	private static void setCustomProperty(){
		System.setProperty("startcaft","5904395");
		showJvmProperies();
	}
}
