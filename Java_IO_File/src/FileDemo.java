import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * java.io.File���������һ�������ԣ�
 * File����ܴ���һ���ض����������ܴ���һ��Ŀ¼�µ�һ���ļ���
 * 
 * java.io.FilenameFilter�ӿ����ڹ����ļ�����
 * boolean accept(File dir,String name)��������ָ���ļ����Ƿ������ĳһ���ļ��б��С�
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
	 * ���һ��ָ��Ŀ¼�µ������ļ���(�������ļ�����)
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
	 * ����������ʽ�����ļ���
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
