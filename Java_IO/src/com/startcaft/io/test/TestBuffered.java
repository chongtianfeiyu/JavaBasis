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
	 * ʹ�û��������������ļ�������Ч��
	 */
	@Test
	public void testBufferedInputOutputStream() {
		
		//1.�ṩ���룬д�����ļ�
		File file = new File("zhuomian.jpg");
		File file1 = new File("zhuomian_copy.jpg");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		//2.�����������������
		try {
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(file1);
			
			//3.����������������������Ķ�����Ϊ�βδ��ݸ��������Ĺ�����;
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			
			//4.�����ʵ���ļ����ƵĲ���
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
			//�رջ����������ڲ���رն�Ӧ�����룬�����
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
