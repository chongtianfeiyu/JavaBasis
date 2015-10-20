package com.startcaft.io.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

/* 
 * 1.流的分类
 * 按照【数据流向】的不同分为：输入流，输出流。
 * 按照【数据单位】的不同分为：字节流(8bit)，字符流(16bit)。
 * 按照【流的角色】的不同分为：节点流(直接作用于文件的)，处理流
 * 
 * 2.IO的体系
 * 抽象基类						节点流(文件流)
 * InputStream					FileInputStream
 * OutputStream					FileOutputStream
 * Reader						FileReader
 * Writer						FileWriter
 */
public class TestFileInputOutputStream {
	
	/*
	 * 从硬盘存在的一个文件中，读取其内容到程序中，使用FileInputStream
	 * read()------每次读取一个数据字节。
	 */
	@Test
	public void testFileInputStream1(){
		
		FileInputStream fs = null;
		try {
			File file = new File("hello.txt");
			fs = new FileInputStream(file);
			
			int b = fs.read();						//每次读取一个数据字节
			while(b != -1){
				System.out.print(b);
				b = fs.read();						//指针下移
			}
//			int b;									//更简洁的写法
//			while((b=fs.read()) != -1){
//				System.out.print(b);
//			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{									//確保流的關閉操作一定會執行
			if(fs != null){
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * FileInputStream
	 * read(byte[] b)---一次讀取指定長度的數據字節
	 */
	@Test
	public void testFileInputStream2(){
		
		FileInputStream fs = null;
		try {
			File file = new File("hello.txt");
			fs = new FileInputStream(file);
			int length;
			byte[] b = new byte[20];					//20個長度的byte[]
			while((length=fs.read(b)) != -1){
				for (int i = 0; i < length; i++) {		//不要根據byte[]的長度來遍歷，要使用read()方法返回的length來遍歷byte[]中的數據。
					System.out.print(b[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(fs != null){
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * FileOutPutStream跟FileInputStream的方法差不多是对应的
	 */
	@Test
	public void testFileOutputStream(){
		
		File file = new File("hello2.txt");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(new String(" i love java").getBytes());		//每次写入一个byte[]。
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * 从硬盘读取一个文件，并写入到另一个位置。(相当于文件的复制)
	 */
	@Test
	public void testFileInputOutputStream(){
		
		File file = new File("hello.txt");
		File file2 = new  File("hello3.txt");
		
		//产生相应的流对象
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			//读取hello.txt的内容，写入到hello3.txt
			fis = new FileInputStream(file);
			fos = new FileOutputStream(file2);
			int len;
			byte[] b = new byte[20];
			while((len = fis.read(b)) != -1){
				//错误的写法
				//fos.write(b);				
				//fos.write(b,0,b.length);
				fos.write(b,0,len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
