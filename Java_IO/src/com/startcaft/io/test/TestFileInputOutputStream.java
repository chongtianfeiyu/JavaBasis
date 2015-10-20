package com.startcaft.io.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

/* 
 * 1.���ķ���
 * ���ա��������򡿵Ĳ�ͬ��Ϊ�����������������
 * ���ա����ݵ�λ���Ĳ�ͬ��Ϊ���ֽ���(8bit)���ַ���(16bit)��
 * ���ա����Ľ�ɫ���Ĳ�ͬ��Ϊ���ڵ���(ֱ���������ļ���)��������
 * 
 * 2.IO����ϵ
 * �������						�ڵ���(�ļ���)
 * InputStream					FileInputStream
 * OutputStream					FileOutputStream
 * Reader						FileReader
 * Writer						FileWriter
 */
public class TestFileInputOutputStream {
	
	/*
	 * ��Ӳ�̴��ڵ�һ���ļ��У���ȡ�����ݵ������У�ʹ��FileInputStream
	 * read()------ÿ�ζ�ȡһ�������ֽڡ�
	 */
	@Test
	public void testFileInputStream1(){
		
		FileInputStream fs = null;
		try {
			File file = new File("hello.txt");
			fs = new FileInputStream(file);
			
			int b = fs.read();						//ÿ�ζ�ȡһ�������ֽ�
			while(b != -1){
				System.out.print(b);
				b = fs.read();						//ָ������
			}
//			int b;									//������д��
//			while((b=fs.read()) != -1){
//				System.out.print(b);
//			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{									//�_�������P�]����һ��������
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
	 * read(byte[] b)---һ���xȡָ���L�ȵĔ����ֹ�
	 */
	@Test
	public void testFileInputStream2(){
		
		FileInputStream fs = null;
		try {
			File file = new File("hello.txt");
			fs = new FileInputStream(file);
			int length;
			byte[] b = new byte[20];					//20���L�ȵ�byte[]
			while((length=fs.read(b)) != -1){
				for (int i = 0; i < length; i++) {		//��Ҫ����byte[]���L�ȁ��v��Ҫʹ��read()�������ص�length���vbyte[]�еĔ�����
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
	 * FileOutPutStream��FileInputStream�ķ�������Ƕ�Ӧ��
	 */
	@Test
	public void testFileOutputStream(){
		
		File file = new File("hello2.txt");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(new String(" i love java").getBytes());		//ÿ��д��һ��byte[]��
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
	 * ��Ӳ�̶�ȡһ���ļ�����д�뵽��һ��λ�á�(�൱���ļ��ĸ���)
	 */
	@Test
	public void testFileInputOutputStream(){
		
		File file = new File("hello.txt");
		File file2 = new  File("hello3.txt");
		
		//������Ӧ��������
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			//��ȡhello.txt�����ݣ�д�뵽hello3.txt
			fis = new FileInputStream(file);
			fos = new FileOutputStream(file2);
			int len;
			byte[] b = new byte[20];
			while((len = fis.read(b)) != -1){
				//�����д��
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
