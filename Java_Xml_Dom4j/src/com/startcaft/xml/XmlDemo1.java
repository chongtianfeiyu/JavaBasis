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
				.println("==============��һ��Document����д�뵽һ��XML�ļ���================");
		String xmlTextString = "<A id='001'><B>���ʲô?</B></A>";
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
	 * ��document�ĵ�����д��һ���µ�xml�ļ�
	 * 
	 * @param document
	 * @throws Exception
	 */
	private static void writerDocumentToFile(Document document) throws Exception {
		// ���յ��Ű�
		// OutputFormat format = OutputFormat.createCompactFormat();
		// �Ű�����ĸ�ʽ
		OutputFormat format = OutputFormat.createPrettyPrint();
		// ����XML�ĵ��ı����ʽ
		format.setEncoding("UTF-8");
		// ����XMLWriter����ָ����д���ļ���������ʽ
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(
				new FileOutputStream(new File("src//a.xml"))), format);
		// д��Xml
		writer.write(document);
		// ����д��
		writer.flush();
		writer.close();
	}

	public static void LoadAndEditXml() {

		// ����SAXReader����
		SAXReader reader = new SAXReader();
		// ͨ��SAXReader�����ȡһ��XML�ļ�����ת����Document����
		try {
			Document document = reader.read(new File("src//sida.xml"));
			// ��ȡ���ڵ�Ԫ��
			Element root = document.getRootElement();
			// �������е�Ԫ�ؽڵ�
			listNodes(root);
			
			//��ȡ�Ĵ�Ԫ�ؽڵ��У��ӽڵ�Ϊ��¥��Ԫ�ؽڵ�
			Element element = root.element("��¥��");
			//��ȡ��element��id���Խڵ����
			Attribute attribute = element.attribute("id");
			//ɾ��������
			element.remove(attribute);
			//����µ�����
			element.addAttribute("name", "����");
			//�ں�¥��Ԫ�ؽڵ�����ӳ���Ԫ�صĽڵ�
			Element newElement = element.addElement("����");
			newElement.setText("�峯");
			//��ȡelement�е�����Ԫ�ؽڵ����
			Element author = element.element("����");
			//ɾ��Ԫ�ؽڵ�
			boolean flag = element.remove(author);
			System.out.println(flag);
			//���CDATA����
			element.addCDATA("��¥�Σ���һ������С˵");
			//д�뵽һ���µ��ļ���
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
	 * ����һ���ڵ�����Ժ��ӽڵ��Լ��ӽڵ������
	 * @param node
	 */
	private static void listNodes(Element node) {

		System.out.println("��ǰ�ڵ������:" + node.getName());
		// ��ȡ��ǰ�ڵ����������
		List<Attribute> attrList = node.attributes();
		// ��������
		for (Attribute attribute : attrList) {
			System.out.println(attribute.getText() + "------"
					+ attribute.getName() + "------" + attribute.getValue());

		}
		
		if (!(node.getTextTrim().equals(""))) {
			System.out.println("�ı�����::::::" + node.getText());
		}
		
		//��ǰ�ڵ�������ӽڵ������
		Iterator<Element> iterator = node.elementIterator();
		while (iterator.hasNext()) {
			Element element = (Element) iterator.next();
			listNodes(element);//����ݹ��ˡ�
		}
	}

	
	/***
	 * ����Element�е�element������elements������ʹ��
	 * 
	 * @param node
	 */
	public static void elementMethod(Element node){
		
		//��ȡnode�ڵ��У��ӽڵ��Ԫ������Ϊ���μǵ�Ԫ�ؽڵ�
		Element e = node.element("���μ�");
		//��ȡ���μ�Ԫ�ؽڵ��У��ӽڵ�Ϊ���ߵ�Ԫ�ؽڵ�(ֻ�ܻ�ȡ��һ������Ԫ�ؽڵ�)
		Element author = e.element("����");
		
		System.out.println(e.getName() + "------" + author.getText());
		
		//��ȡ���μ����Ԫ�ؽڵ��У������ӽڵ�����Ϊ���ߵ�Ԫ�ؽڵ�
		List<Element> authors = e.elements("����");
		for (Element au : authors) {
			System.out.println(au.getText());
		}
		
		//��ȡ���μ����Ԫ�ؽڵ�������ӽڵ�
		List<Element> elements = e.elements();
		for (Element el : elements) {
			System.out.println(el.getText());
		}
	}
}
