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
		
		//每次添加一个设备，都需要修改代码，传递一个实现PCI接口的对象。
		//mb.usePCI(new SoundCard());
		
		//能否不修改代码就完成这个动作。
		//不用new关键字来完成，而是直接获取.class文件。在内部实现创建对象的动作
		
		
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
