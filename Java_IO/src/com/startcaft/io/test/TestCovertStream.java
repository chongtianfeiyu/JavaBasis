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
	 * ��׼���룬�����
	 * ��׼���������System.out
	 * ��׼����������System.in
	 * 
	 * ��ϰĿ�꣺
	 * �Ӽ��������ַ�����Ҫ�󽫶�ȡ���������ַ���ת���ɴ�д���룬Ȼ����������������,
	 * ֱ������"e"����exitʱ���˳�����
	 */
	@Test
	public void test2(){
		
		BufferedReader br = null;
		try {
			InputStream in = System.in;
			InputStreamReader isr = new InputStreamReader(in);
			br = new BufferedReader(isr);
			System.out.println("�������ַ���:");
			
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
	 * ת������InputStreamReader��OutputStreamReader��
	 * ���룺�ַ���--->�ֽ�����
	 * ���룺�ֽ�����--->�ַ���
	 */
	@Test
	public void test1() {
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try {
			//����
			File file = new File("maven.txt");
			FileInputStream fis = new FileInputStream(file);//�ֽ���
			InputStreamReader isr = new InputStreamReader(fis,"UTF-8");//���ֽ���ת����һ���ַ�������Ҫָ�������ʽ
			br = new BufferedReader(isr);
			
			//����
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
