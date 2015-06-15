package com.startcaft.io.filewriter;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.chrono.JapaneseChronology;

import org.junit.Test;

public class FileWriterTest {

	
	/**
	 * 使用FileWriter想一个文本文件输入信息
	 * java.io.Writer(字符写入流抽象基类)
	 * 		\\\java.io.OutPutSreamWriter(OutputStreamWriter是字符流通向字节流的桥梁)
	 * 				\\\java.io.FileWriter(用于向文件写入字符的便捷类，如果要指定字符编码和缓冲区大小，可以先在 FileOutputStream 上构造一个 OutputStreamWriter)
	 * 
	 * @throws IOException
	 */
	@Test
	public void writerContentToFile() throws IOException{
		
		String content = "Hello Java\r\nGood";//\r是linux系统下的换行符，\n是windows下的换行符
		
		FileWriter fWriter = new FileWriter("d:\\java.txt");
		
		fWriter.write(content);
		
		fWriter.flush();//刷新该流的缓冲区去。
		
		fWriter.close();//关闭流，但是要先刷新该流对象。
	}
	
	/**
	 * IO异常的专业处理。k:\\demo.txt 找不到这个文件
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
			//为了程序的严谨，finally要关闭fWriter对象，所以要先判断它是否为null，不为null才出现close。
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
