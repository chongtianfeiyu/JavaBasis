import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * java.io.File类的名字有一定的误导性；
 * File类既能代表一个特定文明，又能代表一个目录下的一组文件。
 * 
 * java.io.FilenameFilter接口用于过滤文件名，
 * boolean accept(File dir,String name)方法测试指定文件名是否包含在某一个文件列表中。
 * 
 * @author wow
 *
 */
public class FileDemo {

	public static void main(String[] args) {
		
		FileInPathNoFilter();
		
		System.out.println("===========================");
		
		FileInPathWithFilter();
	}
	
	
	/**
	 * 输出一个指定目录下的所有文件名(不包含文件过滤)
	 * 
	 */
	private static void FileInPathNoFilter(){
		
		File path = new File(".");
		String[] fileList = path.list();
		
		Arrays.sort(fileList, String.CASE_INSENSITIVE_ORDER);
		
		for(String fName : fileList){
			System.out.println(fName);
		}
	}
	
	private static void FileInPathWithFilter(){
		
		File path = new File(".");
		String[] fileList = path.list(filter("D.*\\.java"));
		
		Arrays.sort(fileList, String.CASE_INSENSITIVE_ORDER);
		
		for(String fName : fileList){
			System.out.println(fName);
		}
	}

	
	/**
	 * 根据正则表达式过滤文件名
	 * 
	 * @param regex
	 * @return
	 */
	private static FilenameFilter filter(final String regex){
		
		return new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				Pattern pattern = Pattern.compile(regex);
				return pattern.matcher(name).matches();
			}
		};
	}
}
