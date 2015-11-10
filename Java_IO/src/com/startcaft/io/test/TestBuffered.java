package com.startcaft.io.test;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class TestBuffered {
	
	/*
	 * 使用缓冲流可以提升文件操作的效率
	 */
	@Test
	public void testBufferedInputOutputStream() {
		
		//1.提供读入，写出的文件
		File file = new File("zhuomian.jpg");
		File file1 = new File("zhuomian_copy.jpg");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		//2.创建输入流，输出流
		try {
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(file1);
			
			//3.将创建的输入流，输出流的对象作为形参传递给缓冲流的构造器;
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			
			//4.具体的实现文件复制的操作
			byte[] b = new byte[1024];
			int len;
			while((len = bis.read(b)) != - 1){
				bos.write(b, 0, len);
				bos.flush();
			}
			System.out.println("copy done!");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			//关闭缓冲流，其内部会关闭对应的输入，输出流
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
