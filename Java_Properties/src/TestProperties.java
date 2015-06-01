import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;


/***
 * Java配置文件的操作
 * 
 * Java.Util.Properties类的基本用法
 * 
 * @author wow
 *
 */
public class TestProperties {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("========获取指定key的属性值========");
		String strValue = getValueByKey("/test.properties", "name");
		System.out.println("========迭代属性列表========");
		getAllProperties("/test.properties");
		System.out.println("========写入配置信息的键值对后在迭代========");
		setProperty("/test.properties", "startcaft", "5904395");
		
	}
	
	//根据key获取属性值
	public static String getValueByKey(String filePath,String keyString) {
		
		Properties ppsProperties = new Properties();
		try {
			
			//这里是指放在classes下，如果有包的话，前面加包名即可。例：/com/web/test.properties  
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
	
	//获取所有配置属性
	public static void getAllProperties(String fileName){
		
		Properties properties = new Properties();
		try {
			InputStream inputStream = Object.class.getResourceAsStream(fileName);
			properties.load(inputStream);
			
			//获取属性列表的迭代器
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
	
	//写入键值对
	public static void setProperty(String fileName,String keyString,String valueString) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = Object.class.getResourceAsStream(fileName);
			properties.load(inputStream);
			
			OutputStream outputStream = new FileOutputStream(fileName);
			
			properties.setProperty(keyString, valueString);
			properties.store(outputStream, "Insert " + keyString + "=" + valueString);
			
			//迭代
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
