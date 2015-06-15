package com.startcaft.io.filewriter;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;

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
		
		String content = "Hello Java";
		
		FileWriter fWriter = new FileWriter("d:\\java.txt");
		
		fWriter.write(content);
		
		fWriter.flush();//刷新该流的缓冲区去。
		
		fWriter.close();//关闭流，但是要先刷新该流对象。
	}
}
