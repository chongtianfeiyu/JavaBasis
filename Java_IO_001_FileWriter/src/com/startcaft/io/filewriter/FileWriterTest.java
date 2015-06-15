package com.startcaft.io.filewriter;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.chrono.JapaneseChronology;

import org.junit.Test;

public class FileWriterTest {

	
	/**
	 * ʹ��FileWriter��һ���ı��ļ�������Ϣ
	 * java.io.Writer(�ַ�д�����������)
	 * 		\\\java.io.OutPutSreamWriter(OutputStreamWriter���ַ���ͨ���ֽ���������)
	 * 				\\\java.io.FileWriter(�������ļ�д���ַ��ı���࣬���Ҫָ���ַ�����ͻ�������С���������� FileOutputStream �Ϲ���һ�� OutputStreamWriter)
	 * 
	 * @throws IOException
	 */
	@Test
	public void writerContentToFile() throws IOException{
		
		String content = "Hello Java\r\nGood";//\r��linuxϵͳ�µĻ��з���\n��windows�µĻ��з�
		
		FileWriter fWriter = new FileWriter("d:\\java.txt");
		
		fWriter.write(content);
		
		fWriter.flush();//ˢ�¸����Ļ�����ȥ��
		
		fWriter.close();//�ر���������Ҫ��ˢ�¸�������
	}
	
	/**
	 * IO�쳣��רҵ����k:\\demo.txt �Ҳ�������ļ�
	 */
	@Test(expected=java.io.FileNotFoundException.class)
	public void IOExceptionTest(){
		
		FileWriter fWriter = null;
		try {
			fWriter = new FileWriter("k:\\demo.txt");
			fWriter.write("ioexception");
			fWriter.flush();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		finally{
			//Ϊ�˳�����Ͻ���finallyҪ�ر�fWriter��������Ҫ���ж����Ƿ�Ϊnull����Ϊnull�ų���close��
			try {
				if (fWriter != null) {
					fWriter.close();
				}
			} catch (IOException e2) {
				System.out.println(e2.toString());
			}
		}
	}
}
