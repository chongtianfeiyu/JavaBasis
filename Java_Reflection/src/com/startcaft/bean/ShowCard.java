package com.startcaft.bean;

public class ShowCard implements PCI {

	@Override
	public void open() {
		
		System.out.println("�Կ� ����......");
	}

	@Override
	public void close() {
		
		System.out.println("�Կ� �ر�......");
	}

}
