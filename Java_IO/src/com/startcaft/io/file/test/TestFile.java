package com.startcaft.io.file.test;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;

/*
 * java.io.File��
 * 1��File��һ���࣬�й���������������󡣴˶����Ӧ��һ�����ļ�����һ�����ļ�Ŀ¼����
 * 2��File���������ƽ̨�޹صġ�
 * 3��File�еķ��������漰����δ�����ɾ�����������ȣ��Լ���ȡ�ļ�����Ŀ¼����Ϣ���޷��޸��ļ��еľ������ݣ�������IO������ɡ�
 * 4��File��Ķ�����ΪIO���ľ�����Ĺ��������βΡ�
 */
public class TestFile {
	
	/*
	 * ·����
	 * ����·��---�����̷����ڵ��������ļ�·��
	 * ���·��---�ڵ�ǰ�ļ�Ŀ¼�µ��ļ���·��
	 * 
	 * getName()---��ȡ�ļ���
	 * getPath()---��ȡ�ļ�·��
	 * getAbsoluteFile()---��ȡ�ļ������ĵľ���·��
	 * getAbsolutePath()---��ȡ�ļ��ľ���·��
	 * getParent()---��ȡ�ļ��ϼ�Ŀ¼
	 * renameTo(File newName)---�������ļ���Ҫ��Դ�ļ�һ�����ڣ�Ŀ���ļ�һ�������ڡ�
	 */
	@Test
	public void test1() {
		
		File file1 = new File("D:\\iotest\\hello.txt");
		//File file = new File("D:/iotest/hello.txt");
		File file2 = new File("hello.txt");
		
		File file3 = new File("d:/iotest/io1");
		
		System.out.println(file1.getName());
		System.out.println(file1.getPath());
		System.out.println(file1.getParent());
		System.out.println(file1.getAbsoluteFile());
		System.out.println(file1.getAbsolutePath());
		System.out.println("////////////////////////////////////////////////////////////////////");
		System.out.println(file3.getName());
		System.out.println(file3.getPath());
		System.out.println(file3.getParent());
		System.out.println(file3.getAbsoluteFile());
		System.out.println(file3.getAbsolutePath());
		
		boolean result = file1.renameTo(file2);//false file2�Ѿ�����
		System.out.println(result);
	}
	
	/*
	 * exists()---�ļ���Ŀ¼�Ƿ����
	 * cabWrite()---�ļ��Ƿ��д
	 * canRead()---�ļ��Ƿ�ɶ�
	 * isFile()---�Ƿ����ļ������ļ�Ŀ¼
	 * isDirectory()---�Ƿ����ļ�Ŀ¼�����ļ�
	 * lastModified()---����޸�ʱ��
	 * length()---�ļ������ݵĳ���
	 */
	@Test
	public void test2(){
		
		File file1 = new File("d:/iotest/hello.txt");
		File file2 = new File("d:/iotest/io1");
		
		//�����ļ���
		System.out.println(file1.exists());
		System.out.println(file1.canWrite());
		System.out.println(file1.canRead());
		System.out.println(file1.isFile());
		System.out.println(file1.isDirectory());
		System.out.println(new Date(file1.lastModified()));
		System.out.println(file1.length());
		
		System.out.println("/////////////////////////////////");
		
		//�����ļ�Ŀ¼
		System.out.println(file2.exists());
		System.out.println(file2.canWrite());
		System.out.println(file2.canRead());
		System.out.println(file2.isFile());
		System.out.println(file2.isDirectory());
		System.out.println(new Date(file2.lastModified()));
		System.out.println(file2.length());
	}
	
	/*
	 * createNewFile()---����һ���ļ�
	 * delete()---ɾ��һ���ļ�
	 * mkDir()---����һ���ļ�Ŀ¼��ֻ�����ϲ�Ŀ¼����ʱ�����ܴ���
	 * mkDirs()---����һ���ļ�Ŀ¼�����ϲ�Ŀ¼������ʱ��һ������
	 * list()---����һ��Ŀ¼�µ������ļ�������
	 * listFiles()---����һ��Ŀ¼�µ����е�File����
	 */
	@Test
	public void test3() throws IOException{
		
		File file1 = new File("d:/iotest/hello.txt");
		System.out.println(file1.delete());
		
		if(!file1.exists()){
			boolean b = file1.createNewFile();
			System.out.println(b);
		}
		
		File file2 = new File("d:/iotest/io2");
		if(!file2.exists()){
			boolean b = file2.mkdir();
			System.out.println(b);
		}
		
		File file3 = new File("d:/iotest");
		String[] fileNames = file3.list();
		for (String string : fileNames) {
			System.out.println(string);
		}
		
		File[] files = file3.listFiles();
		for (File file : files) {
			System.out.println(file.getName());
		}
	}
}
