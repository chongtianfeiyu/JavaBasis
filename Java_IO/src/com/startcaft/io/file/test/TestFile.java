package com.startcaft.io.file.test;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;

/*
 * java.io.File类
 * 1，File是一个类，有构造器来创建其对象。此对象对应着一个【文件】或一个【文件目录】。
 * 2，File类对象是与平台无关的。
 * 3，File中的方法，仅涉及到如何创建，删除，重命名等，以及获取文件或者目录的信息。无法修改文件中的具体内容，必须由IO流来完成。
 * 4，File类的对象常作为IO流的具体类的构造器的形参。
 */
public class TestFile {
	
	/*
	 * 路径：
	 * 绝对路径---包括盘符在内的完整的文件路径
	 * 相对路径---在当前文件目录下的文件的路径
	 * 
	 * getName()---获取文件名
	 * getPath()---获取文件路径
	 * getAbsoluteFile()---获取文件完整的的绝对路径
	 * getAbsolutePath()---获取文件的绝对路径
	 * getParent()---获取文件上级目录
	 * renameTo(File newName)---重命名文件，要求源文件一定存在，目标文件一定不存在。
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
		
		boolean result = file1.renameTo(file2);//false file2已经存在
		System.out.println(result);
	}
	
	/*
	 * exists()---文件或目录是否存在
	 * cabWrite()---文件是否可写
	 * canRead()---文件是否可读
	 * isFile()---是否是文件而非文件目录
	 * isDirectory()---是否是文件目录而非文件
	 * lastModified()---最后修改时间
	 * length()---文件的内容的长度
	 */
	@Test
	public void test2(){
		
		File file1 = new File("d:/iotest/hello.txt");
		File file2 = new File("d:/iotest/io1");
		
		//关于文件的
		System.out.println(file1.exists());
		System.out.println(file1.canWrite());
		System.out.println(file1.canRead());
		System.out.println(file1.isFile());
		System.out.println(file1.isDirectory());
		System.out.println(new Date(file1.lastModified()));
		System.out.println(file1.length());
		
		System.out.println("/////////////////////////////////");
		
		//关于文件目录
		System.out.println(file2.exists());
		System.out.println(file2.canWrite());
		System.out.println(file2.canRead());
		System.out.println(file2.isFile());
		System.out.println(file2.isDirectory());
		System.out.println(new Date(file2.lastModified()));
		System.out.println(file2.length());
	}
	
	/*
	 * createNewFile()---创建一个文件
	 * delete()---删除一个文件
	 * mkDir()---创建一个文件目录，只有在上层目录存在时，才能创建
	 * mkDirs()---创建一个文件目录，当上层目录不存在时，一并创建
	 * list()---遍历一个目录下的所有文件的名称
	 * listFiles()---遍历一个目录下的所有的File对象
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
