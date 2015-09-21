package com.startcaft.java.enums;


/**
 * 如果定义枚举类；
 * 如何使用enum关键字定义枚举类；
 * 		1,常用的方法:values()---返回枚举类中所有的对象。valueOf(string name)---根据字符串返回枚举类中对应的对象(只能是枚举类对象的名字)
 * 		2,如何让枚举类实现接口:
 * 			可以让不同的枚举类的对象调用被重写的抽象方法，执行的效果不同(让每一个对象重写抽象方法)
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
	
	SPRING("spring", "春暖花开"){
		public void show(){
			System.out.println("春天在哪里");
		}
	},
	SUMMER("summer", "夏日炎炎"){
		public void show(){
			System.out.println("生如夏花");
		}
	},
	AUTUMN("autumn", "秋高气爽"){
		public void show(){
			System.out.println("秋天是用来分手的季节");
		}
	},
	WINTER("winter", "白雪皑皑"){
		public void show(){
			System.out.println("冬天里的一把火");
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
		System.out.println("这是一个季节");
	}
}