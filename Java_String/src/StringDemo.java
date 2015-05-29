import java.util.Iterator;


public class StringDemo {

	public static void main(String[] args) {
		String s1 = "abc";//s1就是一个String类型的变量，"abc"是一个对象，它存在常量池中
						  //字符串最大的特点是：一旦被初始化就不可以被改变。
		s1 = "kk";
		System.out.println(s1);//只是s1的指向 改变了 abc这个对象 还是没有改变。
		
		String s2 = new String("abc");//new String() "abc"
		String s3 = "abc";//由于常量池中有 abc 所以 不会再开辟空间来 新建对象
		
		
		System.out.println(s3 == s2);//false,s3和s2指向两个不同的对象。
		System.out.println(s3.equals(s2));//虽然s3和s2指向两个不同的对象，但是两个的值都是"abc"。
	
		System.out.println("====================================");
		method_get();
		System.out.println("====================================");  
        method_trans(); 
        System.out.println("====================================");  
        method_replace(); 
        System.out.println("====================================");  
        method_subString();  
        System.out.println("====================================");  
        method_split(); 
        System.out.println("====================================");  
        method_7(); 
	}
	
	
	public static void method_7(){
		String s = "   Hello Java     ";
		sop(s.toUpperCase());
		sop(s.toLowerCase());
		sop(s.trim());
	}
	
	
	public static void method_split() {
		String s = "zhangs,lisi,wangwu";
		String[] names = s.split(",");
		
		for (String string : names) {
			sop(string);
		}
	}
	
	
	public static void method_subString(){
		String s = "hellojava";
		sop(s.substring(2));//从指定位置开始到结尾，如果角标不存在，会出现字符串角标越界异常。
		sop(s.subSequence(2, 4));//包含头，不包含尾
	}
	
	
	public static void method_replace() {
		String s = "hello csharp";
		String s1 = s.replace('q', 'n');//如果要替换的字符串在原字符串中不存在，则返回的还是原字符串
		
		sop(s);
		sop(s1);
		String s2 = s.replace("csharp", "java");
		sop(s2);
	}
	
	
	private static void method_get() {
		String str = "abcdeakpf";
		//长度
		sop(str.length());
		//根据索引获取字符串
		sop(str.charAt(4));//当访问到字符串中不存在的角标时，会发生StringIndexOfBoundsException。
		
		
		//根据字符获取索引
		sop(str.indexOf('m', 3));//当找不到 指定的字符的时候 返回-1
		
		//反向索引一个字符出现的位置
		sop(str.lastIndexOf('a'));
		
		//返回指定角标上的ASCII码
		sop(str.codePointAt(3));//c 的 ASCII
		
		//返回指定索引之前的字符的 ASCII码
		sop(str.codePointBefore(3));//b 的ASCII
		
		//返回文本范围中的Unicode代码点数
		sop(str.codePointCount(0, 6));
	}
	
	
	public static void method_trans(){
		char[] arr = {'a','b','c','d','e','f','g'};
		String s = new String(arr);
		sop("s=" + s);
		s = new String(arr, 1, 3);//1是角标，3是个数。
		sop("s=" + s);
		

		sop(String.copyValueOf(arr));
		sop(String.copyValueOf(arr, 1, 3));
	}
	
	
	
	private static void sop(Object obj){
		System.out.println(obj);
	}
}
