package com.startcaft.io.test;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

/*
 * FileReader��FileWriter�����ַ��ģ�
 * ���ڷ��ı��ļ�(��Ƶ�ļ�����Ƶ�ļ���ͼƬ�ȶ������ļ�)��ֻ��ʹ���ֽ�����
 */
public class TestFileReaderWriter {
	
	//�����ļ�
	@Test
	public void testFileReaderWriter(){
		
		FileReader reader = null;
		FileWriter writer = null;
		
		try {
			File file = new File("maven.txt");			//��������Ӧ���ļ�һ��Ҫ���ڣ�������׳��쳣��
			File target = new File("maven_copy.txt");
			
			reader = new FileReader(file);
			writer = new FileWriter(target);
			
			char[] c = new char[1024];
			int flag;
			
			while((flag = reader.read(c)) != -1){
				
				String str = new String(c, 0, flag);
				writer.write(str);
				System.out.println("copy_done");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testFileReader() {
		
		File file = new File("maven.txt");
		FileReader reader = null;
		char[] c = new char[24];
		int len;
		
		try {
			reader = new FileReader(file);
			while((len = reader.read(c)) != -1){	//һ���ģ�ֻҪ��ȡ����length������-1���ļ���û�н���
				String str = new String(c, 0, len);
				System.out.print(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
