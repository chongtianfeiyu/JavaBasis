package com.startcaft.bean;

public class MainBoard {

	public MainBoard() {
		super();
	}

	public void run(){
		System.out.println("main board run...");
	}
	
//	//�޷���չ��ҪƵ���ĸ���Դ����
//	public void useSoundCard(SoundCard card){
//		
//		card.open();
//		card.close();
//	}
	
	public void usePCI(PCI p){
		
		if (p != null) {
			p.open();
			p.close();
		}
	}
}
