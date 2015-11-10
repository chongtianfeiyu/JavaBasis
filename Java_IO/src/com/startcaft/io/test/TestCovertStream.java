package com.startcaft.io.test;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class TestCovertStream {
	
	/*
	 * 标准输入，输出流
	 * 标准的输出流，System.out
	 * 标准的输入流，System.in
	 * 
	 * 练习目标：
	 * 从键盘输入字符串，要求将读取到的整行字符串转换成大写输入，然后继续进行输入操作,
	 * 直到输入"e"或者exit时，退出程序
	 */
	@Test
	public void test2(){
		
		BufferedReader br = null;
		try {
			InputStream in = System.in;
			InputStreamReader isr = new InputStreamReader(in);
			br = new BufferedReader(isr);
			System.out.println("请输入字符串:");
			
			String str;
			while(true){
				str = br.readLine();
				if(str.equalsIgnoreCase("e") || str.equalsIgnoreCase("exit")){
					break;
				}
				String str1 = str.toUpperCase();
				System.out.println(str1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/*
	 * 转换流，InputStreamReader，OutputStreamReader；
	 * 编码：字符串--->字节数组
	 * 解码：字节数组--->字符串
	 */
	@Test
	public void test1() {
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try {
			//解码
			File file = new File("maven.txt");
			FileInputStream fis = new FileInputStream(file);//字节流
			InputStreamReader isr = new InputStreamReader(fis,"UTF-8");//将字节流转换成一个字符流，需要指定编码格式
			br = new BufferedReader(isr);
			
			//编码
			File file1 = new File("maven_convert_stream.txt");
			FileOutputStream fos = new FileOutputStream(file1);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			bw = new BufferedWriter(osw);
			
			String str;
			while((str = br.readLine()) != null){
				bw.write(str);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
