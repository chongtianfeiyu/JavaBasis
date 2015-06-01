import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;


/***
 * Java�����ļ��Ĳ���
 * 
 * Java.Util.Properties��Ļ����÷�
 * 
 * @author wow
 *
 */
public class TestProperties {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("========��ȡָ��key������ֵ========");
		String strValue = getValueByKey("/test.properties", "name");
		System.out.println("========���������б�========");
		getAllProperties("/test.properties");
		System.out.println("========д��������Ϣ�ļ�ֵ�Ժ��ڵ���========");
		setProperty("/test.properties", "startcaft", "5904395");
		
	}
	
	//����key��ȡ����ֵ
	public static String getValueByKey(String filePath,String keyString) {
		
		Properties ppsProperties = new Properties();
		try {
			
			//������ָ����classes�£�����а��Ļ���ǰ��Ӱ������ɡ�����/com/web/test.properties  
			InputStream inputStream = Object.class.getResourceAsStream(filePath);
			ppsProperties.load(inputStream);
			
			String strValue = ppsProperties.getProperty(keyString);
			System.out.println(strValue);
			return strValue;
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	//��ȡ������������
	public static void getAllProperties(String fileName){
		
		Properties properties = new Properties();
		try {
			InputStream inputStream = Object.class.getResourceAsStream(fileName);
			properties.load(inputStream);
			
			//��ȡ�����б�ĵ�����
			Enumeration enumeration = properties.propertyNames();
			
			while (enumeration.hasMoreElements()) {
				String keyString = (String)enumeration.nextElement();
				String valueString = properties.getProperty(keyString);
				System.out.println(keyString + "=" + valueString);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//д���ֵ��
	public static void setProperty(String fileName,String keyString,String valueString) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = Object.class.getResourceAsStream(fileName);
			properties.load(inputStream);
			
			OutputStream outputStream = new FileOutputStream(fileName);
			
			properties.setProperty(keyString, valueString);
			properties.store(outputStream, "Insert " + keyString + "=" + valueString);
			
			//����
			Enumeration enumeration = properties.propertyNames();
			while (enumeration.hasMoreElements()) {
				String keyString2 = (String) enumeration.nextElement();
				String valueString2 = properties.getProperty(keyString2);
				System.out.println(keyString2 + "=" + valueString2);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
