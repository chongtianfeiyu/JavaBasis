package com.startcaft.java.enums;


/**
 * �������ö���ࣻ
 * ���ʹ��enum�ؼ��ֶ���ö���ࣻ
 * 		1,���õķ���:values()---����ö���������еĶ���valueOf(string name)---�����ַ�������ö�����ж�Ӧ�Ķ���(ֻ����ö������������)
 * 		2,�����ö����ʵ�ֽӿ�:
 * 			�����ò�ͬ��ö����Ķ�����ñ���д�ĳ��󷽷���ִ�е�Ч����ͬ(��ÿһ��������д���󷽷�)
 */
public class TestSeason1 {
	
	public static void main(String[] args) {
		
		Season1[] seasons = Season1.values();
		for (int i = 0; i < seasons.length; i++) {
			System.out.println(seasons[i]);
		}
		
		Season1 spring = Season1.valueOf("SPRING");
		Season1 winter = Season1.valueOf("WINTER");
		
		spring.show();
		winter.show();
		
		System.out.println(spring);
		
		
	}
}

interface Info{
	void show();
}

enum Season1 implements Info {
	
	SPRING("spring", "��ů����"){
		public void show(){
			System.out.println("����������");
		}
	},
	SUMMER("summer", "��������"){
		public void show(){
			System.out.println("�����Ļ�");
		}
	},
	AUTUMN("autumn", "�����ˬ"){
		public void show(){
			System.out.println("�������������ֵļ���");
		}
	},
	WINTER("winter", "��ѩ����"){
		public void show(){
			System.out.println("�������һ�ѻ�");
		}
	};

	private final String seasonName;
	private final String seasonDesc;

	private Season1(String seasonName, String seasonDesc) {
		this.seasonName = seasonName;
		this.seasonDesc = seasonDesc;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public String getSeasonDesc() {
		return seasonDesc;
	}

	@Override
	public String toString() {
		return "Season [seasonName=" + seasonName + ", seasonDesc="
				+ seasonDesc + "]";
	}

	public void show() {
		System.out.println("����һ������");
	}
}