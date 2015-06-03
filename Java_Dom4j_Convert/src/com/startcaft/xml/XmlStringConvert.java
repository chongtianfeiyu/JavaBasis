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
		//����SAXReader����
		SAXReader reader = new SAXReader();
		//��ȡһ���ļ�����������Document����
		Document document = reader.read(new File("src//csdn.xml"));
		//��ȡ��Ԫ��
		Element root = document.getRootElement();
		//���ĵ�ת�����ַ���
		String docXmlText = document.asXML();
		System.out.println("-------XML�ĵ�����--------");
		System.out.println(docXmlText);
		System.out.println("-------���ڵ������--------");
		String rootXmlText = root.asXML();
		System.out.println(rootXmlText);
		Element java = root.element("java");
		System.out.println("-------java�ڵ������--------");
		String javaXmlText = java.asXML();
		System.out.println(javaXmlText);	
	} 
	
	
	/***
	 * ʹ�ñ������ʽ����һ��XML�ĵ���д�롣
	 * 
	 * @throws Exception
	 */
	public static void codingDocument() throws Exception{
		
		//����Document����
		Document document = DocumentHelper.createDocument();
		//�������ڵ�
		Element root = document.addElement("csdn");
		Element javaEle = root.addElement("java");
		javaEle.setText("Java������");
		Element iosEle = root.addElement("IOS");
		iosEle.setText("IOS������");
		
		writerXml(document);
	} 
	
	
	/***
	 * ��һ���ı��ַ���ת����Document����
	 */
	public static void String2Document() throws Exception{
		String xmlStr = "<csdn><java>Java��</java><net>Net��</net></csdn>";
		Document document = DocumentHelper.parseText(xmlStr);
		Element e = document.getRootElement();
		System.out.println("XML�ĵ����ڵ��������:" + e.getName());
		writerXml(document);
	}
	

	/***
	 * ��һ��Document����д�뵽һ�������ļ���
	 * 
	 * @param ddocument
	 */
	private static void writerXml(Document document) throws Exception {

		// �ı���ʽ����
		OutputFormat format = OutputFormat.createPrettyPrint();
		// �����ַ�����
		format.setEncoding("UTF-8");

		// ����XMLWriter����,ͨ��һ������������һ��Dom4j�ĸ�ʽ�������������
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(
				new FileOutputStream(new File("src//csdn.xml")), "UTF-8"),
				format);
		
		writer.write(document);
		writer.flush();
		writer.close();
	}
}
