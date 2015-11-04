package com.startcaft.io.test;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

/*
 * FileReader，FileWriter处理字符的，
 * 对于非文本文件(视频文件，音频文件，图片等二进制文件)，只能使用字节流！
 */
public class TestFileReaderWriter {
	
	//复制文件
	@Test
	public void testFileReaderWriter(){
		
		FileReader reader = null;
		FileWriter writer = null;
		
		try {
			File file = new File("maven.txt");			//输入流对应的文件一定要存在，否则会抛出异常。
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
			while((len = reader.read(c)) != -1){	//一样的，只要读取到的length不等于-1，文件就没有结束
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
