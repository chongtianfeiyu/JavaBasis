package com.startcaft.io.filereader;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class FileReaderTest {

	/**
	 * 使锟斤拷FileReader锟斤拷取一锟斤拷锟侥硷拷锟斤拷锟斤拷锟斤拷 java.io.Reader(锟街凤拷锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷锟�)
	 * \\\java.io.InputSreamWriter
	 * \\\java.io.FileReader(锟斤拷锟节讹拷取锟侥硷拷锟斤拷锟捷的憋拷锟斤拷啵拷锟斤拷要指锟斤拷锟街凤拷锟斤拷锟斤拷突锟斤拷锟斤拷锟斤拷锟叫�
	 * ---默锟较的憋拷锟斤拷锟斤拷jvm锟斤拷properties锟斤拷)
	 * 
	 * @throws IOException
	 */
	@Test
	public void ReaderFileTest() {

		try {
			// 锟斤拷锟斤拷一锟斤拷锟侥硷拷锟斤拷取锟斤拷锟斤拷锟襟，猴拷指锟斤拷锟斤拷锟狡碉拷锟侥硷拷锟斤拷锟斤拷锟�
			// 要锟斤拷证锟斤拷锟侥硷拷锟窖撅拷锟斤拷锟节ｏ拷锟斤拷锟斤拷锟斤拷锟斤拷冢锟斤拷锟结发锟斤拷FileNotFoundException锟届常锟斤拷
			FileReader fr = new FileReader("d:\\order.txt");

			// read()锟斤拷锟斤拷每锟斤拷只锟斤拷取一锟斤拷锟街凤拷,锟斤拷锟揭伙拷锟皆讹拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�-1锟斤拷锟角碉拷末尾锟剿★拷
			int result1 = fr.read();
			int result2 = fr.read();

			System.out.println(result1);
			System.out.println(result2);

			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通锟斤拷while循锟斤拷锟斤拷取锟侥硷拷锟斤拷锟捷ｏ拷只要read()锟斤拷锟斤拷锟侥凤拷锟斤拷值锟斤拷锟斤拷锟斤拷-1锟酵匡拷锟斤拷一直循锟斤拷锟斤拷
	 * 
	 * @throws
	 * 
	 */
	@Test
	public void ReaderFile2Test() {

		FileReader reader;
		try {
			reader = new FileReader("d:\\java.txt");
			/*
			while (true) {
				int ch = reader.read();
				if (ch == -1) {
					break;
				}
				System.out.println((char)ch);
			}
			*/
			
			int ch = 0;
			while((ch = reader.read()) != -1){
				System.out.print((char)ch);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	/**
	 * int read(char[] arg)锟斤拷锟斤拷锟斤拷取锟侥硷拷锟斤拷锟斤拷
	 * 
	 */
	@Test
	public void ReadeFile3Test(){
		
		try {
			FileReader reader = new FileReader("d:\\java.txt");
			char[] chars = new char[50];
			
			int length = reader.read(chars);//int read(char[] arg)锟斤拷锟截碉拷锟斤拷锟斤拷锟秸讹拷取锟侥硷拷锟街凤拷锟侥革拷锟斤拷锟斤拷
			
			System.out.println("char nums:" + length + "///" + new String(chars));
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("使锟斤拷char[]循锟斤拷锟斤拷取");
		
		//锟斤拷锟斤拷姆锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷50锟斤拷锟斤拷锟饺的ｏ拷锟斤拷锟斤拷募锟斤拷锟斤拷锟窖拷锟斤拷锟饺★拷拇锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟揭伙拷锟斤拷锟剿碉拷锟絚har[] chars = new char[1024]锟酵猴拷
		int charNum = 0;
		try {
			FileReader reader = new FileReader("d:\\java.txt");
			char[] chars = new char[1024];
			while((charNum = reader.read(chars)) != -1){
				System.out.println(new String(chars, 0, charNum));
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	
	/**
	 * 打印一个.cs源代码文件到控制台
	 * 
	 */
	@Test
	public void printJavaFileTest(){
		
		try {
			FileReader reader = new FileReader("D:\\Project\\api2.4-升级优化\\HuTuApi.aspx.cs");
			int length = 0;
			char[] chars = new char[1024];
			while((length = reader.read(chars)) != -1){
				System.out.print(new String(chars,0,length));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	@Test
	public void CopyFileContentTest(){
		
		try {
			FileReader reader = new FileReader("D:\\Project\\api2.4-升级优化\\HuTuApi.aspx.cs");//被读取的文件
			FileWriter writer = new FileWriter("D:\\api.cs");//新创建的文件
			
			//第一种方法，读一个字符，写一个字符，这样读取，磁盘转疯了。速度比较慢
			/*
			int result = 0;
			while((result = reader.read()) != -1){
				writer.write((char)result);
			}
			writer.close();
			reader.close();
			*/
			
			
			//第二种方法，定义一个数组(缓冲区)读取，
			char[] buff = new char[1025];
			int length = 0;
			while((length = reader.read(buff)) != -1){
				writer.write(buff, 0, length);
			}
			
			writer.close();
			reader.close();
			
			System.out.println("copy done!");
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}
