package com.startcaft.io.filewriter;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;

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
		
		String content = "Hello Java";
		
		FileWriter fWriter = new FileWriter("d:\\java.txt");
		
		fWriter.write(content);
		
		fWriter.flush();//ˢ�¸����Ļ�����ȥ��
		
		fWriter.close();//�ر���������Ҫ��ˢ�¸�������
	}
}
