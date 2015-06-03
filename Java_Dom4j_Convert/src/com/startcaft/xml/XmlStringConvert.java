package com.startcaft.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlStringConvert {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		//String2Document();
		
		//codingDocument();
		
		readXmlAsString();
	}
	
	
	
	public static void readXmlAsString() throws Exception{
		//创建SAXReader对象
		SAXReader reader = new SAXReader();
		//读取一个文件，并创建出Document对象
		Document document = reader.read(new File("src//csdn.xml"));
		//获取根元素
		Element root = document.getRootElement();
		//把文档转换成字符串
		String docXmlText = document.asXML();
		System.out.println("-------XML文档内容--------");
		System.out.println(docXmlText);
		System.out.println("-------根节点的内容--------");
		String rootXmlText = root.asXML();
		System.out.println(rootXmlText);
		Element java = root.element("java");
		System.out.println("-------java节点的内容--------");
		String javaXmlText = java.asXML();
		System.out.println(javaXmlText);	
	} 
	
	
	/***
	 * 使用编码的形式创建一个XML文档并写入。
	 * 
	 * @throws Exception
	 */
	public static void codingDocument() throws Exception{
		
		//创建Document对象
		Document document = DocumentHelper.createDocument();
		//创建根节点
		Element root = document.addElement("csdn");
		Element javaEle = root.addElement("java");
		javaEle.setText("Java基础班");
		Element iosEle = root.addElement("IOS");
		iosEle.setText("IOS基础班");
		
		writerXml(document);
	} 
	
	
	/***
	 * 将一个文本字符串转换成Document对象
	 */
	public static void String2Document() throws Exception{
		String xmlStr = "<csdn><java>Java班</java><net>Net班</net></csdn>";
		Document document = DocumentHelper.parseText(xmlStr);
		Element e = document.getRootElement();
		System.out.println("XML文档根节点的名称是:" + e.getName());
		writerXml(document);
	}
	

	/***
	 * 将一个Document对象写入到一个屋里文件中
	 * 
	 * @param ddocument
	 */
	private static void writerXml(Document document) throws Exception {

		// 文本格式缩进
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置字符编码
		format.setEncoding("UTF-8");

		// 创建XMLWriter对象,通过一个输出流对象和一个Dom4j的格式化对象构造出来。
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(
				new FileOutputStream(new File("src//csdn.xml")), "UTF-8"),
				format);
		
		writer.write(document);
		writer.flush();
		writer.close();
	}
}
