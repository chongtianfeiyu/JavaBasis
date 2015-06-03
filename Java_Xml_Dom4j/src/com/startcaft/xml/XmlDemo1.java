package com.startcaft.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import javax.xml.soap.Node;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlDemo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*System.out
				.println("==============将一个Document对象写入到一个XML文件中================");
		String xmlTextString = "<A id='001'><B>你干什么?</B></A>";
		try {
			Document document = DocumentHelper.parseText(xmlTextString);
			try {
				writerDocumentToFile(document);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(document.asXML());
		} catch (DocumentException e) {
			e.printStackTrace();
		}*/
		
		
		LoadAndEditXml();
	}
	
	

	/***
	 * 将document文档对象写入一个新的xml文件
	 * 
	 * @param document
	 * @throws Exception
	 */
	private static void writerDocumentToFile(Document document) throws Exception {
		// 紧凑的排版
		// OutputFormat format = OutputFormat.createCompactFormat();
		// 排版随进的格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置XML文档的编码格式
		format.setEncoding("UTF-8");
		// 创建XMLWriter对象，指定了写出文件及其编码格式
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(
				new FileOutputStream(new File("src//a.xml"))), format);
		// 写入Xml
		writer.write(document);
		// 立即写入
		writer.flush();
		writer.close();
	}

	public static void LoadAndEditXml() {

		// 创建SAXReader对象
		SAXReader reader = new SAXReader();
		// 通过SAXReader对象读取一个XML文件，并转换成Document对象
		try {
			Document document = reader.read(new File("src//sida.xml"));
			// 获取根节点元素
			Element root = document.getRootElement();
			// 遍历所有的元素节点
			listNodes(root);
			
			//获取四大元素节点中，子节点为红楼梦元素节点
			Element element = root.element("红楼梦");
			//获取到element的id属性节点对象
			Attribute attribute = element.attribute("id");
			//删除该属性
			element.remove(attribute);
			//添加新的属性
			element.addAttribute("name", "作者");
			//在红楼梦元素节点中添加朝代元素的节点
			Element newElement = element.addElement("朝代");
			newElement.setText("清朝");
			//获取element中的作者元素节点对象
			Element author = element.element("作者");
			//删除元素节点
			boolean flag = element.remove(author);
			System.out.println(flag);
			//添加CDATA区域
			element.addCDATA("红楼梦，是一部爱情小说");
			//写入到一个新的文件中
			try {
				writerDocumentToFile(document);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/***
	 * 遍历一个节点的属性和子节点以及子节点的属性
	 * @param node
	 */
	private static void listNodes(Element node) {

		System.out.println("当前节点的名称:" + node.getName());
		// 获取当前节点的所有属性
		List<Attribute> attrList = node.attributes();
		// 遍历属性
		for (Attribute attribute : attrList) {
			System.out.println(attribute.getText() + "------"
					+ attribute.getName() + "------" + attribute.getValue());

		}
		
		if (!(node.getTextTrim().equals(""))) {
			System.out.println("文本内容::::::" + node.getText());
		}
		
		//当前节点下面的子节点迭代器
		Iterator<Element> iterator = node.elementIterator();
		while (iterator.hasNext()) {
			Element element = (Element) iterator.next();
			listNodes(element);//这里递归了。
		}
	}

	
	/***
	 * 介绍Element中的element方法和elements方法的使用
	 * 
	 * @param node
	 */
	public static void elementMethod(Element node){
		
		//获取node节点中，子节点的元素名称为西游记的元素节点
		Element e = node.element("西游记");
		//获取西游记元素节点中，子节点为作者的元素节点(只能获取第一个作者元素节点)
		Element author = e.element("作者");
		
		System.out.println(e.getName() + "------" + author.getText());
		
		//获取西游记这个元素节点中，所有子节点名称为作者的元素节点
		List<Element> authors = e.elements("作者");
		for (Element au : authors) {
			System.out.println(au.getText());
		}
		
		//获取西游记这个元素节点的所有子节点
		List<Element> elements = e.elements();
		for (Element el : elements) {
			System.out.println(el.getText());
		}
	}
}
