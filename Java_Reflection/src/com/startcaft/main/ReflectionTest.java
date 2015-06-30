package com.startcaft.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.startcaft.bean.MainBoard;
import com.startcaft.bean.PCI;
import com.startcaft.bean.SoundCard;

public class ReflectionTest {

	public static void main(String[] args) throws Exception {
		
		MainBoard mb = new MainBoard();
		mb.run();
		
		//ÿ�����һ���豸������Ҫ�޸Ĵ��룬����һ��ʵ��PCI�ӿڵĶ���
		//mb.usePCI(new SoundCard());
		
		//�ܷ��޸Ĵ����������������
		//����new�ؼ�������ɣ�����ֱ�ӻ�ȡ.class�ļ������ڲ�ʵ�ִ�������Ķ���
		
		
		File configFile = new File("pci.properties");
		
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(configFile);
		
		properties.load(fileInputStream);
		
		for (int i = 0; i < properties.size(); i++) {
			
			String className = properties.get("pci" + (i+1)).toString();
			
			Class clazz = Class.forName(className);
			
			PCI pci = (PCI) clazz.newInstance();
			
			mb.usePCI(pci);
		}
		
		fileInputStream.close();
	}

}
